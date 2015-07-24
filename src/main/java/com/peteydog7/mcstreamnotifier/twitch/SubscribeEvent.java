/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

package com.peteydog7.mcstreamnotifier.twitch;

import com.peteydog7.mcstreamnotifier.reference.Config;
import com.peteydog7.mcstreamnotifier.reference.Twitch;
import com.peteydog7.mcstreamnotifier.util.LogHelper;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SubscribeEvent {

    public static List<String> subscribeNotificationQueue = new ArrayList<String>();
    public static List<String> existingSubscribers = new ArrayList<String>();

    public static String getLatestSubscriber() {

        String result = null;

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("direction", "DESC"));
        urlParameters.add(new BasicNameValuePair("limit", "10"));
        urlParameters.add(new BasicNameValuePair("offset", "0"));

        try {
            result = Http.sendApiGet(String.format(Twitch.FOLLOW_PATH, Config.Value.TWITCH_CHANNEL), urlParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!(result == null)) {

            JSONObject jsonObject = new JSONObject(result);
            String latest = jsonObject.getJSONArray("follows").getJSONObject(0).getJSONObject("user").getString("name");

            LogHelper.info("Latest Follower: " + latest);

            return latest;

        } else {

            return result;

        }

    }

    public static void checkExistingSubscribers() {

        String result = null;
        List<String> latest = new ArrayList<String>();

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("direction", "DESC"));
        urlParameters.add(new BasicNameValuePair("limit", "50"));
        urlParameters.add(new BasicNameValuePair("offset", "0"));

        try {
            result = Http.sendApiGet(String.format(Twitch.FOLLOW_PATH, Config.Value.TWITCH_CHANNEL), urlParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result == null) {
            return;
        }

        JSONObject jsonObject = new JSONObject(result);

        for (int i = 0; i < 50; i++) {
            String current = jsonObject.getJSONArray("follows").getJSONObject(i).getJSONObject("user").getString("name");
            latest.add(current);
        }

        for (String current : latest) {

        }

        existingSubscribers.addAll(latest);

    }

    public static void checkRecentSubscriptions() {

        String result = null;

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("direction", "DESC"));
        urlParameters.add(new BasicNameValuePair("limit", "10"));
        urlParameters.add(new BasicNameValuePair("offset", "0"));

        try {
            result = Http.sendApiGet(String.format(Twitch.FOLLOW_PATH, Config.Value.TWITCH_CHANNEL), urlParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result == null) {
            return;
        }

        JSONObject jsonObject = new JSONObject(result);

        for (int i = 0; i < 10; i++) {

            String current = jsonObject.getJSONArray("follows").getJSONObject(i).getJSONObject("user").getString("name");

            if (!existingSubscribers.contains(current)) {

                if (Config.Value.FOLLOW_NOTIFICATION) {

                    subscribeNotificationQueue.add(current);

                }

                existingSubscribers.add(current);

            } else return;

        }

        String nextLink = jsonObject.getJSONObject("_links").getString("next");

        for (int i = 0; i < 9; i++) {

            try {
                result = Http.sendGet(nextLink, true);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!(result == null)) {

                jsonObject = new JSONObject(result);

                for (int ii = 0; ii < 10; ii++) {

                    String current = jsonObject.getJSONArray("follows").getJSONObject(ii).getJSONObject("user").getString("name");

                    if (!existingSubscribers.contains(current)) {

                        if (Config.Value.FOLLOW_NOTIFICATION) {

                            subscribeNotificationQueue.add(current);

                        }

                        existingSubscribers.add(current);

                    } else return;

                }

                nextLink = jsonObject.getJSONObject("_links").getString("next");
            }

        }

    }


}
