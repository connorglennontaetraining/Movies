package com.example.connorglennon.movies.views;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connorglennon.movies.MoviesApp;
import com.example.connorglennon.movies.R;
import com.example.connorglennon.movies.model.Page;
import com.example.connorglennon.movies.model.Result;
import com.example.connorglennon.movies.service.API;
import com.example.connorglennon.movies.util.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.net.URI;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Connor Glennon on 23/11/2017.
 */

public class ResultAdapter extends RecyclerView.Adapter {

    private int layoutId;
    List<Result> resultList;

    public ResultAdapter(int layoutId, List<Result> resultList) {
        this.layoutId = layoutId;
        this.resultList = resultList;
    }

    @Override
    public PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MoviesApp.getContext()).inflate(layoutId, parent, false);
        return new PageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PageViewHolder pageViewHolder = (PageViewHolder) holder;
        Result result = resultList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventBus.getDefault().post(result);
            }
        });

        pageViewHolder.title.setText(result.getTitle());
        ImageLoader.loadURL(API.BASE_URL_IMAGES + result.getPosterPath(), pageViewHolder.poster, MoviesApp.getContext());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class PageViewHolder extends RecyclerView.ViewHolder{

        private ImageView poster;
        private TextView title;

        public PageViewHolder(View itemView) {
            super(itemView);

            this.poster = (ImageView) itemView.findViewById(R.id.poster);
            this.title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
