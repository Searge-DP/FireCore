package com.fireball1725.firecore.block;

import com.fireball1725.firecore.reference.ModInfo;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.StatCollector;

public enum Blocks {
    ;

    private static boolean registered = false;
    public final Block block;
    private final String internalName;
    private final Class<? extends ItemBlock> itemBlockClass;
    private final CreativeTabs creativeTabs;

    Blocks(String internalName, Block block) {
        this (internalName, block, ItemBlock.class, null);
    }

    Blocks(String internalName, Block block, CreativeTabs creativeTabs) {
        this (internalName, block, ItemBlock.class, creativeTabs);
    }

    Blocks(String internalName, Block block, Class<? extends ItemBlock> itemBlockClass) {
        this (internalName, block, itemBlockClass, null);
    }

    Blocks(String internalName, Block block, Class<? extends ItemBlock> itemBlockClass, CreativeTabs creativeTabs) {
        this.internalName = internalName;
        this.block = block;
        this.itemBlockClass = itemBlockClass;
        this.creativeTabs = creativeTabs;
        block.setBlockName(ModInfo.MOD_ID + "." + internalName);
    }

    private void register() {
        GameRegistry.registerBlock(block.setCreativeTab(creativeTabs).setBlockTextureName(ModInfo.MOD_ID + ":" + internalName), itemBlockClass, "tileentity." + internalName);
        //todo: fix ModInfo and Log registered block message...
    }

    public static void registerAll() {
        if (registered)
            return;

        for (Blocks b : Blocks.values())
            b.register();

        registered = true;
    }

    public String getInternalName() {
        return internalName;
    }

    public String getStatName() {
        return StatCollector.translateToLocal(block.getUnlocalizedName().replace("tileentity.", "block."));
    }
}
