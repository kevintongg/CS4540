package com.example.news_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class NewsRecyclerViewAdapter extends
    RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

  ArrayList<NewsItem> newsList;
  private Context context;

  NewsRecyclerViewAdapter(ArrayList<NewsItem> newsList, Context context) {
    this.newsList = newsList;
    this.context = context;
  }

  @NonNull
  @Override
  public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
      int viewType) {
    Context createContext = viewGroup.getContext();
    int layoutId = R.layout.news_item;
    LayoutInflater inflater = LayoutInflater.from(createContext);
    boolean shouldAttachToParentImmediately = false;

    View view = inflater.inflate(layoutId, viewGroup, shouldAttachToParentImmediately);

    return new NewsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull NewsViewHolder viewHolder, int position) {
    viewHolder.bind(position);
  }

  @Override
  public int getItemCount() {
    return newsList.size();
  }

  class NewsViewHolder extends ViewHolder {

    public TextView title;
    public TextView description;
    public TextView date;

    NewsViewHolder(View itemView) {
      super(itemView);

      title = itemView.findViewById(R.id.news_title);
      description = itemView.findViewById(R.id.news_description);
      date = itemView.findViewById(R.id.news_date);
    }

    void bind(int index) {
      final String URL = newsList.get(index).getUrl();

      title.setText(String.format("Title\n%s", newsList.get(index).getTitle()));
      description.setText(String.format("Description\n%s", newsList.get(index).getDescription()));
      date.setText(String.format("Date\n%s", newsList.get(index).getPublishedAt()));

      itemView.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = new Intent(Intent.ACTION_VIEW);
          intent.setData(Uri.parse(URL));
          context.startActivity(intent);
        }
      });
    }
  }
}
