
package in.ashwanik.popularmovie1.interfaces;

import android.view.View;

public interface IClickHandler {
    void onItemClicked(View view, int position);

    void onItemLongClicked(View view, int position);

    void onButtonAction(View view, int position);

}
