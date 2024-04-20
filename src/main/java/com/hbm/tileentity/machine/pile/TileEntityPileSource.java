package com.hbm.tileentity.machine.pile;

import net.minecraftforge.common.util.ForgeDirection;
import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.IInventory;
import api.hbm.block.IToolable;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.hbm.util.InventoryUtil;
public class TileEntityPileSource extends TileEntityPileBase {
	int live = 0;
	@Override
	public void updateEntity() {

		if(!worldObj.isRemote) {
			float ra = worldObj.rand.nextFloat();			
			int n = this.getBlockType() == ModBlocks.block_graphite_source ? 1 : 3;

			for(int i = 0; i < 12; i++) {
				this.castRay(n, 5);
			}
			if(n == 2 && ra <0.0003) {
				live++;
				TileEntity te0 = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
				if(te0 instanceof IInventory) {
					IInventory inv = (IInventory) te0;								
					ItemStack out =new ItemStack(ModItems.nugget_technetium);
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
				EntityItem dust = new EntityItem(worldObj, xCoord + 0.5D + dir.offsetX * 0.75D, yCoord + 0.5D + dir.offsetY * 0.75D, zCoord + 0.5D + dir.offsetZ * 0.75D, new ItemStack(ModItems.nugget_technetium));
				dust.motionX = dir.offsetX * 0.25;
				dust.motionY = dir.offsetY * 0.25;
				dust.motionZ = dir.offsetZ * 0.25;
				worldObj.spawnEntityInWorld(dust);
				}	}
			if(n == 2 && ra < 0.00036 && ra > 0.0003) {
				live++;
				TileEntity te0 = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
				if(te0 instanceof IInventory) {
					IInventory inv = (IInventory) te0;								
					ItemStack out =new ItemStack(ModItems.nugget_bismuth);
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
				EntityItem dust = new EntityItem(worldObj, xCoord + 0.5D + dir.offsetX * 0.75D, yCoord + 0.5D + dir.offsetY * 0.75D, zCoord + 0.5D + dir.offsetZ * 0.75D, new ItemStack(ModItems.nugget_bismuth));
				dust.motionX = dir.offsetX * 0.25;
				dust.motionY = dir.offsetY * 0.25;
				dust.motionZ = dir.offsetZ * 0.25;
				worldObj.spawnEntityInWorld(dust);
				}	}
				if(live == 18) {			
				TileEntity te1 = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
				if(te1 instanceof IInventory) {	
					IInventory inv = (IInventory) te1;
					int size = inv.getSizeInventory();	
					for(int i = 0; i < size; i++) {
						int index = i;
						ItemStack stack = inv.getStackInSlot(index);
						if(stack != null &&stack.getItem()== ModItems.pile_rod_plutonium){
							inv.decrStackSize(index, 1);
							worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.block_graphite_plutonium ,0, 3);
							break;
						}
						else if((index == size -1&& stack == null) || (index == size -1 && stack.getItem()!= ModItems.pile_rod_plutonium)){
							worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.block_graphite_drilled ,0, 3);
							break;
						}
					}

				}
				else 	worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.block_graphite_drilled, 0, 3);
				}
		}
	}
}
