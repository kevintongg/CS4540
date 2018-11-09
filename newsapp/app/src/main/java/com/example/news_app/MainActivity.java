package com.example.news_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  private ArrayList<NewsItem> newsItems = new ArrayList<>();
  private NewsRecyclerViewAdapter recyclerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView recyclerView = findViewById(R.id.news_recyclerview);

    recyclerAdapter = new NewsRecyclerViewAdapter(newsItems, this);
    recyclerView.setAdapter(recyclerAdapter);
    recyclerView.setLayoutManager(layoutManager);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int clickedItemId = item.getItemId();
    if (clickedItemId == R.id.get_news) {
      displayNews();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void displayNews() {
    URL url = NetworkUtils.buildURL();
    new NewsQueryTask().execute(url);
  }

  public class NewsQueryTask extends AsyncTask<URL, Void, String> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
    }

    @Override
    protected String doInBackground(URL... urls) {
      URL url = urls[0];
      String data = null;
      try {
        data = NetworkUtils.getResponseFromHttpUrl(url);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return data;
    }

    @Override
    protected void onPostExecute(String result) {
      super.onPostExecute(result);
      newsItems = JsonUtils.parseNews(result);
      recyclerAdapter.newsList.addAll(newsItems);
      recyclerAdapter.notifyDataSetChanged();
    }
  }
}