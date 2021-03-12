package com.loottable.helpers;

import java.net.URLEncoder;

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

    public static JSONArray getMonsterDropTable(int monsterId) {
        String url = osrsBoxApiBase + "/monsters/" + String.valueOf(monsterId);
        Object response = request(url);
        JSONObject castResponse = (JSONObject) response;
        JSONArray dropTable = (JSONArray) castResponse.get("drops");
        return dropTable;
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

    public static int getMonsterId(String monsterName) {
        try {
            monsterName = monsterName.toLowerCase();
            monsterName = monsterName.substring(0, 1).toUpperCase() + monsterName.substring(1);
            String query = URLEncoder.encode("{\"name\":\"" + monsterName + "\", \"duplicate\": false}", "UTF-8");
            String url = osrsBoxApiBase + "/monsters?where=" + query;
            Object response = OsrsBoxApi.request(url);

            JSONObject castResponse = (JSONObject) response;
            JSONArray items = (JSONArray) castResponse.get("_items");

            if (items == null || items.size() == 0) {
                return 0;
            }

            // @Todo have tabs for each WIKI name
            JSONObject firstItem = (JSONObject) items.get(0);
            if (firstItem != null) {
                int monsterId = Integer.valueOf((String) firstItem.get("id"));
                return monsterId;
            }

            return 0;
        } catch (Exception error) {
            return 0;
        }
    }
}
