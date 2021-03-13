package com.loottable.helpers;

import java.net.URLEncoder;
import java.util.Iterator;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Request.Builder;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class OsrsBoxApi {
    private static String osrsBoxApiBase = "https://api.osrsbox.com/";
    public static OkHttpClient client = new OkHttpClient();
    public static Gson gson = new Gson();

    public static JSONArray getMonsterDropTableById(int monsterId) {
        try {
            String url = osrsBoxApiBase + "/monsters/" + String.valueOf(monsterId);
            Object response = request(url);
            JSONObject castResponse = (JSONObject) response;
            JSONArray dropTable = (JSONArray) castResponse.get("drops");
            return dropTable;
        } catch (Exception error) {
            return null;
        }
    }

    public static Object request(String url) {
        Builder requestBuilder = new Builder().url(url);
        Request request = requestBuilder.build();
        try (Response response = client.newCall(request).execute()) {
            JSONParser parser = new JSONParser();
            Object responseObject = parser.parse(response.body().string());
            return responseObject;
        } catch (Exception error) {
            return null;
        }
    }

    /**
     * Potentially returns multiple drop tables (different lvls/zones)
     * @param monsterName
     */
    public static JSONArray getOSRSBoxItemsByName(String monsterName) {
        try {
            monsterName = monsterName.toLowerCase();
            String query = "{ \"$text\" : { \"$search\": \"" + monsterName + "\" } }";
            String url = osrsBoxApiBase + "/monsters?where=" + query;
            Object response = OsrsBoxApi.request(url);

            JSONObject castResponse = (JSONObject) response;
            JSONArray items = (JSONArray) castResponse.get("_items");

            // Filter down where name includes monster name
            Iterator<JSONObject> itemsIter = items.iterator();

            while (itemsIter.hasNext()) {
                JSONObject currentItems = itemsIter.next();
                String currentMonsterName = (String) currentItems.get("name");
                if (!currentMonsterName.toLowerCase().contains(monsterName)) {
                    itemsIter.remove();
                }
            }

            return items;
        } catch (Exception error) {
            return null;
        }
    }
}
