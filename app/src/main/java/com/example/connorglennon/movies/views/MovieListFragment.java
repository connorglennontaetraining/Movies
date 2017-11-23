package com.example.connorglennon.movies.views;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.connorglennon.movies.R;
import com.example.connorglennon.movies.model.Page;
import com.example.connorglennon.movies.model.Result;
import com.example.connorglennon.movies.service.API;
import com.example.connorglennon.movies.service.ApiRequest;
import com.example.connorglennon.movies.service.ConnectionService;
import com.example.connorglennon.movies.util.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {

    CompositeDisposable compositeDisposable;
    RecyclerView rvMovieList;
    List<Result> resultList;

    public MovieListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        compositeDisposable = new CompositeDisposable();
        //compositeDisposable.add();

        rvMovieList = (RecyclerView) getActivity().findViewById(R.id.rvMovieLst);
        Drawable drawable = Drawable.createFromPath(API.BASE_URL_IMAGES + "/nl79FQ8xWZkhL3rDr1v2RFFR6J0.jpg");
        //rvMovieList.setBackground(drawable);
        rvMovieList.setLayoutManager(new LinearLayoutManager(getActivity()));

        ApiRequest apiRequest = ConnectionService.BackendService();
        apiRequest.getTopRatedMovies(API.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Page>(){

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("Movie Api", "Subscribed");
                    }

                    @Override
                    public void onNext(Page value) {
                        ResultAdapter resultAdapter = new ResultAdapter(R.layout.movie_list_item, value.getResults());
                        rvMovieList.setAdapter(resultAdapter);
                        resultAdapter.getPositionClicks().observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(new Observer<Result>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Result value) {
                                        ResultSelected resultSelected = (ResultSelected) getActivity();
                                        resultSelected.onResultSelected(value.getId());
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(compositeDisposable != null){
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
    }
}
