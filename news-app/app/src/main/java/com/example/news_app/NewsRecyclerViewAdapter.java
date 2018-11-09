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

  ArrayList<NewsItem> news;
  private Context context;

  NewsRecyclerViewAdapter(ArrayList<NewsItem> news, Context context) {
    this.news = news;
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
    return news.size();
  }

  class NewsViewHolder extends ViewHolder {

    public TextView title, titleText;
    public TextView description, descriptionText;
    public TextView date, dateText;

    NewsViewHolder(View itemView) {
      super(itemView);

      date = itemView.findViewById(R.id.date);
      dateText = itemView.findViewById(R.id.date_text);
      title = itemView.findViewById(R.id.title);
      titleText = itemView.findViewById(R.id.title_text);
      description = itemView.findViewById(R.id.description);
      descriptionText = itemView.findViewById(R.id.description_text);
    }

    void bind(int index) {
      final String URL = news.get(index).getUrl();

      title.setText("Title\n");
      titleText.setText(String.format("%s\n", news.get(index).getTitle()));
      description.setText("Description\n");
      descriptionText.setText(String.format("%s\n", news.get(index).getDescription()));
      date.setText("Date Published\n");
      dateText.setText(String.format("%s\n", news.get(index).getPublishedAt()));

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