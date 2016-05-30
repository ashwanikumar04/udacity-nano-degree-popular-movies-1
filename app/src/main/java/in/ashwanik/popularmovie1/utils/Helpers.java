package in.ashwanik.popularmovie1.utils;

import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.DecimalFormat;
import java.util.Collection;

import in.ashwanik.popularmovie1.common.BaseApplication;

/**
 * Created by AshwaniK on 2/28/2016.
 */
public class Helpers {
    public static void recyclerAnim(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(500)
                .playOn(view);
    }

    public static double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.#");
        return Double.valueOf(twoDForm.format(d));
    }

    public static <T> boolean isEmpty(Collection<T> list) {
        return list == null || list.size() == 0;
    }

    public static <T> boolean isNotEmpty(Collection<T> list) {
        return list != null && list.size() > 0;
    }

    public static void saveStringAsPreference(String prefFileName, String key, String value) {
        SharedPreference.saveStringAsPreference(BaseApplication.getInstance(), prefFileName, key, value);
    }


    public static String getStringAsPreference(String prefFileName, String key, String defaultValue) {
        return SharedPreference.getStringAsPreference(BaseApplication.getInstance(), prefFileName, key, defaultValue);
    }

    public static void saveIntegerAsPreference(String prefFileName, String key, int value) {
        SharedPreference.saveIntegerAsPreference(BaseApplication.getInstance(), prefFileName, key, value);
    }

    public static int getIntegerAsPreference(String prefFileName, String key, int defaultValue) {
        return SharedPreference.getIntegerAsPreference(BaseApplication.getInstance(), prefFileName, key, defaultValue);
    }
}
