package in.ashwanik.popularmovie1.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.ashwanik.popularmovie1.R;
import in.ashwanik.popularmovie1.common.BaseApplication;
import in.ashwanik.popularmovie1.entities.Movie;
import in.ashwanik.retroclient.utils.Json;


public class DetailsActivity extends BaseActivity {
    Movie movie;
    @Bind(R.id.icon)
    ImageView imageView;

    private String iconId;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= 21) {
                    finishAfterTransition();
                } else {
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setToolBar(true);
        Bundle bundle = getIntent().getExtras();
        final String movieString = bundle.getString("movieString");
        if (Build.VERSION.SDK_INT >= 21) {
            iconId = bundle.getString("iconId");
        }
        try {
            movie = Json.deSerialize(movieString, Movie.class);
            populateData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
    private void populateData() {
        if (Build.VERSION.SDK_INT >= 21) {
            imageView.setTransitionName(iconId);
        }
        imageView.setImageResource(R.drawable.placeholder);
        if (!TextUtils.isEmpty(movie.getFullPosterPath())) {
            Glide.with(BaseApplication.getInstance())
                    .load(movie.getFullPosterPath())
                    .placeholder(R.drawable.placeholder)
                    .into(imageView);
        }

        TextView title = ButterKnife.findById(this, R.id.title);
        TextView overview = ButterKnife.findById(this, R.id.overview);
        TextView releaseDate = ButterKnife.findById(this, R.id.releaseDate);
        TextView rating = ButterKnife.findById(this, R.id.rating);

        title.setText(movie.getOriginalTitle());
        overview.setText(movie.getOverview());
        releaseDate.setText(movie.getReleaseDate());
        rating.setText(movie.getVoteAverage() + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
