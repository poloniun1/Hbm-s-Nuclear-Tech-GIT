package com.hbm.tileentity.bomb;

import java.util.List;
import java.util.function.BiConsumer;

import com.hbm.blocks.bomb.BlockCrashedBomb.EnumDudType;
import com.hbm.util.ContaminationUtil;
import com.hbm.util.EnumUtil;
import com.hbm.util.ContaminationUtil.ContaminationType;
import com.hbm.util.ContaminationUtil.HazardType;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityCrashedBomb extends TileEntity {
	
	@Override
	public void updateEntity() {
		
		if(!worldObj.isRemote) {

		}
	}

	
	@Override public AxisAlignedBB getRenderBoundingBox() { return TileEntity.INFINITE_EXTENT_AABB; }
	@Override @SideOnly(Side.CLIENT) public double getMaxRenderDistanceSquared() { return 65536.0D; }
}
