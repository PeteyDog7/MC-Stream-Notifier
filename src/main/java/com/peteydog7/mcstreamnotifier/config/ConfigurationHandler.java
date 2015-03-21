package com.peteydog7.mcstreamnotifier.config;

import com.peteydog7.mcstreamnotifier.reference.Config;
import com.peteydog7.mcstreamnotifier.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

public class ConfigurationHandler {

    public static Configuration configuration;

    public static void init(File configFile){

        if(configuration == null) {
            configuration = new Configuration(configFile);
            loadConfig();
        }

    }

    protected static String primaryColor;
    protected static String secondaryColor;
    protected static boolean followNotification;
    protected static boolean subscribeNotification;
    protected static String twitchChannel;

    private static void loadConfig(){

        List<String> propOrder = new ArrayList<String>();
        Property prop;

        prop = configuration.get(CATEGORY_GENERAL, Config.KEY_TWITCH_CHANNEL, "channel");
        prop.comment = Config.COMMENT_TWITCH_CHANNEL;
        prop.setLanguageKey(Config.LANGKEY_TWITCH_CHANNEL);
        twitchChannel = prop.getString();
        propOrder.add(prop.getName());

        prop = configuration.get(CATEGORY_GENERAL, Config.KEY_SUBSCRIBE_NOTIFICATION, true);
        prop.comment = Config.COMMENT_SUBSCRIBE_NOTIFICATION;
        prop.setLanguageKey(Config.LANGKEY_SUBSCRIBE_NOTIFICATION);
        subscribeNotification = prop.getBoolean(true);
        propOrder.add(prop.getName());

        prop = configuration.get(CATEGORY_GENERAL, Config.KEY_FOLLOW_NOTIFICATION, true);
        prop.comment = Config.COMMENT_FOLLOW_NOTIFICATION;
        prop.setLanguageKey(Config.LANGKEY_FOLLOW_NOTIFICATION);
        followNotification = prop.getBoolean(true);
        propOrder.add(prop.getName());

        prop = configuration.get(CATEGORY_GENERAL, Config.KEY_PRIMARY_COLOR, "c");
        prop.comment = Config.COMMENT_PRIMARY_COLOR;
        prop.setLanguageKey(Config.LANGKEY_PRIMARY_COLOR);
        primaryColor = prop.getString();
        propOrder.add(prop.getName());

        prop = configuration.get(CATEGORY_GENERAL, Config.KEY_SECONDARY_COLOR, "c");
        prop.comment = Config.COMMENT_SECONDARY_COLOR;
        prop.setLanguageKey(Config.LANGKEY_SECONDARY_COLOR);
        secondaryColor = prop.getString();
        propOrder.add(prop.getName());

        configuration.setCategoryPropertyOrder(CATEGORY_GENERAL, propOrder);

        if(configuration.hasChanged()){

            configuration.save();
            updateConfigReference();

        }

    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
        if(event.modID.equalsIgnoreCase(Reference.MOD_ID)){
            loadConfig();
        }
    }

    private static void updateConfigReference(){

        Config.Value.PRIMARY_COLOR = primaryColor;
        Config.Value.SECONDARY_COLOR = secondaryColor;
        Config.Value.FOLLOW_NOTIFICATION = followNotification;
        Config.Value.SECONDARY_COLOR = secondaryColor;
        Config.Value.TWITCH_CHANNEL = twitchChannel;

    }

}