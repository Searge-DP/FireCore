package com.fireball1725.firecore.proxy;

import com.fireball1725.firecore.event.EventRenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;

public abstract class CommonProxy implements IProxy {
    @Override
    public void registerBlocks() {

    }

    @Override
    public void registerItems() {

    }

    @Override
    public void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new EventRenderPlayerEvent());
    }

    @Override
    public void registerConfiguration(File configFile) {

    }

    @Override
    public void registerKeyBindings() {

    }

    @Override
    public void registerRenderers() {

    }

    @Override
    public void registerGuis() {

    }

    @Override
    public void registerOverlays() {

    }

    @Override
    public void registerThreads() {

    }

    @Override
    public void registerPacketHandler() {

    }
}
