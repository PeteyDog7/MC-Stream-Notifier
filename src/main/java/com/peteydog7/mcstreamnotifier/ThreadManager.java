/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

package com.peteydog7.mcstreamnotifier;

import com.peteydog7.mcstreamnotifier.notification.NotifierThread;
import com.peteydog7.mcstreamnotifier.reference.Config;
import com.peteydog7.mcstreamnotifier.twitch.FollowEvent;
import com.peteydog7.mcstreamnotifier.twitch.TwitchApiThread;
import com.peteydog7.mcstreamnotifier.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentTranslation;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ThreadManager {

    public static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(16);
    public static int iteration = 0;
    public static boolean canceled = false;

    public static ScheduledFuture<?> twitchApiThread;
    public static ScheduledFuture<?> notifierThread;

    public static void init(){

        if (Config.Value.TWITCH_CHANNEL=="channel") {
            canceled = true;
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation("Please c"));
            return;
        }

        FollowEvent.checkExistingFollowers();
        LogHelper.info("Existing Followers: " + FollowEvent.existingFollowers);

        /*scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                LogHelper.info("PeteyDog7");
                if (FollowEvent.followNotificationQueue.isEmpty()) {
                    FollowEvent.followNotificationQueue.add("PeteyDog7");
                }
            }
        }, 15, 10, TimeUnit.SECONDS);*/

        twitchApiThread = scheduler.scheduleAtFixedRate(new TwitchApiThread(), 0, 60, TimeUnit.SECONDS);
        notifierThread = scheduler.scheduleAtFixedRate(new NotifierThread(), 0, 15, TimeUnit.SECONDS);
    }

    public static boolean restartThreadTwitch(){

        boolean canceled = twitchApiThread.cancel(false);

        if (canceled){
            twitchApiThread = scheduler.scheduleAtFixedRate(new TwitchApiThread(), 0, 15, TimeUnit.SECONDS);
        }

        return canceled;

    }

}
