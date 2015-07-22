/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

package com.peteydog7.mcstreamnotifier.reference;

import cpw.mods.fml.client.config.GuiConfigEntries;
import net.minecraft.client.resources.I18n;

public class Config {

    public static final String GUI_TITLE = Reference.MOD_NAME + " Settings";
    public static final String CATEGORY_LANGKEY = "mcstreamnotifier.configgui.ctgy";

    public static final Class<? extends GuiConfigEntries.IConfigEntry> CLASS_BOOLEAN = GuiConfigEntries.BooleanEntry.class;
    public static final Class<? extends GuiConfigEntries.IConfigEntry> CLASS_CHAT_COLOR = GuiConfigEntries.ChatColorEntry.class;

    /////////////////////////////////////////////////////////////////////////

    public static final String CATEGORY_NOTIFICATIONS = "notifications";
    public static final String CATEGORY_NOTIFICATIONS_LANGKEY = "mcstreamnotifier.configgui.ctgy.notification";

    //------

    public static final String KEY_PRIMARY_COLOR = "primary_color";
    public static final String LANGKEY_PRIMARY_COLOR = "mcstreamnotifier.configgui.primary_color";
    public static final String COMMENT_PRIMARY_COLOR = "Main color used in notifications. Make sure it is easy to read because it is used most.";

    public static final String KEY_SECONDARY_COLOR = "secondary_color";
    public static final String LANGKEY_SECONDARY_COLOR = "mcstreamnotifier.configgui.secondary_color";
    public static final String COMMENT_SECONDARY_COLOR = "Second color used in notifications. Used to highlight a username or other important words.";

    public static final String KEY_FOLLOW_NOTIFICATION = "follow_notification_enabled";
    public static final String LANGKEY_FOLLOW_NOTIFICATION = "mcstreamnotifier.configgui.follow_notification_enabled";
    public static final String COMMENT_FOLLOW_NOTIFICATION = "Set true to enable notifications for new followers to the channel.";

    public static final String KEY_SUBSCRIBE_NOTIFICATION = "subscribe_notification_enabled";
    public static final String LANGKEY_SUBSCRIBE_NOTIFICATION = "mcstreamnotifier.configgui.subscribe_notification_enabled";
    public static final String COMMENT_SUBSCRIBE_NOTIFICATION = "Set true to enable notifications for new subscribers to the channel. (Twitch partners only)";

    ///////////////////////////////////////////////////////////

    public static final String CATEGORY_TWITCH = "twitch";
    public static final String CATEGORY_TWITCH_LANGKEY = "mcstreamnotifier.configgui.ctgy.twitch";

    //------

    public static final String KEY_TWITCH_CHANNEL = "twitch_channel";
    public static final String LANGKEY_TWITCH_CHANNEL = "mcstreamnotifier.configgui.twitch_channel";
    public static final String COMMENT_TWITCH_CHANNEL = "Enter the name of the Twitch channel that you would like to receive notifications from. Provide it as it is in the URL to your channel. twitch.tv/<channel>";

    public static final String KEY_AUTH_TOKEN = "auth_token";
    public static final String LANGKEY_AUTH_TOKEN = "mcstreamnotifier.configgui.auth_token";
    public static final String COMMENT_AUTH_TOKEN = "Automatically configured by typing '/auth' in-game. Visit https://github.com/PeteyDog7/MCStreamNotifier for details on manually configuring this.";

    //authentication

    public static class Value {

        public static String PRIMARY_COLOR;
        public static String SECONDARY_COLOR;
        public static boolean FOLLOW_NOTIFICATION;
        public static boolean SUBSCRIBE_NOTIFICATION;
        public static String TWITCH_CHANNEL;
        public static String AUTH_TOKEN;

    }

}
