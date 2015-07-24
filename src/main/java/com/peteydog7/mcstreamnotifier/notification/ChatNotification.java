/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

package com.peteydog7.mcstreamnotifier.notification;

import com.peteydog7.mcstreamnotifier.reference.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ChatNotification {

    public static EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

    public static String PRIMARY_COLOR_CODE = "\u00a7" + Config.Value.PRIMARY_COLOR;
    public static String SECONDARY_COLOR_CODE = "\u00a7" + Config.Value.SECONDARY_COLOR;

    public static String CHAT_PREFIX = EnumChatFormatting.DARK_GRAY + "[" + EnumChatFormatting.DARK_PURPLE + "StreamNotify" + EnumChatFormatting.DARK_GRAY + "] ";

    public static void followNotification(String username) {
        player.addChatComponentMessage(new ChatComponentText(CHAT_PREFIX + PRIMARY_COLOR_CODE + "Thank you " + SECONDARY_COLOR_CODE + username + EnumChatFormatting.RESET + PRIMARY_COLOR_CODE + " for " + PRIMARY_COLOR_CODE + "following " + PRIMARY_COLOR_CODE + "the " + PRIMARY_COLOR_CODE + "channel!"));
    }

    public static void subscriberNotification(String username) {
        player.addChatComponentMessage(new ChatComponentText(CHAT_PREFIX + PRIMARY_COLOR_CODE + "Thank you " + SECONDARY_COLOR_CODE + username + EnumChatFormatting.RESET + PRIMARY_COLOR_CODE + " for " + PRIMARY_COLOR_CODE + "subscribing " + PRIMARY_COLOR_CODE + "to " + PRIMARY_COLOR_CODE + "the " + PRIMARY_COLOR_CODE + "channel!"));
    }

    public static void followInfo() {
        player.addChatComponentMessage(new ChatComponentText(CHAT_PREFIX + PRIMARY_COLOR_CODE + "Follow " + PRIMARY_COLOR_CODE + "the " + PRIMARY_COLOR_CODE + "channel " + PRIMARY_COLOR_CODE + "to " + PRIMARY_COLOR_CODE + "get " + PRIMARY_COLOR_CODE + "notifications " + PRIMARY_COLOR_CODE + "when " + PRIMARY_COLOR_CODE + "I " + PRIMARY_COLOR_CODE + "go " + PRIMARY_COLOR_CODE + "live!"));
    }

    public static void subscribeInfo() {
        player.addChatComponentMessage(new ChatComponentText(CHAT_PREFIX + PRIMARY_COLOR_CODE + "Subscribe " + PRIMARY_COLOR_CODE + "to " + PRIMARY_COLOR_CODE + "the " + PRIMARY_COLOR_CODE + "channel " + PRIMARY_COLOR_CODE + "to " + PRIMARY_COLOR_CODE + "support " + PRIMARY_COLOR_CODE + "me " + PRIMARY_COLOR_CODE + "and " + PRIMARY_COLOR_CODE + "receive " + PRIMARY_COLOR_CODE + "awesome " + PRIMARY_COLOR_CODE + "perks!"));
    }

    public static void modInfo() {
        player.addChatComponentMessage(new ChatComponentText(CHAT_PREFIX + PRIMARY_COLOR_CODE + "Notifications " + PRIMARY_COLOR_CODE + "brought " + PRIMARY_COLOR_CODE + "to " + PRIMARY_COLOR_CODE + "you " + PRIMARY_COLOR_CODE + "by " + PRIMARY_COLOR_CODE + "MCStreamNotifier!"));
    }


}
