package in.ashwanik.popularmovie1.common;

import android.app.Application;
import android.os.Build;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.MaterialModule;

import in.ashwanik.popularmovie1.R;
import in.ashwanik.popularmovie1.web.ApiUrls;
import in.ashwanik.retroclient.RetroClientServiceInitializer;

/**
 * Created by AshwaniK on 2/28/2016.
 */
public class BaseApplication extends Application {
    private static BaseApplication sInstance;

    public static BaseApplication getInstance() {
        return sInstance;
    }

    int progressViewColor;

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

}
