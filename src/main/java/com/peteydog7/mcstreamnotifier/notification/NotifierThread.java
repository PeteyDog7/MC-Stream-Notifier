/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

package com.peteydog7.mcstreamnotifier.notification;

import com.peteydog7.mcstreamnotifier.ThreadManager;
import com.peteydog7.mcstreamnotifier.reference.Config;
import com.peteydog7.mcstreamnotifier.twitch.FollowEvent;
import com.peteydog7.mcstreamnotifier.twitch.SubscribeEvent;
import com.peteydog7.mcstreamnotifier.util.LogHelper;

public class NotifierThread implements Runnable {

    @Override
    public void run() {

        LogHelper.info("Iteration: " + ThreadManager.iteration);

        if (ThreadManager.iteration >= 36) ThreadManager.iteration = 1;

        if (ThreadManager.iteration == 36) {
            credit();
            LogHelper.info("credit");
        } else if (ThreadManager.iteration == 24) {
            if (Config.Value.SUBSCRIBE_NOTIFICATION) {
                subscribeInfo();
                LogHelper.info("subInfo");
            } else follow();
        } else if (ThreadManager.iteration == 12) {
            followInfo();
            LogHelper.info("followInfo");
        } else {
            if (!subscribe()) {
                follow();
                LogHelper.info("follow");
            } else {
                LogHelper.info("sub");
            }
        }

        ThreadManager.iteration++;

    }

    public void follow() {

        if (FollowEvent.followNotificationQueue.isEmpty()) {

            LogHelper.info("Follow Notification Queue Empty");
            return;

        }

        String name = FollowEvent.followNotificationQueue.get(0);
        LogHelper.info("Follow Notification: " + name);

        ChatNotification.followNotification(name);

        FollowEvent.followNotificationQueue.remove(name);

    }

    public boolean subscribe() {

        if (SubscribeEvent.subscribeNotificationQueue.isEmpty()) {
            LogHelper.info("Subscription Notification Queue Empty");
            return false;
        }

        String name = SubscribeEvent.subscribeNotificationQueue.get(0);
        LogHelper.info("Subscription Notification: " + name);

        ChatNotification.subscriberNotification(name);

        SubscribeEvent.subscribeNotificationQueue.remove(name);

        return true;

    }

    public void followInfo() {

        ChatNotification.followInfo();

    }

    public void subscribeInfo() {

        ChatNotification.subscribeInfo();

    }

    public void credit() {
        ChatNotification.modInfo();
    }

}
