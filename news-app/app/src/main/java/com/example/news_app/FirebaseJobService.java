package com.example.news_app;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.AsyncTask;

public class FirebaseJobService extends JobService {

  private AsyncTask asyncTask;
  NewsRepository newsRepository;

  @Override
  public boolean onStartJob(JobParameters params) {
    asyncTask = new AsyncTask() {
      @Override
      protected Object doInBackground(Object[] objects) {
        Context context = FirebaseJobService.this;
        return false;
      }
    };
    return false;
  }

  @Override
  public boolean onStopJob(JobParameters params) {
    return false;
  }
}
