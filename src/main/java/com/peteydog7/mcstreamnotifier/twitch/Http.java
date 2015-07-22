/*
 * MC Stream Notifier  Copyright (C) 2015  PeteyDog7
 * This program comes with ABSOLUTELY NO WARRANTY. This is free software,
 * and you are welcome to redistribute it under certain conditions.
 * View the included license or visit http://www.gnu.org/licenses/gpl-3.0.txt
 * for more information.
 */

package com.peteydog7.mcstreamnotifier.twitch;

import com.peteydog7.mcstreamnotifier.ThreadManager;
import com.peteydog7.mcstreamnotifier.reference.Config;
import com.peteydog7.mcstreamnotifier.reference.Twitch;
import com.peteydog7.mcstreamnotifier.util.LogHelper;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.lwjgl.Sys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Http {

    private static final String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    public static String sendApiGet(String urlPath, List<NameValuePair> urlParameters) throws Exception {

        if (Config.Value.AUTH_TOKEN!="none") {
            urlParameters.add(new BasicNameValuePair("oauth_token", Config.Value.AUTH_TOKEN));
        }

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

        request.addHeader("client_id",Twitch.CLIENT_ID);
        request.addHeader("Accept",Twitch.API_VERSION);

        HttpResponse response = client.execute(request);

        LogHelper.info("Sending 'GET' request to URL : " + url);
        LogHelper.info("Response Code : " + response.getStatusLine().getStatusCode());

        if (response.getStatusLine().getStatusCode()!=200) {
            ThreadManager.restartThreadTwitch();
            return null;
        }

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();

    }

    public static String sendGet(String url, boolean apiCall) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);

        if (apiCall) {
            request.addHeader("client_id",Twitch.CLIENT_ID);
            request.addHeader("Accept",Twitch.API_VERSION);
        }

        HttpResponse response = client.execute(request);

        LogHelper.info("Sending 'GET' request to URL : " + url);
        LogHelper.info("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();

    }

    public static void _sendPost() throws Exception {

        String url = "https://api.twitch.tv/kraken/oauth2/login";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("login_type", "login"));
        urlParameters.add(new BasicNameValuePair("client_id", "8pc75if65zzg5fqu7wvxktb80sn493h"));
        urlParameters.add(new BasicNameValuePair("redirect_uri", "http://localhost"));
        urlParameters.add(new BasicNameValuePair("response_type", "token"));
        urlParameters.add(new BasicNameValuePair("scope", "channel_subscriptions"));
        urlParameters.add(new BasicNameValuePair("login", "MCStreamNotifier"));
        urlParameters.add(new BasicNameValuePair("password", "MCStreamNotifier"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        LogHelper.info("Sending 'POST' request to URL : " + url);
        //LogHelper.info("Post parameters : " + post.getEntity());
        LogHelper.info("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        LogHelper.info(result.toString());

    }

    public static void sendPost() throws Exception {

        String url = "https://api.twitch.tv/kraken/oauth2/login";

        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        HttpContext context = new BasicHttpContext();

        // add header
        httpPost.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("login_type", "login"));
        urlParameters.add(new BasicNameValuePair("client_id", "8pc75if65zzg5fqu7wvxktb80sn493h"));
        urlParameters.add(new BasicNameValuePair("redirect_uri", "http://localhost"));
        urlParameters.add(new BasicNameValuePair("response_type", "token"));
        urlParameters.add(new BasicNameValuePair("scope", "channel_subscriptions"));
        urlParameters.add(new BasicNameValuePair("login", "MCStreamNotifier"));
        urlParameters.add(new BasicNameValuePair("password", "MCStreamNotifier"));

        httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(httpPost, context);
        String redirectURL = response.getFirstHeader("Location").getValue();
        System.out.println(redirectURL);

    }

}
