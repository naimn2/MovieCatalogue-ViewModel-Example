package com.muflihun.moviecatalogue3.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.muflihun.moviecatalogue3.R;
import com.muflihun.moviecatalogue3.models.Item;

public class DetailMovie extends AppCompatActivity {
    private TextView tvTitle, tvDesc, tvPopularity, tvRating, tvRelease, tvLanguage;
    private ImageView ivPoster, ivBackdrop;

    public static final String EXTRA_ITEM = "extraItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvTitle = findViewById(R.id.tv_title_detail);
        tvDesc = findViewById(R.id.tv_description_detail);
        tvPopularity = findViewById(R.id.tv_popularity);
        tvRating = findViewById(R.id.tv_rating);
        tvRelease = findViewById(R.id.tv_release);
        tvLanguage = findViewById(R.id.tv_language);
        ivPoster = findViewById(R.id.iv_poster_detail);
        ivBackdrop = findViewById(R.id.iv_backdrop);

        Item item = getIntent().getParcelableExtra(EXTRA_ITEM);

        tvTitle.setText(item.getTitle());
        tvDesc.setText(item.getOverview());
        tvRating.setText(String.valueOf(item.getVote()));
        tvPopularity.setText(String.valueOf(item.getPopularity()));
        tvRelease.setText(item.getRelease());
        tvLanguage.setText(item.getLanguage());
        Glide.with(this).load("https://image.tmdb.org/t/p/w185/"+item.getPoster()).into(ivPoster);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/"+item.getBackdrop()).into(ivBackdrop);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
