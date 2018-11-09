package com.example.news_app;

import com.example.news_app.utilities.NetworkUtils;
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
        "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=027ae063a4b145d9a7dbf456b328d889");
    String result = NetworkUtils.getResponseFromHttpUrl(url);
    System.out.println(result);
    assert (result.contains("\"status\":\"ok\""));
  }
}