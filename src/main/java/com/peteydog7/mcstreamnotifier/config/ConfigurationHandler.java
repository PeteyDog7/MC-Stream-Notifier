package com.peteydog7.mcstreamnotifier.config;

import com.peteydog7.mcstreamnotifier.reference.Config;
import com.peteydog7.mcstreamnotifier.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

public class ConfigurationHandler {

    public static Configuration configuration;

    protected static String primaryColor;
    protected static String secondaryColor;
    protected static boolean followNotification;
    protected static boolean subscribeNotification;
    protected static String twitchChannel;

    public static void init(File configFile){

        if(configuration == null) {
            configuration = new Configuration(configFile);
            loadConfig();
        }

    }

    private static void loadConfig(){

        twitchChannel = configuration.getString(Config.KEY_TWITCH_CHANNEL, CATEGORY_GENERAL, "channel", Config.COMMENT_TWITCH_CHANNEL, Config.LANGKEY_TWITCH_CHANNEL);
        subscribeNotification = configuration.getBoolean(Config.KEY_SUBSCRIBE_NOTIFICATION, CATEGORY_GENERAL, true, Config.COMMENT_SUBSCRIBE_NOTIFICATION, Config.LANGKEY_SUBSCRIBE_NOTIFICATION);
        followNotification = configuration.getBoolean(Config.KEY_FOLLOW_NOTIFICATION, CATEGORY_GENERAL, true, Config.COMMENT_FOLLOW_NOTIFICATION, Config.LANGKEY_FOLLOW_NOTIFICATION);
        primaryColor = configuration.getString(Config.KEY_PRIMARY_COLOR, CATEGORY_GENERAL, "c", Config.COMMENT_PRIMARY_COLOR, Config.LANGKEY_PRIMARY_COLOR);
        secondaryColor = configuration.getString(Config.KEY_SECONDARY_COLOR, CATEGORY_GENERAL, "c", Config.COMMENT_SECONDARY_COLOR, Config.LANGKEY_SECONDARY_COLOR);

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