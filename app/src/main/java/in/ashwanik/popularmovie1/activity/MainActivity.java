package in.ashwanik.popularmovie1.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.ashwanik.popularmovie1.R;
import in.ashwanik.popularmovie1.events.FloatingActionButtonClickEvent;

public class MainActivity extends BaseActivity {

    @Bind(R.id.sortMoviesMenu)
    FloatingActionMenu floatingActionMenu;

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new FloatingActionButtonClickEvent(v.getId()));
            floatingActionMenu.close(true);
        }
    };


    private void initializeFloatingActionButtons() {

        FloatingActionButton sortByPopular = ButterKnife.findById(floatingActionMenu, R.id.sortByPopular);
        FloatingActionButton sortByHighestRated = ButterKnife.findById(floatingActionMenu, R.id.sortByHighestRated);

        sortByPopular.setOnClickListener(onClickListener);
        sortByHighestRated.setOnClickListener(onClickListener);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolBar(false);
        floatingActionMenu.setClosedOnTouchOutside(true);
        initializeFloatingActionButtons();
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
