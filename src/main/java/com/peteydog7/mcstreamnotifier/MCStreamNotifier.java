/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

package com.peteydog7.mcstreamnotifier;

import com.peteydog7.mcstreamnotifier.command.AuthCommand;
import com.peteydog7.mcstreamnotifier.config.ConfigurationHandler;
import com.peteydog7.mcstreamnotifier.event.EventHandler;
import com.peteydog7.mcstreamnotifier.proxy.IProxy;
import com.peteydog7.mcstreamnotifier.reference.Reference;
import com.peteydog7.mcstreamnotifier.util.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.client.ClientCommandHandler;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class MCStreamNotifier {

    @Mod.Instance(Reference.MOD_ID)
    public static MCStreamNotifier instance;

    @SidedProxy(clientSide = Reference.PROXY_CLIENT_CLASS, serverSide = Reference.PROXY_SERVER_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        FMLCommonHandler.instance().bus().register(new EventHandler());

        LogHelper.info("Pre Initialization Event Complete!");

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        ClientCommandHandler.instance.registerCommand(new AuthCommand());

        LogHelper.info("Initialization Event Complete!");

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        LogHelper.info("Post Initialization Event Complete!");

    }

}

