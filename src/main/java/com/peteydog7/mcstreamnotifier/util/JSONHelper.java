package com.peteydog7.mcstreamnotifier.util;

import org.json.JSONObject;

public class JSONHelper {

    public static JSONObject getJSONObject(String string){

        JSONObject jsonObject = new JSONObject(string);

        return jsonObject;

    }

}
