package com.hbm.tileentity.bomb;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import com.hbm.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.IInventory;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.hbm.util.InventoryUtil;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCrashedBomb extends TileEntity {
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return TileEntity.INFINITE_EXTENT_AABB;
	}

	@Override
	public void updateEntity() {
		if(!worldObj.isRemote) {
			float ra = worldObj.rand.nextFloat();
			if(ra<0.0012){
				boolean canOutput = true;	
				ItemStack out = ra > 0.001 ? new ItemStack(ModItems.cell_balefire): 
				ra> 0.0006 ? new ItemStack(ModItems.cell_anti_schrabidium):new ItemStack(ModItems.cell_antimatter);
				TileEntity te0 = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord );
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
								break;
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
								break;
							}
						}
						}
				}
			}
}
}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return 65536.0D;
	}

}
