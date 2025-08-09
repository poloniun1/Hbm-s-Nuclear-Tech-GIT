package com.hbm.handler.ability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.hbm.inventory.recipes.CustomMachineRecipes;
import com.hbm.inventory.recipes.CustomMachineRecipes.CustomMachineRecipe;
import com.hbm.items.ModItems;
import com.hbm.config.ToolConfig;
import com.hbm.explosion.ExplosionNT;
import com.hbm.explosion.ExplosionNT.ExAttrib;
import com.hbm.handler.ThreeInts;
import com.hbm.items.tool.ItemToolAbility;
import com.hbm.util.EnchantmentUtil;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.entity.item.EntityItem;


public interface IToolAreaAbility extends IBaseAbility {
	// Should call tool.breakExtraBlock on a bunch of blocks.
	// The initial block is implicitly broken, so don't call breakExtraBlock on it.
	// Returning true skips the reference block from being broken
	public boolean onDig(int level, World world, int x, int y, int z, EntityPlayer player, ItemToolAbility tool);

	// Whether breakExtraBlock is called at all. Currently only false for explosion
	public default boolean allowsHarvest(int level) {
		return true;
	}

	public final static int SORT_ORDER_BASE = 0;

	// region handlers
	public static final IToolAreaAbility NONE = new IToolAreaAbility() {
		@Override
		public String getName() {
			return "";
		}

		@Override
		public int sortOrder() {
			return SORT_ORDER_BASE + 0;
		}

		@Override
		public boolean onDig(int level, World world, int x, int y, int z, EntityPlayer player, ItemToolAbility tool) {
			return false;
		}
	};

	public static final IToolAreaAbility RECURSION = new IToolAreaAbility() {
		@Override
		public String getName() {
			return "tool.ability.recursion";
		}

		@Override
		public boolean isAllowed() {
			return ToolConfig.abilityVein;
		}

		public final int[] radiusAtLevel = { 3, 4, 5, 6, 7, 9, 10 };

		@Override
		public int levels() {
			return radiusAtLevel.length;
		}

		@Override
		public String getExtension(int level) {
			return " (" + radiusAtLevel[level] + ")";
		}

		@Override
		public int sortOrder() {
			return SORT_ORDER_BASE + 1;
		}

		// Note: if reusing it across different instatces of a tool
		// is a problem here, then it had already been one before
		// the refactor! The solution is to simply make this a local
		// of the onDig method and pass it around as a parameter.
		private Set<ThreeInts> pos = new HashSet<>();

		@Override
		public boolean onDig(int level, World world, int x, int y, int z, EntityPlayer player, ItemToolAbility tool) {
			Block b = world.getBlock(x, y, z);

			if(b == Blocks.stone && !ToolConfig.recursiveStone) {
				return false;
			}

			if(b == Blocks.netherrack && !ToolConfig.recursiveNetherrack) {
				return false;
			}

			pos.clear();

			recurse(world, x, y, z, x, y, z, player, tool, 0, radiusAtLevel[level]);
			ItemStack stack = player.getHeldItem();
			EnchantmentUtil.addEnchantment(stack, Enchantment.silkTouch, 1);

			return false;
		}

		private final List<ThreeInts> offsets = new ArrayList<ThreeInts>(3 * 3 * 3 - 1) {
			{
				for(int dx = -1; dx <= 1; dx++) {
					for(int dy = -1; dy <= 1; dy++) {
						for(int dz = -1; dz <= 1; dz++) {
							if(dx != 0 || dy != 0 || dz != 0) {
								add(new ThreeInts(dx, dy, dz));
							}
						}
					}
				}
			}
		};

		private void recurse(World world, int x, int y, int z, int refX, int refY, int refZ, EntityPlayer player, ItemToolAbility tool, int depth, int radius) {
			List<ThreeInts> shuffledOffsets = new ArrayList<>(offsets);
			Collections.shuffle(shuffledOffsets);

			for(ThreeInts offset : shuffledOffsets) {
				breakExtra(world, x + offset.x, y + offset.y, z + offset.z, refX, refY, refZ, player, tool, depth, radius);
			}
		}

		private void breakExtra(World world, int x, int y, int z, int refX, int refY, int refZ, EntityPlayer player, ItemToolAbility tool, int depth, int radius) {
			if(pos.contains(new ThreeInts(x, y, z)))
				return;

			depth += 1;

			if(depth > ToolConfig.recursionDepth)
				return;

			pos.add(new ThreeInts(x, y, z));

			// don't lose the ref block just yet
			if(x == refX && y == refY && z == refZ)
				return;

			if(Vec3.createVectorHelper(x - refX, y - refY, z - refZ).lengthVector() > radius)
				return;

			Block b = world.getBlock(x, y, z);
			Block ref = world.getBlock(refX, refY, refZ);
			int meta = world.getBlockMetadata(x, y, z);
			int refMeta = world.getBlockMetadata(refX, refY, refZ);

			if(!isSameBlock(b, ref))
				return;

			if(meta != refMeta)
				return;

			if(player.getHeldItem() == null)
				return;
			ItemStack stack = player.getHeldItem();

			EnchantmentUtil.addEnchantment(stack, Enchantment.silkTouch, 1);

			tool.breakExtraBlock(world, x, y, z, player, refX, refY, refZ);

			EnchantmentUtil.removeEnchantment(stack, Enchantment.silkTouch);

			recurse(world, x, y, z, refX, refY, refZ, player, tool, depth, radius);
		}

		private boolean isSameBlock(Block b1, Block b2) {
			if(b1 == b2)
				return true;
			if((b1 == Blocks.redstone_ore && b2 == Blocks.lit_redstone_ore) || (b1 == Blocks.lit_redstone_ore && b2 == Blocks.redstone_ore))
				return true;

			return false;
		}
	};

	public static final IToolAreaAbility HAMMER = new IToolAreaAbility() {
		@Override
		public String getName() {
			return "tool.ability.hammer";
		}

		@Override
		public boolean isAllowed() {
			return ToolConfig.abilityHammer;
		}

		public final int[] rangeAtLevel = { 1, 2, 3, 4 };

		@Override
		public int levels() {
			return rangeAtLevel.length;
		}

		@Override
		public String getExtension(int level) {
			return " (" + rangeAtLevel[level] + ")";
		}

		@Override
		public int sortOrder() {
			return SORT_ORDER_BASE + 2;
		}

		@Override
		public boolean onDig(int level, World world, int x, int y, int z, EntityPlayer player, ItemToolAbility tool) {
			int range = rangeAtLevel[level];

			for(int a = x - range; a <= x + range; a++) {
				for(int b = y - range; b <= y + range; b++) {
					for(int c = z - range; c <= z + range; c++) {
						if(a == x && b == y && c == z)
							continue;

						tool.breakExtraBlock(world, a, b, c, player, x, y, z);
					}
				}
			}

			return false;
		}
	};

	public static final IToolAreaAbility HAMMER_FLAT = new IToolAreaAbility() {
		@Override
		public String getName() {
			return "tool.ability.hammer_flat";
		}

		@Override
		public boolean isAllowed() {
			return ToolConfig.abilityHammer;
		}

		public final int[] rangeAtLevel = { 1, 2, 3, 4 };

		@Override
		public int levels() {
			return rangeAtLevel.length;
		}

		@Override
		public String getExtension(int level) {
			return " (" + rangeAtLevel[level] + ")";
		}

		@Override
		public int sortOrder() {
			return SORT_ORDER_BASE + 3;
		}

		@Override
		public boolean onDig(int level, World world, int x, int y, int z, EntityPlayer player, ItemToolAbility tool) {
			int range = rangeAtLevel[level];

			MovingObjectPosition hit = raytraceFromEntity(world, player, false, 4.5d);
			if(hit == null) return true;
			int sideHit = hit.sideHit;

			// we successfully destroyed a block. time to do AOE!
			int xRange = range;
			int yRange = range;
			int zRange = 0;
			switch (sideHit) {
				case 0:
				case 1:
					yRange = 0;
					zRange = range;
					break;
				case 2:
				case 3:
					xRange = range;
					zRange = 0;
					break;
				case 4:
				case 5:
					xRange = 0;
					zRange = range;
					break;
			}

			for(int a = x - xRange; a <= x + xRange; a++) {
				for(int b = y - yRange; b <= y + yRange; b++) {
					for(int c = z - zRange; c <= z + zRange; c++) {
						if(a == x && b == y && c == z)
							continue;

						tool.breakExtraBlock(world, a, b, c, player, x, y, z);
					}
				}
			}

			return false;
		}

		// Taken from TConstruct, licensed under CC0 (public domain)
		// https://github.com/SlimeKnights/TinkersConstruct/blob/9ea7a0e60fe180aff591701b12c89da21da99289/src/main/java/tconstruct/library/tools/AbilityHelper.java#L707-L731
		private MovingObjectPosition raytraceFromEntity(World world, EntityPlayer player, boolean par3, double range) {
			float f = 1.0F;
			float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
			float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
			double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
			double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f + 1.62D;
			double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
			Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
			float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
			float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
			float f5 = -MathHelper.cos(-f1 * 0.017453292F);
			float f6 = MathHelper.sin(-f1 * 0.017453292F);
			float f7 = f4 * f5;
			float f8 = f3 * f5;
			double d3 = range;
			if (player instanceof EntityPlayerMP)
			{
				d3 = ((EntityPlayerMP) player).theItemInWorldManager.getBlockReachDistance();
			}
			Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
			return world.func_147447_a(vec3, vec31, par3, !par3, par3);
		}
	};

	public static final IToolAreaAbility EXPLOSION = new IToolAreaAbility() {
		@Override
		public String getName() {
			return "tool.ability.explosion";
		}

		@Override
		public boolean isAllowed() {
			return ToolConfig.abilityExplosion;
		}

		public final float[] strengthAtLevel = { 2.5F, 5F, 10F, 15F };

		@Override
		public int levels() {
			return strengthAtLevel.length;
		}

		@Override
		public String getExtension(int level) {
			return " (" + strengthAtLevel[level] + ")";
		}

		@Override
		public boolean allowsHarvest(int level) {
			return false;
		}

		@Override
		public int sortOrder() {
			return SORT_ORDER_BASE + 4;
		}

		@Override
		public boolean onDig(int level, World world, int x, int y, int z, EntityPlayer player, ItemToolAbility tool) {
			float strength = strengthAtLevel[level];

			ExplosionNT ex = new ExplosionNT(player.worldObj, player, x + 0.5, y + 0.5, z + 0.5, strength);
			ex.addAttrib(ExAttrib.ALLDROP);
			ex.addAttrib(ExAttrib.NOHURT);
			ex.addAttrib(ExAttrib.NOPARTICLE);
			ex.doExplosionA();
			ex.doExplosionB(false);

			player.worldObj.createExplosion(player, x + 0.5, y + 0.5, z + 0.5, 0.1F, false);

			return true;
		}
	};

	public static final IToolAreaAbility GOD = new IToolAreaAbility() {
		@Override
		public String getName() {
			return "tool.ability.god";
		}

		@Override
		public boolean isAllowed() {
			return ToolConfig.abilityGod;
		}

		public final int[] radiusAtLevel = { 1, 2, 3, 4};

		@Override
		public int levels() {
			return radiusAtLevel.length;
		}

		@Override
		public String getExtension(int level) {
			return " (" + radiusAtLevel[level] + ")";
		}

		@Override
		public int sortOrder() {
			return SORT_ORDER_BASE + 5;
		}
		// Note: if reusing it across different instatces of a tool
		// is a problem here, then it had already been one before
		// the refactor! The solution is to simply make this a local
		// of the onDig method and pass it around as a parameter.
		private Set<ThreeInts> pos = new HashSet<>();
		private List<ItemStack> products = new ArrayList();
			
		@Override
		public boolean onDig(int level, World world, int x, int y, int z, EntityPlayer player, ItemToolAbility tool) {
			Block b = world.getBlock(x, y, z);
			int meta = world.getBlockMetadata(x, y, z);
			if(b == Blocks.stone && !ToolConfig.recursiveStone) {
				return false;
			}

			if(b == Blocks.netherrack && !ToolConfig.recursiveNetherrack) {
				return false;
			}
			if(b == Blocks.dirt)
				return false;
			if(b == Blocks.grass)
				return false;
			if(b == Blocks.sand)
				return false;
			if(b == Blocks.sandstone)
				return false;
			pos.clear();
			ItemStack stack = new ItemStack(b, 1, meta);
			CustomMachineRecipe result = getMatchingRecipe(stack);
			ItemStack stack1;
			CustomMachineRecipe result1;
			ItemStack st;			
			if(result != null) {
				for(int i = 0; i < result.outputItems.length; i++) {
					if(world.rand.nextFloat() < result.outputItems[i].value){
						stack1 = result.outputItems[i].key.copy();
						result1 = getMatchingRecipe(stack1);
							if(result1 != null) {								
								for(int j = 0; j < result1.outputItems.length;j++) {
									if(world.rand.nextFloat() < result1.outputItems[j].value){
										st= result1.outputItems[j].key.copy();
										if(st != null)  products.add(st);
								}
							}
						}
					}
				}
			}

			products = products.stream()
        // 表示id为key， 接着如果有重复的，那么从BillsNums对象o1与o2中筛选出一个，这里选择o1，
        // 并把id重复，需要将nums和sums与o1进行合并的o2, 赋值给o1，最后返回o1
			.collect(Collectors.toMap(ItemStack::getItem, a -> a, (o1,o2)-> {
				o1.stackSize += o2.stackSize;
				return o1;
			})).values().stream().collect(Collectors.toList());	
		
			recurse(world, x, y, z, x, y, z, player, tool, 0, radiusAtLevel[level]);
			if(products != null) {
				player.getHeldItem().damageItem(1, player);
				for(ItemStack product : products) 						
					world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, product.copy()));
				world.setBlockToAir(x, y, z);
			}						
			return false;
		}

		private final List<ThreeInts> offsets = new ArrayList<ThreeInts>(3 * 3 * 3 - 1) {
			{
				for(int dx = -1; dx <= 1; dx++) {
					for(int dy = -1; dy <= 1; dy++) {
						for(int dz = -1; dz <= 1; dz++) {
							if(dx != 0 || dy != 0 || dz != 0) {
								add(new ThreeInts(dx, dy, dz));
							}
						}
					}
				}
			}
		};

		private void recurse(World world, int x, int y, int z, int refX, int refY, int refZ, EntityPlayer player, ItemToolAbility tool, int depth, int radius) {
			List<ThreeInts> shuffledOffsets = new ArrayList<>(offsets);
			Collections.shuffle(shuffledOffsets);

			for(ThreeInts offset : shuffledOffsets) {
				breakExtra(world, x + offset.x, y + offset.y, z + offset.z, refX, refY, refZ, player, tool, depth, radius);
			}
		}

		private void breakExtra(World world, int x, int y, int z, int refX, int refY, int refZ, EntityPlayer player, ItemToolAbility tool, int depth, int radius) {
			if(pos.contains(new ThreeInts(x, y, z)))
				return;

			depth += 1;

			if(depth > ToolConfig.recursionDepth)
				return;

			pos.add(new ThreeInts(x, y, z));

			// don't lose the ref block just yet
			if(x == refX && y == refY && z == refZ)
				return;

			if(Vec3.createVectorHelper(x - refX, y - refY, z - refZ).lengthVector() > radius)
				return;

			Block b = world.getBlock(x, y, z);
			Block ref = world.getBlock(refX, refY, refZ);
			int meta = world.getBlockMetadata(x, y, z);
			int refMeta = world.getBlockMetadata(refX, refY, refZ);

			if(!isSameBlock(b, ref))
				return;

			if(meta != refMeta)
				return;

			if(player.getHeldItem() == null)
				return;

			if(products != null) {
				player.getHeldItem().damageItem(1, player);
				for(ItemStack product : products) 						
					world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, product.copy()));
			}else tool.breakExtraBlock(world, x, y, z, player, refX, refY, refZ);
			world.setBlockToAir(x, y, z);	
			recurse(world, x, y, z, refX, refY, refZ, player, tool, depth, radius);
		}

		private boolean isSameBlock(Block b1, Block b2) {
			if(b1 == b2)
				return true;
			if((b1 == Blocks.redstone_ore && b2 == Blocks.lit_redstone_ore) || (b1 == Blocks.lit_redstone_ore && b2 == Blocks.redstone_ore))
				return true;

			return false;
		}

		public CustomMachineRecipe getMatchingRecipe(ItemStack stack) {
			List<CustomMachineRecipe> recipes = CustomMachineRecipes.recipes.get("normalfactory");
			if(recipes == null || recipes.isEmpty()) return null;

			for(CustomMachineRecipe recipe : recipes) {
				if(recipe.inputItems.length == 0) continue;
				if(!recipe.inputItems[0].matchesRecipe(stack, true)) continue;
				return recipe;
			}

			return null;
		}
	};


	public static final IToolAreaAbility WORLD = new IToolAreaAbility() {
		@Override
		public String getName() {
			return "tool.ability.world";
		}

		@Override
		public boolean isAllowed() {
			return ToolConfig.abilityGod;
		}

		public final int[] radiusAtLevel = { 1, 2, 3, 5};

		@Override
		public int levels() {
			return radiusAtLevel.length;
		}

		@Override
		public String getExtension(int level) {
			return " (" + radiusAtLevel[level] + ")";
		}

		@Override
		public int sortOrder() {
			return SORT_ORDER_BASE + 6;
		}
		// Note: if reusing it across different instatces of a tool
		// is a problem here, then it had already been one before
		// the refactor! The solution is to simply make this a local
		// of the onDig method and pass it around as a parameter.
		private Set<ThreeInts> pos = new HashSet<>();
		private List<ItemStack> products = new ArrayList();
			
		@Override
		public boolean onDig(int level, World world, int x, int y, int z, EntityPlayer player, ItemToolAbility tool) {
			Block b = world.getBlock(x, y, z);
			int meta = world.getBlockMetadata(x, y, z);
			if(b == Blocks.stone && !ToolConfig.recursiveStone) {
				return false;
			}

			if(b == Blocks.netherrack && !ToolConfig.recursiveNetherrack) {
				return false;
			}
			if(b == Blocks.dirt)
				return false;
			if(b == Blocks.grass)
				return false;
			if(b == Blocks.sand)
				return false;
			if(b == Blocks.sandstone)
				return false;
			pos.clear();
			ItemStack stack = new ItemStack(b, 1, meta);
			CustomMachineRecipe result = getMatchingRecipe(stack);
			ItemStack stack1;			
			if(result != null) {
				for(int i = 0; i < result.outputItems.length; i++) {
					if(world.rand.nextFloat() < result.outputItems[i].value){
						stack1 = result.outputItems[i].key.copy();
						if(stack1 != null)  products.add(stack1);
					}
				}
			}

		
			recurse(world, x, y, z, x, y, z, player, tool, 0, radiusAtLevel[level]);
			if(products != null) {
				player.getHeldItem().damageItem(1, player);
				for(ItemStack product : products) 						
					world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, product.copy()));
				world.setBlockToAir(x, y, z);
			}						
			return false;
		}

		private final List<ThreeInts> offsets = new ArrayList<ThreeInts>(3 * 3 * 3 - 1) {
			{
				for(int dx = -1; dx <= 1; dx++) {
					for(int dy = -1; dy <= 1; dy++) {
						for(int dz = -1; dz <= 1; dz++) {
							if(dx != 0 || dy != 0 || dz != 0) {
								add(new ThreeInts(dx, dy, dz));
							}
						}
					}
				}
			}
		};

		private void recurse(World world, int x, int y, int z, int refX, int refY, int refZ, EntityPlayer player, ItemToolAbility tool, int depth, int radius) {
			List<ThreeInts> shuffledOffsets = new ArrayList<>(offsets);
			Collections.shuffle(shuffledOffsets);

			for(ThreeInts offset : shuffledOffsets) {
				breakExtra(world, x + offset.x, y + offset.y, z + offset.z, refX, refY, refZ, player, tool, depth, radius);
			}
		}

		private void breakExtra(World world, int x, int y, int z, int refX, int refY, int refZ, EntityPlayer player, ItemToolAbility tool, int depth, int radius) {
			if(pos.contains(new ThreeInts(x, y, z)))
				return;

			depth += 1;

			if(depth > ToolConfig.recursionDepth)
				return;

			pos.add(new ThreeInts(x, y, z));

			// don't lose the ref block just yet
			if(x == refX && y == refY && z == refZ)
				return;

			if(Vec3.createVectorHelper(x - refX, y - refY, z - refZ).lengthVector() > radius)
				return;

			Block b = world.getBlock(x, y, z);
			Block ref = world.getBlock(refX, refY, refZ);
			int meta = world.getBlockMetadata(x, y, z);
			int refMeta = world.getBlockMetadata(refX, refY, refZ);

			if(!isSameBlock(b, ref))
				return;

			if(meta != refMeta)
				return;

			if(player.getHeldItem() == null)
				return;

			if(products != null) {
				player.getHeldItem().damageItem(1, player);
				for(ItemStack product : products) 						
					world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, product.copy()));
			}else tool.breakExtraBlock(world, x, y, z, player, refX, refY, refZ);
			world.setBlockToAir(x, y, z);	
			recurse(world, x, y, z, refX, refY, refZ, player, tool, depth, radius);
		}

		private boolean isSameBlock(Block b1, Block b2) {
			if(b1 == b2)
				return true;
			if((b1 == Blocks.redstone_ore && b2 == Blocks.lit_redstone_ore) || (b1 == Blocks.lit_redstone_ore && b2 == Blocks.redstone_ore))
				return true;

			return false;
		}

		public CustomMachineRecipe getMatchingRecipe(ItemStack stack) {
			List<CustomMachineRecipe> recipes = CustomMachineRecipes.recipes.get("simplefactory");
			if(recipes == null || recipes.isEmpty()) return null;

			for(CustomMachineRecipe recipe : recipes) {
				if(recipe.inputItems.length == 0) continue;
				if(!recipe.inputItems[0].matchesRecipe(stack, true)) continue;
				return recipe;
			}

			return null;
		}
	};
	// endregion handlers



	static final IToolAreaAbility[] abilities = { NONE, RECURSION, HAMMER, HAMMER_FLAT, EXPLOSION, GOD, WORLD };

	static IToolAreaAbility getByName(String name) {
		for(IToolAreaAbility ability : abilities) {
			if(ability.getName().equals(name))
				return ability;
		}

		return NONE;
	}
}
