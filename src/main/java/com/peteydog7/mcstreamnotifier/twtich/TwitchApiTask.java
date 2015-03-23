/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

package com.peteydog7.mcstreamnotifier.twtich;

import com.peteydog7.mcstreamnotifier.reference.Config;

import java.util.TimerTask;

public class TwitchApiTask extends TimerTask {

    @Override
    public void run() {

            if (Config.Value.FOLLOW_NOTIFICATION) {

                TwitchAPI.getLatestFollower();
                TwitchAPI.getRecentFollowers();

            }

    }
}
