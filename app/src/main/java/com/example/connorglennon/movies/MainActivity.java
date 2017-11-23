package com.example.connorglennon.movies;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.connorglennon.movies.model.Page;
import com.example.connorglennon.movies.model.Result;
import com.example.connorglennon.movies.service.API;
import com.example.connorglennon.movies.service.ApiRequest;
import com.example.connorglennon.movies.service.ConnectionService;
import com.example.connorglennon.movies.views.MovieFragment;
import com.example.connorglennon.movies.views.MovieListFragment;
import com.example.connorglennon.movies.views.ResultSelected;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements ResultSelected{

    FragmentManager fragmentManager;
    FrameLayout fragmentContainer;

    //TODO check caching mechanism.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentContainer = (FrameLayout) findViewById(R.id.fragmentContainer);

        if(savedInstanceState == null)
        {
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, new MovieListFragment())
                    .commit();
        }
    }

    @Override
    public void onResultSelected(int result) {
        MovieFragment movieListFragment = new MovieFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", result);
        movieListFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, movieListFragment)
                .addToBackStack("")
                .commit();
    }
}
