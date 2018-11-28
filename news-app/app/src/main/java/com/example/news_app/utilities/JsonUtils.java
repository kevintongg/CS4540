package com.example.news_app.utilities;

import com.example.news_app.NewsItem;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

  public static ArrayList<NewsItem> parseNews(String JSONString) {
    ArrayList<NewsItem> news = new ArrayList<>();

    try {
      JSONObject object = new JSONObject(JSONString);
      JSONArray array = object.getJSONArray("articles");
      int counter = 0;

      for (int i = 0; i < array.length(); i++) {
        JSONObject item = array.getJSONObject(i);

        news.add(new NewsItem(
            counter,
            item.getString("author"),
            item.getString("title"),
            item.getString("description"),
            item.getString("url"),
            item.getString("urlToImage"),
            item.getString("publishedAt")));

        counter++;
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return news;
  }
}


