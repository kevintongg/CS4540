package com.example.news_app.utilities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.ContextCompat;
import com.example.news_app.MainActivity;
import com.example.news_app.NewsUpdate;
import com.example.news_app.R;
import com.example.news_app.UpdateIntentService;

public class NotificationUtils {

  private static final int NEWS_NOTIFICATION_ID = 8;
  private static final int NEWS_PENDING_ID = 23;
  private static final int ACTION_IGNORE_PENDING_INTENT_ID = 14;
  private static final String NEWS_NOTIFICATION_CHANNEL_ID = "news_notification_channel";

  public static void newNews(Context context) {
    NotificationManager manager = (NotificationManager) context
        .getSystemService(Context.NOTIFICATION_SERVICE);
    BigTextStyle bigTextStyle = new BigTextStyle().bigText(
        context.getString(R.string.new_news));

    if (VERSION.SDK_INT >= VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel(NEWS_NOTIFICATION_CHANNEL_ID,
          context.getString(R.string.main_notification), NotificationManager.IMPORTANCE_HIGH);
      manager.createNotificationChannel(channel);
    }

    Builder builder = new Builder(context, NEWS_NOTIFICATION_CHANNEL_ID)
        .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
        .setSmallIcon(R.drawable.thinking)
        .setLargeIcon(largeIcon(context))
        .setContentTitle(context.getString(R.string.new_notification))
        .setContentText("New News")
        .setStyle(bigTextStyle)
        .setDefaults(NotificationCompat.DEFAULT_ALL)
        .setContentIntent(contentIntent(context))
        .addAction(ignoreReminderAction(context))
        .setAutoCancel(true);

    manager.notify(NEWS_NOTIFICATION_ID, builder.build());
  }

  public static void clearNotifications(Context context) {
    NotificationManager manager = (NotificationManager) context
        .getSystemService(Context.NOTIFICATION_SERVICE);
    manager.cancelAll();
  }

  private static Action ignoreReminderAction(Context context) {
    Intent intent = new Intent(context, UpdateIntentService.class);
    intent.setAction(NewsUpdate.ACTION_DISMISS_NOTIFICATION);

    PendingIntent pendingIntent = PendingIntent.getService(
        context,
        ACTION_IGNORE_PENDING_INTENT_ID,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT);
    return new Action(R.drawable.worried, "Bye", pendingIntent);
  }

  private static PendingIntent contentIntent(Context context) {
    Intent startActivityIntent = new Intent(context, MainActivity.class);
    return PendingIntent.getActivity(
        context,
        NEWS_NOTIFICATION_ID,
        startActivityIntent,
        PendingIntent.FLAG_UPDATE_CURRENT);
  }

  private static Bitmap largeIcon(Context context) {
    Resources res = context.getResources();
    Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.thinking);
    return largeIcon;
  }
}
