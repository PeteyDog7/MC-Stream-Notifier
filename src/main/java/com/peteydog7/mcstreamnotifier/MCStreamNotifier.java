package com.peteydog7.mcstreamnotifier;

import com.peteydog7.mcstreamnotifier.config.ConfigurationHandler;
import com.peteydog7.mcstreamnotifier.proxy.IProxy;
import com.peteydog7.mcstreamnotifier.reference.Reference;
import com.peteydog7.mcstreamnotifier.twtich.Http;
import com.peteydog7.mcstreamnotifier.util.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        LogHelper.info("Pre Initialization Event Complete!");

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        LogHelper.info("Initialization Event Complete!");

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("direction", "DESC"));
        urlParameters.add(new BasicNameValuePair("limit", "10"));
        urlParameters.add(new BasicNameValuePair("offset", "0"));
        
        try {
            LogHelper.info(Http.sendGet("channels/giantwaffle/follows", urlParameters));
        } catch (Exception e) {
            e.printStackTrace();
        }


        LogHelper.info("Post Initialization Event Complete!");

    }

}
