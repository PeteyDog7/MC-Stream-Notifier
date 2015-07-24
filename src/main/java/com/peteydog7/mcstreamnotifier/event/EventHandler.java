/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

package com.peteydog7.mcstreamnotifier.event;

import com.peteydog7.mcstreamnotifier.ThreadManager;
import com.peteydog7.mcstreamnotifier.notification.ChatNotification;
import com.peteydog7.mcstreamnotifier.reference.Config;
import com.peteydog7.mcstreamnotifier.util.ChatComponents;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class EventHandler {

    public static boolean inGame = false;

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {

        inGame = true;

        event.player.addChatMessage(new ChatComponentTranslation(ChatNotification.CHAT_PREFIX + EnumChatFormatting.DARK_PURPLE + "MCStreamNotifier Enabled!"));

        if (Config.Value.TWITCH_CHANNEL == "channel" && Config.Value.FOLLOW_NOTIFICATION) {

            event.player.addChatMessage(ChatComponents.format(EnumChatFormatting.YELLOW, "You have follow notifications enabled but do not have your twitch channel configured. Please configure your twitch channel or disabled follow notifications."));

        }

        ThreadManager.init();

        if (Config.Value.AUTH_TOKEN == "none" && Config.Value.SUBSCRIBE_NOTIFICATION) {
            event.player.addChatMessage(ChatComponents.format(EnumChatFormatting.YELLOW, "You have subscription notifications on but have not authenticated your twitch account with this mod. Please type '/auth' to authorize MCStreamNotifier to use your account or disable subscription notifications in the config."));
        }

    }

    @SubscribeEvent
    public void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        inGame = false;
    }

}
