/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

package com.peteydog7.mcstreamnotifier.command;

import com.peteydog7.mcstreamnotifier.reference.Twitch;
import com.peteydog7.mcstreamnotifier.server.CallbackServer;
import com.peteydog7.mcstreamnotifier.util.ChatComponents;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

import net.minecraft.util.EnumChatFormatting;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class AuthCommand implements ICommand {

    private List aliases;
    public AuthCommand(){
        this.aliases = new ArrayList();
        this.aliases.add("authorize");
        this.aliases.add("auth");
    }

    @Override
    public String getCommandName() {
        return "authorize";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "authorize";
    }

    @Override
    public List getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        p_71515_1_.addChatMessage(ChatComponents.format(EnumChatFormatting.GRAY, "Authorizing..."));
        CallbackServer callbackServer = new CallbackServer(p_71515_1_);
        if (!openWebpage(URI.create("https://api.twitch.tv/kraken/oauth2/authorize?response_type=token&client_id=" + Twitch.CLIENT_ID + "&redirect_uri=" + Twitch.REDIRECT_URI + "&scope=" + Twitch.SCOPES))) {
            p_71515_1_.addChatMessage(ChatComponents.format(EnumChatFormatting.LIGHT_PURPLE, "Please click the following link to authorize this mod using your Twitch account."));
            p_71515_1_.addChatMessage(ChatComponents.newChatWithLinks("https://api.twitch.tv/kraken/oauth2/authorize?response_type=token&client_id=" + Twitch.CLIENT_ID + "&redirect_uri=" + Twitch.REDIRECT_URI + "&scope=" + Twitch.SCOPES));
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
