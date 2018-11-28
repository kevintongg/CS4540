package com.example.news_app;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {NewsItem.class}, version = 1, exportSchema = false)
public abstract class NewsRoomDatabase extends RoomDatabase {

  public abstract NewsItemDao newsItemDao();

  private static volatile NewsRoomDatabase INSTANCE;

  static NewsRoomDatabase getDatabase(final Context CONTEXT) {
    if (INSTANCE == null) {
      synchronized (NewsRoomDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(CONTEXT.getApplicationContext(), NewsRoomDatabase.class,
              "news_database")
              .build();
        }
      }
    }
    return INSTANCE;
  }
}
