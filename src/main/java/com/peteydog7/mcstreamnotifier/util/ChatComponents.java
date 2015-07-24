/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

package com.peteydog7.mcstreamnotifier.util;

import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

import java.util.regex.Pattern;

public final class ChatComponents {

    public static IChatComponent newChatWithLinks(String string) {
        final Pattern pattern = Pattern.compile("((?:(?:https?)://)?(?:[-\\w_\\.]{2,}\\.[a-z]{2,4}.*?(?=[\\.\\?!,;:]?(?:[\u00A7 \\n]|$))))",
                Pattern.CASE_INSENSITIVE);
        IChatComponent ichat = new ChatComponentText("");
        // Go through the words, looking for a url
        String[] array = string.split(" ");
        for (int i = 0; i < array.length; i++) {
            IChatComponent link = new ChatComponentText(array[i]);
            if (pattern.matcher(array[i]).matches()) {
                String url = array[i].toLowerCase();
                // Add schema so client doesn't crash.
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                ClickEvent click = new ClickEvent(ClickEvent.Action.OPEN_URL, url);
                ichat.getChatStyle().setChatClickEvent(click);
            }
            // Append this word.
            ichat.appendSibling(link);
        }
        return ichat;
    }

    public static ChatComponentTranslation format(EnumChatFormatting color, String str, Object... args) {
        ChatComponentTranslation ret = new ChatComponentTranslation(str, args);
        ret.getChatStyle().setColor(color);
        return ret;
    }

}