package com.hbm.blocks.bomb;

import com.hbm.blocks.BlockEnumMulti;
import com.hbm.config.BombConfig;
import com.hbm.entity.logic.EntityBalefire;
import com.hbm.entity.logic.EntityNukeExplosionMK5;
import com.hbm.explosion.vanillant.ExplosionVNT;
import com.hbm.explosion.vanillant.standard.BlockAllocatorStandard;
import com.hbm.explosion.vanillant.standard.BlockProcessorStandard;
import com.hbm.explosion.vanillant.standard.EntityProcessorCross;
import com.hbm.explosion.vanillant.standard.PlayerProcessorStandard;
import com.hbm.handler.threading.PacketThreading;
import com.hbm.interfaces.IBomb;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import com.hbm.packet.toclient.AuxParticlePacketNT;
import com.hbm.particle.helper.ExplosionCreator;
import com.hbm.tileentity.bomb.TileEntityCrashedBomb;
import com.hbm.util.EnumUtil;
import com.hbm.inventory.fluid.Fluids;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCrashedBomb extends BlockEnumMulti implements ITileEntityProvider, IBomb {
	
	public static enum EnumDudType {
		BALEFIRE, CONVENTIONAL, NUKE, SALTED
	}

	public BlockCrashedBomb(Material mat) {
		super(mat, EnumDudType.class, false, false);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityCrashedBomb();
	}

	@Override public int getRenderType() { return -1; }
	@Override public boolean isOpaqueCube() { return false; }
	@Override public boolean renderAsNormalBlock() { return false; }

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float fx, float fy, float fz) {
		if(world.isRemote) return true;

		if(player.getHeldItem() != null && player.getHeldItem().getItem() == ModItems.defuser) {

			//TODO: make this less scummy
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, new ItemStack(ModItems.egg_balefire_shard, 6)));
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, new ItemStack(ModItems.fluid_barrel_full, 5, Fluids.AMAT.getID())));
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, new ItemStack(ModItems.fluid_barrel_full, 1, Fluids.ASCHRAB.getID())));
			
				world.func_147480_a(x, y, z, false);
				return true;
		}

		return false;
	}

	@Override
	public BombReturnCode explode(World world, int x, int y, int z) {

		if(!world.isRemote) {

			EnumDudType type = EnumUtil.grabEnumSafely(EnumDudType.class, world.getBlockMetadata(x, y, z));
			world.setBlockToAir(x, y, z);

			if(type == type.BALEFIRE) {
				EntityBalefire bf = new EntityBalefire(world);
				bf.setPosition(x, y, z);
				bf.destructionRange = (int) (BombConfig.fatmanRadius * 1.25);
				world.spawnEntityInWorld(bf);
				spawnMush(world, x, y, z, true);
			}

			if(type == type.CONVENTIONAL) {
				ExplosionVNT xnt = new ExplosionVNT(world, x + 0.5, y + 0.5, z + 0.5, 35F);
				xnt.setBlockAllocator(new BlockAllocatorStandard(24));
				xnt.setBlockProcessor(new BlockProcessorStandard().setNoDrop());
				xnt.setEntityProcessor(new EntityProcessorCross(5D).withRangeMod(1.5F));
				xnt.setPlayerProcessor(new PlayerProcessorStandard());
				xnt.explode();
				ExplosionCreator.composeEffectLarge(world, x + 0.5, y + 0.5, z + 0.5);
			}
			
			if(type == type.NUKE) {
				world.spawnEntityInWorld(EntityNukeExplosionMK5.statFac(world, 35, x + 0.5, y + 0.5, z + 0.5));
				spawnMush(world, x, y, z, MainRegistry.polaroidID == 11 || world.rand.nextInt(100) == 0);
			}
			
			if(type == type.SALTED) {
				world.spawnEntityInWorld(EntityNukeExplosionMK5.statFac(world, 25, x + 0.5, y + 0.5, z + 0.5).moreFallout(25));
				spawnMush(world, x, y, z, MainRegistry.polaroidID == 11 || world.rand.nextInt(100) == 0);
			}
		}

		return BombReturnCode.DETONATED;
	}
	
	public static void spawnMush(World world, int x, int y, int z, boolean balefire) {
		world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, "hbm:weapon.mukeExplosion", 15.0F, 1.0F);
		NBTTagCompound data = new NBTTagCompound();
		data.setString("type", "muke");
		data.setBoolean("balefire", balefire);
		PacketThreading.createAllAroundThreadedPacket(new AuxParticlePacketNT(data, x + 0.5, y + 0.5, z + 0.5), new TargetPoint(world.provider.dimensionId, x + 0.5, y + 0.5, z + 0.5, 250));
	}
}
