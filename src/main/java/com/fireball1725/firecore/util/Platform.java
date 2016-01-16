package com.fireball1725.firecore.util;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class Platform {
    public static boolean isServer() {
        return FMLCommonHandler.instance().getEffectiveSide().isServer();
    }

    public static boolean isClient() {
        return FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    public static boolean isSameItem(@Nullable ItemStack itemStack1, @Nullable ItemStack itemStack2) {
        return itemStack1 != null && itemStack2 != null && itemStack1.isItemEqual(itemStack2);
    }
}
