package com.example.news_app;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {

  private NewsRepository repository;
  private LiveData<List<NewsItem>> allNews;

  public NewsItemViewModel(Application application) {
    super(application);
    repository = new NewsRepository(application);
    allNews = repository.getAllNews();
  }

  public LiveData<List<NewsItem>> getAllNews() {
    return allNews;
  }

  public void setAllNews(
      LiveData<List<NewsItem>> allNews) {
    this.allNews = allNews;
  }

  public void insert(List<NewsItem> item) {
    repository.insert(item);
  }

  public void clear() {
    repository.clearAll();
  }
}
