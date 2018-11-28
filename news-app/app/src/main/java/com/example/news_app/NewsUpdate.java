package com.example.news_app;

import android.content.Context;
import com.example.news_app.utilities.NotificationUtils;

public class NewsUpdate {

  private static final String ACTION_UPDATE_NEWS = "update";
  public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";

  protected static void executeTask(Context context, String task) {
    if (ACTION_DISMISS_NOTIFICATION.equals(task)) {
      NotificationUtils.clearNotifications(context);
    } else if (ACTION_UPDATE_NEWS.equals(task)) {
      NewsUpdate update = new NewsUpdate();
      update.update();
    }
  }

  private void update() {

  }
}
