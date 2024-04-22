package com.hbm.tileentity.machine.pile;
import com.hbm.blocks.machine.pile.BlockGraphiteBreedingFuel ;
import com.hbm.items.ModItems;
import com.hbm.blocks.ModBlocks;
import com.hbm.config.GeneralConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import api.hbm.block.IPileNeutronReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import com.hbm.blocks.generic.BlockFlammable;
import com.hbm.inventory.RecipesCommon.MetaBlock;
import com.hbm.lib.RefStrings;
import com.hbm.util.InventoryUtil;

import net.minecraft.inventory.IInventory;
import api.hbm.block.IToolable;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
public class TileEntityPileBreedingFuel extends TileEntityPileBase implements IPileNeutronReceiver {
	
	public int neutrons;
	public int lastNeutrons;
	public int progress;
	public static final int maxProgress = GeneralConfig.enable528 ? 5000 : 3000;
	
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote) {
			react();
			
			if(this.progress >= this.maxProgress) {
				TileEntity te0 = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
				if(te0 instanceof IInventory) {
					IInventory inv = (IInventory) te0;								
					ItemStack out =new ItemStack(ModItems.cell_tritium);
					boolean canOutput = true;
						for(int j = 0; j < inv.getSizeInventory(); j++) {
	
							int slot = j;
							
							if(!inv.isItemValidForSlot(slot, out))
								continue;
								
							ItemStack target = inv.getStackInSlot(slot);
							
							if(canOutput && InventoryUtil.doesStackDataMatch(out, target) && target.stackSize < Math.min(target.getMaxStackSize(), inv.getInventoryStackLimit())) {
								target.stackSize ++;
								canOutput = false;
								break;
							}
						}
					if(canOutput){	
						for(int j = 0; j <inv.getSizeInventory(); j++) {
	
							int slot = j;
							
							if(!inv.isItemValidForSlot(slot, out))
								continue;
							
							if(inv.getStackInSlot(slot) == null ) {
								ItemStack copy = out.copy();
								copy.stackSize = 1;
								inv.setInventorySlotContents(slot, copy);
								canOutput = false;
								break;
							}
						}
						}
				}
			
				else{
			ForgeDirection dir = ForgeDirection.DOWN;
			EntityItem dust = new EntityItem(worldObj, xCoord + 0.5D + dir.offsetX * 0.75D, yCoord + 0.5D + dir.offsetY * 0.75D, zCoord + 0.5D + dir.offsetZ * 0.75D, new ItemStack(ModItems.cell_tritium));
			dust.motionX = dir.offsetX * 0.25;
			dust.motionY = dir.offsetY * 0.25;
			dust.motionZ = dir.offsetZ * 0.25;
			worldObj.spawnEntityInWorld(dust);
				}
			TileEntity te1 = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
			if(te1 instanceof IInventory) {
						
					IInventory inv = (IInventory) te1;
					int size = inv.getSizeInventory();
						
					for(int i = 0; i < size; i++) {
						int index = i;
						ItemStack stack = inv.getStackInSlot(index);
						if(stack != null &&stack.getItem()== ModItems.pile_rod_uranium){
							inv.decrStackSize(index, 1);
							worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.block_graphite_fuel , 0, 3);
							break;
						}
						else if(stack != null &&stack.getItem()== ModItems.pile_rod_lithium){
							inv.decrStackSize(index, 1);
							worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.block_graphite_lithium , 0, 3);
							break;
						}
						else if((index == size -1&& stack == null) || (index == size -1 && stack.getItem()!= ModItems.pile_rod_uranium && stack.getItem()!= ModItems.pile_rod_lithium)){
							worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.block_graphite_drilled ,0, 3);
							break;
						}
					}
				}	else 	worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.block_graphite_drilled, 0, 3);
			}
		}
	}
	
	private void react() {
		
		this.lastNeutrons = this.neutrons;
		this.progress += this.neutrons;
		
		this.neutrons = 0;
		
		if(lastNeutrons <= 0)
			return;
		
		for(int i = 0; i < 2; i++)
			this.castRay(1, 5);
	}
	
	@Override
	public void receiveNeutrons(int n) {
		this.neutrons += n;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.progress = nbt.getInteger("progress");
		this.neutrons = nbt.getInteger("neutrons");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("progress", this.progress);
		nbt.setInteger("neutrons", this.neutrons);
	}
}
