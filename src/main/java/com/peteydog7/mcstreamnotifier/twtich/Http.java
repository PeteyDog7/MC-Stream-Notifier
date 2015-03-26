/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit 'http://www.gnu.org/licenses/gpl-3.0.txt'
 * for more information.
 */

package com.peteydog7.mcstreamnotifier.twtich;

import com.peteydog7.mcstreamnotifier.reference.Twitch;
import com.peteydog7.mcstreamnotifier.util.LogHelper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

public class Http {

    private static final String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    public static String sendApiGet(String urlPath, List<NameValuePair> urlParameters) throws Exception {

        URIBuilder uriBuilder = new URIBuilder();

        uriBuilder.setScheme("https");
        uriBuilder.setHost(Twitch.API_BASE);
        uriBuilder.setPath(urlPath);
        uriBuilder.setParameters(urlParameters);

        URI url = uriBuilder.build();

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);

        HttpResponse response = client.execute(request);

        LogHelper.info("Sending 'GET' request to URL : " + url);
        LogHelper.info("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();

    }

    public static String sendGet(String url) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);

        HttpResponse response = client.execute(request);

        LogHelper.info("Sending 'GET' request to URL : " + url);
        LogHelper.info("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();

    }

}
