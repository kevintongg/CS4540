package com.example.news_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.example.news_app.utilities.JsonUtils;
import com.example.news_app.utilities.NetworkUtils;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  private ArrayList<NewsItem> newsItems = new ArrayList<>();
  private NewsRecyclerViewAdapter viewAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView recyclerView = findViewById(R.id.news_recyclerview);

    viewAdapter = new NewsRecyclerViewAdapter(newsItems, this);
    recyclerView.setAdapter(viewAdapter);
    recyclerView.setLayoutManager(layoutManager);
  }

  public class NewsQueryTask extends AsyncTask<URL, Void, String> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
    }

    @Override
    protected String doInBackground(URL... urls) {
      URL url = urls[0];
      String result = null;
      try {
        result = NetworkUtils.getResponseFromHttpUrl(url);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return result;
    }

    @Override
    protected void onPostExecute(String result) {
      super.onPostExecute(result);
      newsItems = JsonUtils.parseNews(result);
      viewAdapter.newsList.addAll(newsItems);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int clickedItemId = item.getItemId();
    if (clickedItemId == R.id.action_search) {
      displayNews();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void displayNews() {
    URL url = NetworkUtils.buildURL();
    new NewsQueryTask().execute(url);
  }
}