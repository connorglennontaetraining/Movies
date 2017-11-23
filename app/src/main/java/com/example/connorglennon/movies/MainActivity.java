package com.example.connorglennon.movies;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.connorglennon.movies.model.Result;
import com.example.connorglennon.movies.service.API;
import com.example.connorglennon.movies.views.MovieFragment;
import com.example.connorglennon.movies.views.MovieListFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Result result) {
        MovieFragment movieListFragment = new MovieFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(API.PARAM_ID, result.getId());
        movieListFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, movieListFragment)
                .addToBackStack("")
                .commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
