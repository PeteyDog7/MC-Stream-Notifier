package com.peteydog7.mcstreamnotifier;

import com.peteydog7.mcstreamnotifier.proxy.IProxy;
import com.peteydog7.mcstreamnotifier.reference.Reference;
import com.peteydog7.mcstreamnotifier.util.LogHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.Mod.MOD_ID, name = Reference.Mod.MOD_NAME, version = Reference.Mod.MOD_VERSION)
public class MCStreamNotifier {

    @Mod.Instance(Reference.Mod.MOD_ID)
    public static MCStreamNotifier instance;

    @SidedProxy(clientSide = Reference.Mod.PROXY_CLIENT_CLASS, serverSide = Reference.Mod.PROXY_SERVER_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        LogHelper.info("Pre Initialization Event Complete!");

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        LogHelper.info("Initialization Event Complete!");

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        LogHelper.info("Post Initialization Event Complete!");

    }

}
