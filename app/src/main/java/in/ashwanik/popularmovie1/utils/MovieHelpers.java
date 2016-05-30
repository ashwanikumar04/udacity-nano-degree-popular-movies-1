package in.ashwanik.popularmovie1.utils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import in.ashwanik.popularmovie1.common.BaseApplication;
import in.ashwanik.popularmovie1.entities.Movie;
import in.ashwanik.popularmovie1.events.FavoriteRemovedEvent;

/**
 * Created by AshwaniK on 5/30/2016.
 */

public class MovieHelpers {
    public static int INVALID_FAVORITE_INDEX = -1;

    public static int isFavorite(Movie movie) {
        return isFavorite(movie.getId());
    }

    public static int isFavorite(int id) {
        List<Movie> movies = BaseApplication.getInstance().getMovies();
        int index = INVALID_FAVORITE_INDEX;
        for (int localIndex = 0; localIndex < movies.size(); localIndex++) {
            if (movies.get(localIndex).getId() == id) {
                index = localIndex;
                break;
            }
        }
        return index;
    }

    public static boolean updateFavorites(Movie movie) {
        List<Movie> movies = BaseApplication.getInstance().getMovies();

        int index = isFavorite(movie);
        boolean isAdd;
        if (index > INVALID_FAVORITE_INDEX) {
            movies.remove(index);
            isAdd = false;
        } else {
            movies.add(movie);
            isAdd = true;
        }
        BaseApplication.getInstance().setMovies(movies);
        if (!isAdd) {
            EventBus.getDefault().post(new FavoriteRemovedEvent());
        }
        return isAdd;
    }


}
