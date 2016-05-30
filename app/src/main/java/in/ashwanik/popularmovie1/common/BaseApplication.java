package in.ashwanik.popularmovie1.common;

import android.app.Application;
import android.os.Build;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.MaterialModule;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import in.ashwanik.popularmovie1.R;
import in.ashwanik.popularmovie1.entities.Movie;
import in.ashwanik.popularmovie1.utils.Helpers;
import in.ashwanik.popularmovie1.web.ApiUrls;
import in.ashwanik.retroclient.RetroClientServiceInitializer;
import in.ashwanik.retroclient.utils.Json;

/**
 * Created by AshwaniK on 2/28/2016.
 */
public class BaseApplication extends Application {
    private static BaseApplication sInstance;
    int progressViewColor;
    private List<Movie> movies;

    public static BaseApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            progressViewColor = getResources().getColor(R.color.accent, null);
        } else {
            progressViewColor = getResources().getColor(R.color.accent);
        }
        Iconify
                .with(new MaterialModule());
        RetroClientServiceInitializer.getInstance().initialize(ApiUrls.BASE_API_URL, getApplicationContext(), progressViewColor, true);
        RetroClientServiceInitializer.getInstance().setLogCategoryName("Popular_Movies");
    }

    public List<Movie> getMovies() {
        String favorites = Helpers.getStringAsPreference(Constants.PREFS_NAME_MAIN, Constants.KEYS_FAVORITES, "");
        if (!TextUtils.isEmpty(favorites)) {
            Type listType = new TypeToken<ArrayList<Movie>>() {
            }.getType();
            movies = Json.deSerializeList(favorites, listType);
        } else {
            movies = new ArrayList<>();
        }
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        Helpers.saveStringAsPreference(Constants.PREFS_NAME_MAIN, Constants.KEYS_FAVORITES, Json.serialize(movies));
    }


}
