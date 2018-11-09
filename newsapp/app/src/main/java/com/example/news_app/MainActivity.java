package com.example.news_app;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.example.news_app.utilities.JsonUtils;
import com.example.news_app.utilities.NetworkUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

  private ArrayList<NewsItem> news = new ArrayList<>();
  private NewsRecyclerViewAdapter adapter;

  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView recyclerView = findViewById(R.id.news_recyclerview);

    adapter = new NewsRecyclerViewAdapter(news, this);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(layoutManager);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int selectedItemId = item.getItemId();
    if (selectedItemId == R.id.get_news) {
      displayNews();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void displayNews() {
    URL url = NetworkUtils.buildURL();
    NewsQueryTask task = new NewsQueryTask();
    task.execute(url);
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
      news = JsonUtils.parseNews(result);
      adapter.news.addAll(news);
      adapter.notifyDataSetChanged();
    }
  }
}