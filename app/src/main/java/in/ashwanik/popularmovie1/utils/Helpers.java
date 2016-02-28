package in.ashwanik.popularmovie1.utils;

import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by AshwaniK on 2/28/2016.
 */
public class Helpers {
    public static void recyclerAnim(View view) {
        YoYo.with(Techniques.FadeIn)
                .duration(500)
                .playOn(view);
    }
}
