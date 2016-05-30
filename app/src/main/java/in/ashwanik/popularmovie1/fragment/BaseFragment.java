package in.ashwanik.popularmovie1.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import in.ashwanik.popularmovie1.activity.BaseActivity;
import in.ashwanik.retroclient.entities.ErrorData;

/**
 * A placeholder fragment containing a simple view.
 */
public class BaseFragment extends Fragment {

    public void showSnackBar(String message, View view) {
        ((BaseActivity) getActivity()).showSnackBar(message, view);
    }

    public void showSnackBar(ErrorData errorData, View view) {
        ((BaseActivity) getActivity()).showSnackBar(errorData, view);
    }
}
