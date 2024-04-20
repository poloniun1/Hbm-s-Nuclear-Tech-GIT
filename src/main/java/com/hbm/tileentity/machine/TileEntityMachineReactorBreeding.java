package com.hbm.tileentity.machine;

import com.hbm.blocks.ModBlocks;
import com.hbm.blocks.machine.ReactorResearch;
import com.hbm.inventory.container.ContainerMachineReactorBreeding;
import com.hbm.inventory.gui.GUIMachineReactorBreeding;
import com.hbm.inventory.recipes.BreederRecipes;
import com.hbm.inventory.recipes.BreederRecipes.BreederRecipe;
import com.hbm.tileentity.IGUIProvider;
import com.hbm.tileentity.TileEntityMachineBase;
import com.hbm.util.CompatEnergyControl;
import com.hbm.tileentity.machine.rbmk.TileEntityRBMKRod;

import api.hbm.tile.IInfoProviderEC;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

@Optional.InterfaceList({@Optional.Interface(iface = "li.cil.oc.api.network.SimpleComponent", modid = "OpenComputers")})
public class TileEntityMachineReactorBreeding extends TileEntityMachineBase implements SimpleComponent, IGUIProvider, IInfoProviderEC {

	public long flux ;
	public double progress;
	public long totalflux = 0;
	private static final int[] slots_io = new int[] { 0, 1 };

	public TileEntityMachineReactorBreeding() {
		super(2);
	}
	
	@Override
	public String getName() {
		return "container.reactorBreeding";
	}

	@Override
	public void updateEntity() {

		if(!worldObj.isRemote) {
			this.flux = 0;
			getInteractions();
			this.totalflux += this.flux;
			if(canProcess()) {
				
			progress = 0.0025D * (this.totalflux/ BreederRecipes.getOutput(slots[0]).flux);
				
				if(this.progress >= 1.0D) {
					this.totalflux = this.totalflux - BreederRecipes.getOutput(slots[0]).flux * 400;
					this.progress -= 1.0D;
					this.processItem();
					this.markDirty();
					
				}
			}
			else progress = 0D;
						
			NBTTagCompound data = new NBTTagCompound();
			data.setLong("flux", flux);
			data.setLong("totalflux", totalflux);
			data.setDouble("progress", progress);
			this.networkPack(data, 20);
		}
	}
	
	public void networkUnpack(NBTTagCompound data) {
		super.networkUnpack(data);
		totalflux = data.getLong("totalflux");		
		flux = data.getLong("flux");
		progress = data.getDouble("progress");
	}
	
	public void getInteractions() {
		
		for(byte d = 2; d < 6; d++) {
			ForgeDirection dir = ForgeDirection.getOrientation(d);
			Block b = worldObj.getBlock(xCoord + dir.offsetX, yCoord, zCoord + dir.offsetZ);
			
			if(b == ModBlocks.reactor_research) {

				int[] pos = ((ReactorResearch) ModBlocks.reactor_research).findCore(worldObj, xCoord + dir.offsetX, yCoord, zCoord + dir.offsetZ);

				if(pos != null) {
					TileEntity tile = worldObj.getTileEntity(pos[0], pos[1], pos[2]);

					if(tile instanceof TileEntityReactorResearch) {
						TileEntityReactorResearch reactor = (TileEntityReactorResearch) tile;
						this.flux += reactor.totalFlux;
					}
				}
			}


				
			TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord, zCoord + dir.offsetZ);

			if(tile instanceof TileEntityRBMKRod) {
				TileEntityRBMKRod reactor = (TileEntityRBMKRod) tile;
				this.flux = this.flux + (long)(reactor.fluxSlow + reactor.fluxFast * 0.2);
				}
				
			
		}
	}

	public boolean canProcess() {
		
		if(slots[0] == null)
			return false;
		
		BreederRecipe recipe = BreederRecipes.getOutput(slots[0]);
		
		if(recipe == null)
			return false;
		
		if(this.totalflux < recipe.flux)
			return false;

		if(slots[1] == null)
			return true;

		if(!slots[1].isItemEqual(recipe.output))
			return false;

		if(slots[1].stackSize < slots[1].getMaxStackSize())
			return true;
		else
			return false;
	}

	private void processItem() {
		
		if(canProcess()) {
			
			BreederRecipe rec = BreederRecipes.getOutput(slots[0]);
			
			if(rec == null)
				return;
			
			ItemStack itemStack = rec.output;

			if(slots[1] == null) {
				slots[1] = itemStack.copy();
			} else if(slots[1].isItemEqual(itemStack)) {
				slots[1].stackSize += itemStack.stackSize;
			}

			slots[0].stackSize--;
				
			if(slots[0].stackSize <= 0) {
				slots[0] = null;
			}
		}
	}

	

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return slots_io;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemStack) {
		return i == 0;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemStack, int j) {
		return i == 1;
	}

	public int getProgressScaled(int i) {
		return (int) (this.progress * i);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		totalflux = nbt.getLong("totalflux");		
		flux = nbt.getLong("flux");
		progress = nbt.getDouble("progress");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setLong("totalflux",totalflux);		
		nbt.setLong("flux", flux);
		nbt.setDouble("progress", progress);
	}
	
	AxisAlignedBB bb = null;
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		
		if(bb == null) {
			bb = AxisAlignedBB.getBoundingBox(
					xCoord,
					yCoord,
					zCoord,
					xCoord + 1,
					yCoord + 3,
					zCoord + 1
					);
		}
		
		return bb;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}
	
	// do some opencomputer stuff
	@Override
	public String getComponentName() {
		return "breeding_reactor";
	}

	@Callback
	@Optional.Method(modid = "OpenComputers")
	public Object[] getFlux(Context context, Arguments args) {
		return new Object[] {flux};
	}

	@Callback
	@Optional.Method(modid = "OpenComputers")
	public Object[] getProgress(Context context, Arguments args) {
		return new Object[] {progress};
	}

	@Callback
	@Optional.Method(modid = "OpenComputers")
	public Object[] getInfo(Context context, Arguments args) {
		return new Object[] {flux, progress};
	}

	@Override
	public Container provideContainer(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerMachineReactorBreeding(player.inventory, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiScreen provideGUI(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GUIMachineReactorBreeding(player.inventory, this);
	}

	@Override
	public void provideExtraInfo(NBTTagCompound data) {
		data.setLong(CompatEnergyControl.I_FLUX, flux);
	}
}
