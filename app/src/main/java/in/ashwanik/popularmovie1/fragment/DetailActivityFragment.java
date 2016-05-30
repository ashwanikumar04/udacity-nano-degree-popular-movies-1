package in.ashwanik.popularmovie1.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.MaterialIcons;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.ashwanik.popularmovie1.R;
import in.ashwanik.popularmovie1.adapters.ReviewsAdapter;
import in.ashwanik.popularmovie1.adapters.TrailersAdapter;
import in.ashwanik.popularmovie1.common.BaseApplication;
import in.ashwanik.popularmovie1.common.Constants;
import in.ashwanik.popularmovie1.entities.Movie;
import in.ashwanik.popularmovie1.interfaces.IClickHandler;
import in.ashwanik.popularmovie1.response.ReviewsAndTrailersResponse;
import in.ashwanik.popularmovie1.utils.FontIconHelper;
import in.ashwanik.popularmovie1.utils.Helpers;
import in.ashwanik.popularmovie1.utils.MovieHelpers;
import in.ashwanik.popularmovie1.web.clients.MovieClient;
import in.ashwanik.retroclient.entities.ErrorData;
import in.ashwanik.retroclient.interfaces.RequestHandler;
import in.ashwanik.retroclient.service.RetroClientServiceGenerator;
import in.ashwanik.retroclient.utils.Json;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends BaseFragment {

    Movie movie;
    @Bind(R.id.icon)
    ImageView imageView;
    @Bind(R.id.favorite)
    FloatingActionButton floatingActionButtonFavorite;
    @Bind(R.id.detailOptions)
    FloatingActionMenu floatingActionMenu;
    @Bind(R.id.detailsView)
    TextView detailsView;
    @Bind(R.id.content)
    ScrollView content;
    RetroClientServiceGenerator serviceGenerator;
    ReviewsAndTrailersResponse reviewsAndTrailersResponse;
    IClickHandler handler;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // EventBus.getDefault().post(new FloatingActionButtonClickEvent((int) v.getTag()));
            if (reviewsAndTrailersResponse == null) {
                loadDataFromApi((int) v.getTag());
            } else {
                showDetail((int) v.getTag());
            }

            floatingActionMenu.close(true);
        }
    };
    View view;
    private String iconId;

    private void initializeFloatingActionButtons() {

        FloatingActionButton reviews = ButterKnife.findById(floatingActionMenu, R.id.reviews);
        reviews
                .setImageDrawable(FontIconHelper.getFontDrawable(BaseApplication.getInstance(), MaterialIcons.md_play_circle_filled));
        reviews.setTag(Constants.DetailType.REVIEWS);
        FloatingActionButton trailers = ButterKnife.findById(floatingActionMenu, R.id.trailers);
        trailers
                .setImageDrawable(FontIconHelper.getFontDrawable(BaseApplication.getInstance(), MaterialIcons.md_view_headline));
        trailers.setTag(Constants.DetailType.TRAILERS);
        reviews.setOnClickListener(onClickListener);
        trailers.setOnClickListener(onClickListener);
    }

    private void loadDataFromApi(final int type) {
        MovieClient movieClient = serviceGenerator.getService(MovieClient.class, "movieClient");

        serviceGenerator.execute(movieClient
                        .getMoviesAndTrailerResponse(movie.getId(), Constants.API_KEY,
                                "trailers,reviews"),
                new RequestHandler<ReviewsAndTrailersResponse>() {
                    @Override
                    public void onSuccess(ReviewsAndTrailersResponse response) {
                        reviewsAndTrailersResponse = response;
                        showDetail(type);
                    }

                    @Override
                    public void onError(ErrorData errorData) {
                        showSnackBar(errorData, imageView);
                    }
                });
    }

    private void showData(int type) {
        try {
            final Dialog dialog = new Dialog(getActivity(), R.style.Dialog_No_Border);
            dialog.getWindow().getAttributes().windowAnimations = R.style.SlideUpDownAnimation;
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View dialogView = inflater.inflate(R.layout.detail_dialog, null);
            final RecyclerView recyclerView;
            TextView tvTitle = ButterKnife.findById(dialogView, R.id.tvTitle);
            tvTitle.setText(type == Constants.DetailType.REVIEWS ? "Reviews" : "Trailers");
            ImageView dialogCancel = ButterKnife.findById(dialogView, R.id.dialog_cancel);
            dialogCancel.setImageDrawable(FontIconHelper.getFontDrawable(getActivity(), MaterialIcons.md_cancel));
            dialogCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });


            recyclerView = ButterKnife.findById(dialogView, R.id.recyclerView);
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {

                }
            });
            if (type == Constants.DetailType.TRAILERS) {
                handler = new IClickHandler() {
                    @Override
                    public void onItemClicked(final View view, int i) {
                    }

                    @Override
                    public void onItemLongClicked(View view, int i) {

                    }

                    @Override
                    public void onButtonAction(View view, int i) {
                        switch (view.getId()) {
                            case R.id.play:
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(reviewsAndTrailersResponse.getTrailers().getYoutube().get(i).getFullYoutubeUrl())));
                                break;
                            case R.id.share:
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_SEND);
                                intent.setType("text/plain");
                                intent.putExtra(Intent.EXTRA_TEXT, reviewsAndTrailersResponse.getTrailers().getYoutube().get(i).getFullYoutubeUrl());
                                startActivity(Intent.createChooser(intent, "Share via"));
                                break;
                        }
                    }
                };
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(type == Constants.DetailType.REVIEWS
                    ? new ReviewsAdapter(getActivity(), reviewsAndTrailersResponse.getReviews().getResults())
                    : new TrailersAdapter(getActivity(), reviewsAndTrailersResponse.getTrailers().getYoutube(), handler));
            dialog.setContentView(dialogView);
            dialog.setCanceledOnTouchOutside(true);
            dialog.getWindow().getAttributes().width = WindowManager.LayoutParams.MATCH_PARENT;
            dialog.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void showDetail(int tag) {
        switch (tag) {
            case Constants.DetailType.REVIEWS:
                if (reviewsAndTrailersResponse != null && reviewsAndTrailersResponse.getReviews() != null && Helpers.isNotEmpty(reviewsAndTrailersResponse.getReviews().getResults())) {
                    showData(Constants.DetailType.REVIEWS);
                } else {
                    showSnackBar("No reviews are available for this movie.", imageView);
                }
                break;
            case Constants.DetailType.TRAILERS:
                if (reviewsAndTrailersResponse != null && reviewsAndTrailersResponse.getTrailers() != null && Helpers.isNotEmpty(reviewsAndTrailersResponse.getTrailers().getYoutube())) {
                    showData(Constants.DetailType.TRAILERS);
                } else {
                    showSnackBar("No trailers are available for this movie.", imageView);
                }
                break;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_detail, container, false);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, view);
        serviceGenerator = new RetroClientServiceGenerator(DetailActivityFragment.this.getActivity(),
                false);

        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle == null) {
            bundle = getArguments();
        }
        String movieString = "";
        if (bundle != null) {
            movieString = bundle.getString("movieString");
            if (Build.VERSION.SDK_INT >= 21) {
                iconId = bundle.getString("iconId");
            }
        }

        detailsView.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(movieString)) {
            try {
                movie = Json.deSerialize(movieString, Movie.class);
                populateData();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            showFavoriteButton(MovieHelpers.isFavorite(movie) > MovieHelpers.INVALID_FAVORITE_INDEX);

            floatingActionButtonFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showFavoriteButton(MovieHelpers.updateFavorites(movie));
                }
            });
            floatingActionMenu.setClosedOnTouchOutside(true);
            initializeFloatingActionButtons();
        } else {
            detailsView.setVisibility(View.VISIBLE);
            content.setVisibility(View.GONE);
            floatingActionButtonFavorite.setVisibility(View.GONE);
            floatingActionMenu.setVisibility(View.GONE);
        }
        return view;
    }

    private void showFavoriteButton(boolean isFavorite) {
        floatingActionButtonFavorite
                .setImageDrawable(isFavorite
                                ? new IconDrawable(BaseApplication.getInstance(), MaterialIcons.md_favorite)
                                .colorRes(R.color.white)
                                .actionBarSize()
                                : new IconDrawable(BaseApplication.getInstance(), MaterialIcons.md_favorite_border)
                                .colorRes(R.color.white)
                                .actionBarSize()
                );
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

        TextView title = ButterKnife.findById(view, R.id.title);
        TextView overview = ButterKnife.findById(view, R.id.overview);
        TextView releaseDate = ButterKnife.findById(view, R.id.releaseDate);
        TextView rating = ButterKnife.findById(view, R.id.rating);

        title.setText(movie.getOriginalTitle());
        overview.setText(movie.getOverview());
        releaseDate.setText(movie.getReleaseDate());
        rating.setText(movie.getVoteAverage() + "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
