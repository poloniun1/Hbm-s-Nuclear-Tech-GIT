package com.hbm.tileentity.machine.pile;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.GeneralConfig;
import com.hbm.handler.threading.PacketThreading;
import com.hbm.packet.toclient.AuxParticlePacketNT;
import com.hbm.blocks.machine.pile.BlockGraphiteFuel;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import api.hbm.block.IPileNeutronReceiver;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import com.hbm.blocks.generic.BlockFlammable;
import com.hbm.items.ModItems;
import com.hbm.lib.RefStrings;
import com.hbm.util.InventoryUtil;

import net.minecraft.inventory.IInventory;
import api.hbm.block.IToolable;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityPileFuel extends TileEntityPileBase implements IPileNeutronReceiver {

	public int heat;
	public static final int maxHeat = 1000;
	public int neutrons;
	public int lastNeutrons;
	public int progress;
	public boolean canProduce = true;
	public static final int maxProgress = GeneralConfig.enable528 ? 15000 : 12000; //might double to reduce compact setup's effectiveness

	@Override
	public void updateEntity() {

		if(!worldObj.isRemote) {
			dissipateHeat();
			checkRedstone(react());
			transmute();
		float ra = worldObj.rand.nextFloat();
			if(this.heat >= this.maxHeat) {
				worldObj.newExplosion(null, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 4, true, true);
				worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.gas_radon_dense);
			}

			if(worldObj.rand.nextFloat() * 2F <= this.heat / (float)this.maxHeat) {
				NBTTagCompound data = new NBTTagCompound();
				data.setString("type", "vanillaExt");
				data.setString("mode", "smoke");
				data.setDouble("mY", 0.05);
				PacketThreading.createAllAroundThreadedPacket(new AuxParticlePacketNT(data, xCoord + 0.25 + worldObj.rand.nextDouble() * 0.5, yCoord + 1, zCoord + 0.25 + worldObj.rand.nextDouble() * 0.5),
						new TargetPoint(worldObj.provider.dimensionId, xCoord + 0.5, yCoord + 1, zCoord + 0.5, 20));
			}

			if(this.progress >= this.maxProgress-500 && canProduce) {
				canProduce = false ;
				if(ra < 0.2){
				ItemStack out = ra > 0.175 ? new ItemStack(ModItems.nugget_bismuth):new ItemStack(ModItems.nugget_technetium);
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
				}	}}		
		
			if(this.progress >= this.maxProgress) {
				boolean canOutput = true;
				boolean canInput = true;
				there:
				for (int i = -4 ; i <= 4 ; i++){
					for (int j = -4 ; j <= 4 ; j++){
						TileEntity te0 = worldObj.getTileEntity(xCoord + i, yCoord , zCoord + j);
				if(te0 instanceof IInventory) {
					IInventory inv = (IInventory) te0;								
					ItemStack out =new ItemStack(ModItems.pile_rod_pu239);
						for(int k = 0; k < inv.getSizeInventory(); k++) {
	
							int slot = k;
							
							if(!inv.isItemValidForSlot(slot, out))
								continue;
								
							ItemStack target = inv.getStackInSlot(slot);
							
							if(canOutput && InventoryUtil.doesStackDataMatch(out, target) && target.stackSize < Math.min(target.getMaxStackSize(), inv.getInventoryStackLimit())) {
								target.stackSize ++;
								canOutput = false;
								break there;
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
								break there;
							}
						}
						}
				}
			}}
				if(canOutput){
				ForgeDirection dir = ForgeDirection.DOWN;
				EntityItem dust = new EntityItem(worldObj, xCoord + 0.5D + dir.offsetX * 0.75D, yCoord + 0.5D + dir.offsetY * 0.75D, zCoord + 0.5D + dir.offsetZ * 0.75D, new ItemStack(ModItems.pile_rod_pu239));
				dust.motionX = dir.offsetX * 0.25;
				dust.motionY = dir.offsetY * 0.25;
				dust.motionZ = dir.offsetZ * 0.25;
				worldObj.spawnEntityInWorld(dust);
				}
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
						if(stack != null &&stack.getItem()== ModItems.pile_rod_uranium){
							inv.decrStackSize(index, 1);
							worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.block_graphite_fuel ,0, 3);
							canInput = false;
							break newthere;
						}
						else if(stack != null &&stack.getItem()== ModItems.pile_rod_lithium){
							inv.decrStackSize(index, 1);
							worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.block_graphite_lithium ,0, 3);
							canInput = false;
							break newthere;
						}
					}

				}}}
				if(canInput)	worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.block_graphite_drilled, 0, 3);

			}
		}
		
	}


	private void dissipateHeat() {
		this.heat -= (this.getBlockMetadata() & 4) == 4 ? heat * 0.065 : heat * 0.05; //remove 5% of the stored heat per tick; 6.5% for windscale
	}

	private int react() {

		int reaction = (int) (this.neutrons * (1D - ((double)this.heat / (double)this.maxHeat) * 0.5D)); //max heat reduces reaction by 50% due to thermal expansion

		this.lastNeutrons = this.neutrons;
		this.neutrons = 0;

		int lastProgress = this.progress;

		this.progress += reaction;

		if(reaction <= 0)
			return lastProgress;

		//this.heat += reaction;

		for(int i = 0; i < 12; i++)
			this.castRay(5);

		return lastProgress;
	}

	private void checkRedstone(int lastProgress) {
		int lastLevel = MathHelper.clamp_int((lastProgress * 16) / maxProgress, 0, 15);
		int newLevel = MathHelper.clamp_int((progress * 16) / maxProgress, 0, 15);
		if(lastLevel != newLevel)
			worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, this.getBlockType());
	}

	private void transmute() {

		if((this.getBlockMetadata() & 8) == 8) {
			if(this.progress < this.maxProgress - 1000) //Might be subject to change, but 1000 seems like a good number.
				this.progress = maxProgress - 1000;

			return;
		} else if(this.progress >= maxProgress - 1000) {
			worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, this.getBlockMetadata() | 8, 3);
			return;
		}
	}

	@Override
	public void receiveNeutrons(int n) {
		this.neutrons += n;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.heat = nbt.getInteger("heat");
		this.progress = nbt.getInteger("progress");
		this.neutrons = nbt.getInteger("neutrons");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("heat", this.heat);
		nbt.setInteger("progress", this.progress);
		nbt.setInteger("neutrons", this.neutrons);
	}
}
