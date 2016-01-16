package com.fireball1725.firecore;

import com.fireball1725.firecore.proxy.IProxy;
import com.fireball1725.firecore.reference.ModInfo;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, certificateFingerprint = ModInfo.FINGERPRINT, dependencies = ModInfo.DEPENDENCIES, version = ModInfo.VERSION_BUILD)
public class FireCoreMod {
    @Mod.Instance
    public static FireCoreMod instance;

    @SidedProxy(clientSide = ModInfo.CLIENT_PROXY_CLASS, serverSide = ModInfo.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    private FireCore fireCore;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        fireCore = new FireCore(ModInfo.MOD_ID, proxy);
        fireCore.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        fireCore.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        fireCore.postInit(event);
    }
}
