package com.example.news_app;

import java.net.URL;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class NetworkUnitTest {

  public static final String TAG = "NetworkUnitTest";

  @Test
  public void networkCallReturnsUsableJSON() throws Exception {
    URL url = new URL(
        "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=9a160bc43a444927ba23f43d25b2f709");
    String result = NetworkUtils.getResponseFromHttpUrl(url);
    System.out.println(result);
    assert (result.contains("\"status\":\"ok\""));
  }
}