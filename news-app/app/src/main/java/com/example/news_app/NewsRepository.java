package com.example.news_app;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.example.news_app.MainActivity.NewsQueryTask;
import com.example.news_app.utilities.NetworkUtils;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class NewsRepository {

  private NewsItemDao newsItemDao;
  private LiveData<List<NewsItem>> allNews;

  public NewsRepository(Application application) {
    NewsRoomDatabase database = NewsRoomDatabase.getDatabase(application.getApplicationContext());
    newsItemDao = database.newsItemDao();
    allNews = newsItemDao.loadAllNewsItems();
  }

  public LiveData<List<NewsItem>> getAllNews() {
    return allNews;
  }

  public void insert(List<NewsItem> item) {
    new insertAsyncTask(newsItemDao).execute(item);
  }

  public void clearAll() {
    new clearAllAsyncTask(newsItemDao).execute();
  }

  private static class insertAsyncTask extends AsyncTask<List<NewsItem>, Void, Void> {

    private NewsItemDao asyncDao;

    insertAsyncTask(NewsItemDao dao) {
      asyncDao = dao;
    }

    @Override
    protected Void doInBackground(List<NewsItem>... newsItems) {
      for (List<NewsItem> news : newsItems) {
        asyncDao.insert(news);
      }
      return null;
    }
  }

  private static class clearAllAsyncTask extends AsyncTask<List<NewsItem>, Void, Void> {

    private NewsItemDao asyncDao;

    public clearAllAsyncTask(NewsItemDao asyncDao) {
      this.asyncDao = asyncDao;
    }

    @Override
    protected Void doInBackground(List<NewsItem>... newsItems) {
      asyncDao.clearAll();
      return null;
    }
  }

  public class getNewsTask extends AsyncTask<URL, Void, String> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      clearAll();
    }

    @Override
    protected String doInBackground(URL... urls) {
      URL url = urls[0];
      String results = null;

      try {
        results = NetworkUtils.getResponseFromHttpUrl(url);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return results;
    }

    @Override
    protected void onPostExecute(String s) {
      super.onPostExecute(s);
    }
  }

  public void makeQuery() {
    URL url = NetworkUtils.buildURL();
    new getNewsTask().execute(url);
  }
}
