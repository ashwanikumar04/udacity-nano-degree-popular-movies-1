package in.ashwanik.popularmovie1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.ashwanik.popularmovie1.R;
import in.ashwanik.popularmovie1.fragment.DetailActivityFragment;

public class MainActivity extends BaseActivity {

    @Bind(R.id.detailView)
    @Nullable
    FrameLayout detailView;

    public FrameLayout getDetailView() {
        return detailView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int smallestScreenWidth = getResources().getConfiguration().smallestScreenWidthDp;
        if (smallestScreenWidth >= 600) {
            setContentView(R.layout.activity_main_large);
        } else {
            setContentView(R.layout.activity_main_small);
        }
        ButterKnife.bind(this);
        setToolBar(false);
        if (detailView != null) {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.detailView, new DetailActivityFragment(), "TAG_FRAGMENT")
                        .commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
