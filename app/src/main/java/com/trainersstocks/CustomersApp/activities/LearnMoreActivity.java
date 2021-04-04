package com.trainersstocks.CustomersApp.activities;

import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;

import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractActivity;
import com.trainersstocks.CustomersApp.storyView.StoriesProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LearnMoreActivity extends MyAbstractActivity implements StoriesProgressView.StoriesListener {

    private static final int PROGRESS_COUNT = 2;

    @BindView(R.id.stories)
    StoriesProgressView storiesProgressView;

    @BindView(R.id.image)
    AppCompatImageView image;

    private int counter = 0;
    private final int[] resources = new int[]{
            R.drawable.img1,
            R.drawable.iimg2,
    };

    private final long[] durations = new long[]{
            1500L, 1500L
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_more);
        initview();
        listners();
    }

    @Override
    public void initview() {
        ButterKnife.bind(this);
        showBackButton();


    }

    @Override
    public void listners() {
        storiesProgressView.setStoriesCount(PROGRESS_COUNT);
        storiesProgressView.setStoryDuration(3000L);
        // or
        // storiesProgressView.setStoriesCountWithDurations(durations);
        storiesProgressView.setStoriesListener(this);
//        storiesProgressView.startStories();
        counter = 0;
        storiesProgressView.startStories(counter);
        image.setImageResource(resources[counter]);


    }

    @Override
    public void onNext() {
        image.setImageResource(resources[++counter]);
    }

    @Override
    public void onPrev() {
        if ((counter - 1) < 0) return;
        image.setImageResource(resources[--counter]);

    }

    @Override
    public void onComplete() {

    }

    @Override
    protected void onDestroy() {
        // Very important !
        storiesProgressView.destroy();
        super.onDestroy();
    }

}