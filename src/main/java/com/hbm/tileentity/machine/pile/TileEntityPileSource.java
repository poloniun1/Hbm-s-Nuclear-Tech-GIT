package com.hbm.tileentity.machine.pile;

import com.hbm.blocks.ModBlocks;
import net.minecraftforge.common.util.ForgeDirection;
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
			int n = this.getBlockType() == ModBlocks.block_graphite_source ? 2 : 5;
			
				this.castRay(5);
		
			if(ra< 0.00036) {

				ItemStack out = ra > 0.0003 ? new ItemStack(ModItems.nugget_bismuth) : new ItemStack(ModItems.nugget_technetium);
				live++;
				if(n == 2){
					out = ra > 0.0003 ? new ItemStack(ModItems.pellet_charged) : new ItemStack(ModItems.nugget_polonium);
					live++;
				}
				boolean canOutput = true;

				here:
				for (int i = -4 ; i <= 4 ; i++){
					for (int j = -4 ; j <= 4 ; j++){
						TileEntity te0 = worldObj.getTileEntity(xCoord + i, yCoord , zCoord + j);					
				if(te0 instanceof IInventory) {
					IInventory inv = (IInventory) te0;								

						for(int k = 0; k < inv.getSizeInventory(); k++) {
	
							int slot = k;
							
							if(!inv.isItemValidForSlot(slot, out))
								continue;
								
							ItemStack target = inv.getStackInSlot(slot);
							
							if(canOutput && InventoryUtil.doesStackDataMatch(out, target) && target.stackSize < Math.min(target.getMaxStackSize(), inv.getInventoryStackLimit())) {
								target.stackSize ++;
								canOutput = false;
								break here;
							}
						}
					if(canOutput){	
						for(int k = 0; k <inv.getSizeInventory(); k++) {
	
							int slot = k;
							
							if(!inv.isItemValidForSlot(slot, out))
								continue;
							
							if(inv.getStackInSlot(slot) == null ) {
								ItemStack copy = out.copy();
								copy.stackSize = 1;
								inv.setInventorySlotContents(slot, copy);
								canOutput = false;
								break here;
							}
						}
						}
				}
			}}
				if(canOutput){
				ForgeDirection dir = ForgeDirection.DOWN;
				EntityItem dust = new EntityItem(worldObj, xCoord + 0.5D + dir.offsetX * 0.75D, yCoord + 0.5D + dir.offsetY * 0.75D, zCoord + 0.5D + dir.offsetZ * 0.75D, out);
				dust.motionX = dir.offsetX * 0.25;
				dust.motionY = dir.offsetY * 0.25;
				dust.motionZ = dir.offsetZ * 0.25;
				worldObj.spawnEntityInWorld(dust);
				}	}		
				if(live >= 18) {
				boolean canInput = true;
				newthere:
				for (int i = -4 ; i <= 4 ; i++){
					for (int j = -4 ; j <= 4 ; j++){
						TileEntity te1 = worldObj.getTileEntity(xCoord + i, yCoord , zCoord + j);					
				if(te1 instanceof IInventory) {	
					IInventory inv = (IInventory) te1;
					int size = inv.getSizeInventory();	
					for(int k = 0; k < size; k++) {
						int index = k;
						ItemStack stack = inv.getStackInSlot(index);
						if(stack != null &&stack.getItem()== ModItems.pile_rod_plutonium){
							inv.decrStackSize(index, 1);
							worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.block_graphite_plutonium ,0, 3);
							canInput = false;
							break newthere;
						}
						else if(stack != null &&stack.getItem()== ModItems.pile_rod_source){
							worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.block_graphite_source ,0, 3);
							canInput = false;
							break newthere;
						}
					}

				}}}
				if(canInput)	worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.block_graphite_drilled, 0, 3);
				}
		}
	}
}

