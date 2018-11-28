package com.example.news_app;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class UpdateIntentService extends IntentService {

  public UpdateIntentService(String name) {
    super(name);
  }

  @Override
  protected void onHandleIntent(@Nullable Intent intent) {
    String intentAction = intent.getAction();
    NewsUpdate.executeTask(this, intentAction);
  }
}
