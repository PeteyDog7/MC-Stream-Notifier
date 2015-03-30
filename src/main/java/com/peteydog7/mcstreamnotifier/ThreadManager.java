/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

package com.peteydog7.mcstreamnotifier;

import com.peteydog7.mcstreamnotifier.notification.NotifierThread;
import com.peteydog7.mcstreamnotifier.twitch.FollowEvent;
import com.peteydog7.mcstreamnotifier.twitch.TwitchApiThread;
import com.peteydog7.mcstreamnotifier.util.LogHelper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadManager {

    public static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(16);
    public static int iteration;

    public static void init(){

        LogHelper.info("Thread Manager INIT");
        iteration=0;

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

        scheduler.scheduleAtFixedRate(new TwitchApiThread(), 0, 60, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(new NotifierThread(), 0, 15, TimeUnit.SECONDS);
    }

}
