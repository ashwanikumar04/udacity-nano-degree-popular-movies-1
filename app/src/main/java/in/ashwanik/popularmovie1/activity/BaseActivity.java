package in.ashwanik.popularmovie1.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import in.ashwanik.popularmovie1.R;
import in.ashwanik.retroclient.entities.ErrorData;

/**
 * Created by AshwaniK on 2/28/2016.
 */
public class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void setToolBar(boolean setHomeAction) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && setHomeAction) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public void showSnackBar(String message, final View view) {
        showSnackBar(message, view, null, null);
    }

    private void showSnackBar(String message, final View view, String action, View.OnClickListener onClickListener) {
        if (onClickListener != null && !TextUtils.isEmpty(action)) {
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                    .setAction("Retry", onClickListener).show();
        } else {
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                    .setAction("", null).show();
        }

    }


    public void showSnackBar(ErrorData errorData, final View view) {
        showSnackBar(errorData, view, null, null);
    }

    private void showSnackBar(ErrorData errorData, final View view, String action, View.OnClickListener onClickListener) {
        String message = errorData.getMessage();
        if (onClickListener != null && !TextUtils.isEmpty(action)) {
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                    .setAction("Retry", onClickListener).show();
        } else {
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                    .setAction("", null).show();
        }

    }
}
