package com.fireball1725.firecore;

import com.fireball1725.firecore.helpers.LogHelper;
import com.fireball1725.firecore.proxy.IProxy;
import com.google.common.base.Stopwatch;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.util.concurrent.TimeUnit;

public class FireCore {
    private final String MOD_ID;
    private IProxy proxy;
    public LogHelper logHelper;

    public FireCore(String MOD_ID, IProxy proxy) {
        this.MOD_ID = MOD_ID;
        this.proxy = proxy;
    }

    public void preInit(FMLPreInitializationEvent event) {
        logHelper = new LogHelper(MOD_ID);
        final Stopwatch stopwatch = Stopwatch.createStarted();
        logHelper.info("Pre Initialization ( started )");
        proxy.registerPacketHandler();
        proxy.registerBlocks();
        proxy.registerItems();
        proxy.registerKeyBindings();
        proxy.registerGuis();
        proxy.registerConfiguration(event.getSuggestedConfigurationFile());
        logHelper.info("Pre Initialization ( ended after " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms )");
    }

    public void init(FMLInitializationEvent event) {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        logHelper.info("Initialization ( started )");
        proxy.registerRenderers();
        proxy.registerEvents();
        logHelper.info("Initialization ( ended after " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms )");
    }

    public void postInit(FMLPostInitializationEvent event) {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        logHelper.info("Post Initialization ( started )");
        proxy.registerOverlays();
        proxy.registerThreads();
        logHelper.info("Post Initialization ( ended after " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms )");
    }
}
