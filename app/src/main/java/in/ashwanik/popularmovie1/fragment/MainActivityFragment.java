package in.ashwanik.popularmovie1.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.ashwanik.popularmovie1.R;
import in.ashwanik.popularmovie1.activity.BaseActivity;
import in.ashwanik.popularmovie1.activity.DetailsActivity;
import in.ashwanik.popularmovie1.adapters.MoviesAdapter;
import in.ashwanik.popularmovie1.common.Constants;
import in.ashwanik.popularmovie1.common.EndlessRecyclerViewScrollListener;
import in.ashwanik.popularmovie1.entities.Movie;
import in.ashwanik.popularmovie1.events.FloatingActionButtonClickEvent;
import in.ashwanik.popularmovie1.interfaces.IActionHandler;
import in.ashwanik.popularmovie1.interfaces.IClickHandler;
import in.ashwanik.popularmovie1.response.MovieResponse;
import in.ashwanik.popularmovie1.web.clients.MovieClient;
import in.ashwanik.retroclient.entities.ErrorData;
import in.ashwanik.retroclient.interfaces.RequestHandler;
import in.ashwanik.retroclient.service.RetroClientServiceGenerator;
import in.ashwanik.retroclient.utils.Json;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    View view;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    List<Movie> movies;

    @Bind(R.id.noData)
    TextView noData;

    IClickHandler handler;

    MoviesAdapter adapter;
    RetroClientServiceGenerator serviceGenerator;

    private void showSnackBar(String message, View view) {
        ((BaseActivity) getActivity()).showSnackBar(message, view);
    }

    private void showSnackBar(ErrorData errorData, View view) {
        ((BaseActivity) getActivity()).showSnackBar(errorData, view);
    }

    private void loadDataFromApi(int page, final IActionHandler<List<Movie>> actionHandler) {
        MovieClient movieClient = serviceGenerator.getService(MovieClient.class, "movieClient");

        serviceGenerator.execute(movieClient
                        .getMovies(Constants.API_KEY,
                                page, sortBy),
                new RequestHandler<MovieResponse>() {
                    @Override
                    public void onSuccess(MovieResponse response) {
                        if (actionHandler != null) {
                            actionHandler.handle(response.getResults());
                        }
                        movies.addAll(response.getResults());
                        int curSize = adapter.getItemCount();
                        adapter.notifyItemRangeInserted(curSize, movies.size() - 1);

                    }

                    @Override
                    public void onError(ErrorData errorData) {
                        showSnackBar(errorData, recyclerView);
                    }
                });
    }


    void initializeRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView
                .addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount) {
                        loadDataFromApi(page + 1, null);
                    }
                });
    }

    private String sortBy;

    @Subscribe
    public void onEvent(FloatingActionButtonClickEvent event) {
        movies.clear();
        adapter = new MoviesAdapter(getActivity(), movies, handler);
        switch (event.getClickedButtonId()) {
            case R.id.sortByPopular:
                sortBy = "popularity.desc";
                break;

            case R.id.sortByHighestRated:
                sortBy = "vote_average.desc";
                break;
        }
        fetchData();
    }

    private void fetchData() {
        noData.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        initializeRecyclerView();
        loadDataFromApi(1, new IActionHandler<List<Movie>>() {
            @Override
            public void handle() {

            }

            @Override
            public void handle(List<Movie> data) {
                if (data.size() == 0) {
                    noData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    noData.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, container, false);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, view);
        movies = new ArrayList<>();
        serviceGenerator = new RetroClientServiceGenerator(MainActivityFragment.this.getActivity(),
                false);
        handler = new IClickHandler() {
            @Override
            public void onItemClicked(View view, int position) {
                Movie movie = movies.get(position);
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("movieString", Json.serialize(movie));
                if (Build.VERSION.SDK_INT >= 21) {
                    View icon = ButterKnife.findById(view, R.id.iv_movie_poster);
                    intent.putExtra("iconId", icon.getTransitionName());
                    ActivityOptionsCompat options;
                    options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(),
                            new Pair<>(icon,
                                    icon.getTransitionName())
                    );
                    ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
                } else {
                    startActivity(intent);
                }

            }

            @Override
            public void onItemLongClicked(View view, int position) {

            }

            @Override
            public void onButtonAction(View view, int position) {

            }
        };
        adapter = new MoviesAdapter(this.getActivity(), movies, handler);
        sortBy = "";
        fetchData();
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);

    }

}
