package com.fireball1725.firecore.proxy;

import java.io.File;

public interface IProxy {
    void registerBlocks();
    void registerItems();
    void registerEvents();
    void registerConfiguration(File configFile);
    void registerKeyBindings();
    void registerRenderers();
    void registerGuis();
    void registerOverlays();
    void registerThreads();
    void registerPacketHandler();
}
