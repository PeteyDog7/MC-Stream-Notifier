/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

package com.peteydog7.mcstreamnotifier.twtich;

import com.peteydog7.mcstreamnotifier.util.LogHelper;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TwitchAPI {

    public static String getLatestFollower() {

        String result = null;

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("direction", "DESC"));
        urlParameters.add(new BasicNameValuePair("limit", "10"));
        urlParameters.add(new BasicNameValuePair("offset", "0"));

        try {
            result = Http.sendGet("channels/giantwaffle/follows", urlParameters);
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }

        JSONObject jsonObject = new JSONObject(result);
        String lastest = jsonObject.getJSONArray("follows").getJSONObject(1).getJSONObject("user").getString("name");

        LogHelper.info("Latest Follower: "+lastest);

        return lastest;

    }

    public static String getRecentFollowers() {

        String followers = null;

        return followers;

    }

}
