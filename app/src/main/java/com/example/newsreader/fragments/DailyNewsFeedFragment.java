package com.example.newsreader.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newsreader.MainActivity;
import com.example.newsreader.R;
import com.example.newsreader.adapters.NewsFeedAdapter;
import com.example.newsreader.models.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DailyNewsFeedFragment extends Fragment {

    @BindView(R.id.rvDailyNewsFeed) RecyclerView rvDailyNewsFeed;

    private DatabaseReference refDB;
    DatabaseReference refNewsFeed;
    List<Post> postDataSource = new ArrayList<>();
    NewsFeedAdapter adapterNewsFeed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_daily_news_feed, container, false);
        ButterKnife.bind(this, view);
        bindVariables();
        attachListeners();
        return view;
    }

    private void bindVariables() {
        refDB = FirebaseDatabase.getInstance().getReference();
        refNewsFeed = refDB.child("news");
        adapterNewsFeed = new NewsFeedAdapter(getActivity(), postDataSource);
        rvDailyNewsFeed.setAdapter(adapterNewsFeed);

    }


    private void attachListeners() {
        refNewsFeed.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postDataSource.clear();
                for(DataSnapshot postSnap : dataSnapshot.getChildren()){
                    Post post = postSnap.getValue(Post.class);
                    postDataSource.add(post);
                }
                adapterNewsFeed.notifyDataSetChanged();
                try {
                    ((MainActivity) getActivity()).hideLoader();
                }catch (Exception ex){
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvDailyNewsFeed.setLayoutManager(llm);
        getActivity().setTitle(getActivity().getString(R.string.all_time_feed));
    }
}
