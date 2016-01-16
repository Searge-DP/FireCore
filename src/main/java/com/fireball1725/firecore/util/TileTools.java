package com.fireball1725.firecore.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class TileTools {
    public static <T> T getTileEntity(IBlockAccess world, int x, int y, int z, Class<T> clazz) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        return !clazz.isInstance(tileEntity) ? null : (T) tileEntity;
    }
}
