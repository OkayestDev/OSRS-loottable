package com.loottable.helpers;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class OsrsBoxApi {
    private static String osrsBoxApiBase = "https://api.osrsbox.com/";

    public static JSONArray getMonsterDropTable(int monsterId) {
        String url = osrsBoxApiBase + "/monsters/" + String.valueOf(monsterId);
        Object response = OsrsBoxApi.request(url);

        JSONObject monsterInfo = (JSONObject) response;

        JSONArray dropTable = (JSONArray) monsterInfo.get("drops");
        return dropTable;
    }

    public static Object request(String url) {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            JSONParser parser = new JSONParser();
            Object resultObject = parser.parse(json);
            return resultObject;
        } catch (Exception error) {
            return null;
        }
    }
}
