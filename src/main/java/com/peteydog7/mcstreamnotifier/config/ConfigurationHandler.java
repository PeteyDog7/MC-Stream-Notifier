/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

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
            updateConfigReference();
        }

    }

    protected static String primaryColor;
    protected static String secondaryColor;
    protected static boolean followNotification;
    protected static boolean subscribeNotification;
    protected static String twitchChannel;

    private static void loadConfig(){

        List<String> propOrderNotifications = new ArrayList<String>();
        Property prop;

        prop = configuration.get(Config.CATEGORY_TWITCH, Config.KEY_TWITCH_CHANNEL, "channel");
        prop.comment = Config.COMMENT_TWITCH_CHANNEL;
        prop.setLanguageKey(Config.LANGKEY_TWITCH_CHANNEL);
        twitchChannel = prop.getString();
        //propOrderTwitch.add(prop.getName());

        prop = configuration.get(Config.CATEGORY_NOTIFICATIONS, Config.KEY_SUBSCRIBE_NOTIFICATION, true);
        prop.comment = Config.COMMENT_SUBSCRIBE_NOTIFICATION;
        prop.setLanguageKey(Config.LANGKEY_SUBSCRIBE_NOTIFICATION);
        subscribeNotification = prop.getBoolean(true);
        propOrderNotifications.add(prop.getName());

        prop = configuration.get(Config.CATEGORY_NOTIFICATIONS, Config.KEY_FOLLOW_NOTIFICATION, true);
        prop.comment = Config.COMMENT_FOLLOW_NOTIFICATION;
        prop.setLanguageKey(Config.LANGKEY_FOLLOW_NOTIFICATION);
        followNotification = prop.getBoolean(true);
        propOrderNotifications.add(prop.getName());

        prop = configuration.get(Config.CATEGORY_NOTIFICATIONS, Config.KEY_PRIMARY_COLOR, "c", Config.COMMENT_PRIMARY_COLOR, Property.Type.COLOR);
        prop.setLanguageKey(Config.LANGKEY_PRIMARY_COLOR);
        prop.setValidValues(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"});
        primaryColor = prop.getString();
        propOrderNotifications.add(prop.getName());

        prop = configuration.get(Config.CATEGORY_NOTIFICATIONS, Config.KEY_SECONDARY_COLOR, "c", Config.COMMENT_SECONDARY_COLOR, Property.Type.COLOR);
        prop.setLanguageKey(Config.LANGKEY_SECONDARY_COLOR);
        prop.setValidValues(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"});
        secondaryColor = prop.getString();
        propOrderNotifications.add(prop.getName());

        configuration.setCategoryPropertyOrder(Config.CATEGORY_NOTIFICATIONS, propOrderNotifications);

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