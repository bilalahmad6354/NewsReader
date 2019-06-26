package com.example.newsreader.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsreader.MainActivity;
import com.example.newsreader.R;
import com.example.newsreader.WebViewActivity;
import com.example.newsreader.common.HelperVariables;
import com.example.newsreader.models.Post;
import com.squareup.picasso.Picasso;
import java.util.List;

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.NewsFeedViewHolder> {


    private List<Post> listPost;
    public Context context;

    public NewsFeedAdapter(Context context , List<Post> listPost){
        this.context = context;
        this.listPost = listPost;
    }
    @NonNull
    @Override
    public NewsFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news_feed, parent, false);
        NewsFeedViewHolder newsFeedViewHolder = new NewsFeedViewHolder(v);

        return newsFeedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsFeedViewHolder holder, int position) {
        Post post = getListPost().get(position);
        holder.tvTitle.setText(post.getTitle());
        if(! post.getImg().isEmpty())
            Picasso.get().load(post.getImg()).placeholder(R.drawable.img_dummy).into(holder.ivThumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startWebviewActivity(position);
            }
        });

    }

    private void startWebviewActivity(int position) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(HelperVariables.KEY_URL_ARGUMENT, listPost.get(position).getLink());
        context.startActivity(intent);
        ((MainActivity) context).overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }


    @Override
    public int getItemCount() {
        return listPost.size();
    }


    public static class NewsFeedViewHolder extends RecyclerView.ViewHolder {
        CardView cvNewsFeed;
        TextView tvTitle;
        ImageView ivThumbnail;


        NewsFeedViewHolder(View itemView) {
            super(itemView);
            cvNewsFeed = (CardView)itemView.findViewById(R.id.cvNewsFeed);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            ivThumbnail = (ImageView) itemView.findViewById(R.id.ivThumbnail);
        }
    }


    public List<Post> getListPost() {
        return listPost;
    }

    public void setListPost(List<Post> listPost) {
        this.listPost = listPost;
    }


}
