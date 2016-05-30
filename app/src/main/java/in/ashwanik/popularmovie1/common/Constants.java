package in.ashwanik.popularmovie1.common;

/**
 * Created by AshwaniK on 2/28/2016.
 */
public class Constants {

    public static String API_KEY = "e94bceabe6c0fd49a455c793de6c4ff5";

    public static final String PREFS_NAME_MAIN = "Keys";
    public static final String KEYS_FAVORITES = "Favorites";
    public static final String KEYS_SORT_TYPE = "SortType";


    public static class SortType {
        public static final int SORT_BY_MOST_POPULAR = 1;
        public static final int SORT_BY_HEIGHEST_RATED = 2;
        public static final int SORT_BY_FAVORITE_MOVIES = 3;
    }


    public static class DetailType {
        public static final int REVIEWS = 1;
        public static final int TRAILERS = 2;
    }

}
