package com.example.news_app;

import android.app.Application;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    CalligraphyConfig.initDefault(
        new CalligraphyConfig.Builder()
            .setDefaultFontPath("font")
            .setFontAttrId(R.attr.fontPath)
            .build());
  }
}
