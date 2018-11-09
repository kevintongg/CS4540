package com.example.news_app.utilities;import android.net.Uri;import java.io.IOException;import java.io.InputStream;import java.net.HttpURLConnection;import java.net.MalformedURLException;import java.net.URL;import java.util.Scanner;public class NetworkUtils {  private final static String BASE_URL = "https://newsapi.org/v1/articles?source=the-verge&sortBy=latest&apiKey=9a160bc43a444927ba23f43d25b2f709";  public static URL buildURL() {    Uri uri = Uri.parse(BASE_URL).buildUpon().build();    URL url = null;    try {      url = new URL(uri.toString());    } catch (MalformedURLException e) {      e.printStackTrace();    }    return url;  }  /**   * This method returns the entire result from the HTTP response.   *   * @param url The URL to fetch the HTTP response from.   * @return The contents of the HTTP response.   * @throws IOException Related to network and stream reading   */  public static String getResponseFromHttpUrl(URL url) throws IOException {    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();    try {      InputStream in = urlConnection.getInputStream();      Scanner scanner = new Scanner(in);      scanner.useDelimiter("\\A");      boolean hasInput = scanner.hasNext();      if (hasInput) {        return scanner.next();      } else {        return null;      }    } finally {      urlConnection.disconnect();    }  }}