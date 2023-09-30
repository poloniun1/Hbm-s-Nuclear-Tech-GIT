package com.hbm.entity.item;

import java.util.ArrayList;
import java.util.List;

import com.hbm.inventory.RecipesCommon.AStack;
import com.hbm.items.ModItems;
import com.hbm.items.tool.ItemDrone.EnumDroneType;
import com.hbm.tileentity.network.TileEntityDroneDock;
import com.hbm.tileentity.network.TileEntityDroneProvider;
import com.hbm.tileentity.network.TileEntityDroneRequester;
import com.hbm.util.fauxpointtwelve.BlockPos;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityRequestDrone extends EntityDroneBase {
	
	public ItemStack heldItem;
	public List program = new ArrayList();
	
	public static enum DroneProgram {
		UNLOAD, DOCK
	}

	public EntityRequestDrone(World world) {
		super(world);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if(!worldObj.isRemote) {
			
			if(Vec3.createVectorHelper(motionX, motionY, motionZ).lengthVector() < 0.01) {
				if(program.isEmpty()) {
					this.setDead(); //self-destruct if no further operations are pending
					this.entityDropItem(new ItemStack(ModItems.drone, 1, EnumDroneType.REQUEST.ordinal()), 1F);
					return;
				}
				
				Object next = program.get(0);
				program.remove(0);
				
				if(next instanceof BlockPos) {
					BlockPos pos = (BlockPos) next;
					this.setTarget(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				} else if(next instanceof AStack && heldItem == null) {
					
					AStack aStack = (AStack) next;
					TileEntity tile = worldObj.getTileEntity((int) Math.floor(posX), (int) Math.floor(posY - 1), (int) Math.floor(posZ));
					
					if(tile instanceof TileEntityDroneProvider) {
						TileEntityDroneProvider provider = (TileEntityDroneProvider) tile;
						
						for(int i = 0; i < provider.slots.length; i++) {
							ItemStack stack = provider.slots[i];
							
							if(stack != null && aStack.matchesRecipe(stack, true)) {
								this.heldItem = stack.copy();
								this.setAppearance(1);
								provider.slots[i] = null;
								provider.markDirty();
								break;
							}
						}
					}
				} else if(next == DroneProgram.UNLOAD && this.heldItem != null) {

					TileEntity tile = worldObj.getTileEntity((int) Math.floor(posX), (int) Math.floor(posY - 1), (int) Math.floor(posZ));
					if(tile instanceof TileEntityDroneRequester) {
						TileEntityDroneRequester requester = (TileEntityDroneRequester) tile;
						
						for(int i = 9; i < 18; i++) {
							ItemStack stack = requester.slots[i];
							if(stack != null && stack.getItem() == heldItem.getItem() && stack.getItemDamage() == heldItem.getItemDamage()) {
								int toTransfer = Math.min(stack.getMaxStackSize() - stack.stackSize, heldItem.stackSize);
								requester.slots[i].stackSize += toTransfer;
								this.heldItem.stackSize -= toTransfer;
							}
						}
						
						if(this.heldItem.stackSize <= 0) this.heldItem = null;
						
						if(this.heldItem != null) for(int i = 9; i < 18; i++) {
							if(requester.slots[i] == null) {
								requester.slots[i] = this.heldItem.copy();
								this.heldItem = null;
								break;
							}
						}
						
						if(this.heldItem == null) {
							this.setAppearance(0);
						}
						
						requester.markDirty();
					}
				} else if(next == DroneProgram.DOCK) {

					TileEntity tile = worldObj.getTileEntity((int) Math.floor(posX), (int) Math.floor(posY - 1), (int) Math.floor(posZ));
					if(tile instanceof TileEntityDroneDock) {
						TileEntityDroneDock dock = (TileEntityDroneDock) tile;
						
						for(int i = 0; i < dock.slots.length; i++) {
							if(dock.slots[i] == null) {
								this.setDead();
								dock.slots[i] = new ItemStack(ModItems.drone, 1, EnumDroneType.REQUEST.ordinal());
							}
						}
					}
					
					if(!this.isDead) {
						this.setDead();
						this.entityDropItem(new ItemStack(ModItems.drone, 1, EnumDroneType.REQUEST.ordinal()), 1F);
					}
				}
			}
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		
		if(nbt.hasKey("held")) {
			NBTTagCompound stack = nbt.getCompoundTag("held");
			this.heldItem = ItemStack.loadItemStackFromNBT(stack);
		}

		this.dataWatcher.updateObject(10, nbt.getByte("app"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		
		if(heldItem != null) {
			NBTTagCompound stack = new NBTTagCompound();
			this.heldItem.writeToNBT(stack);
			nbt.setTag("held", stack);
		}

		nbt.setByte("app", this.dataWatcher.getWatchableObjectByte(10));
	}
}