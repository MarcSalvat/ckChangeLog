package de.cketti.sample.changelog;

import de.cketti.library.changelog.ChangeLog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChangeLog cl = new ChangeLog(this);
        if (cl.isFirstRun()) {
            cl.getLogDialog().show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_whats_new: {
                new DayNightTemeChangeLog(this, true).getLogDialog().show();
                break;
            }
            case R.id.menu_full_changelog: {
                new DayNightTemeChangeLog(this, false).getFullLogDialog().show();
                break;
            }
        }

        return true;
    }

    /**
     * Example that shows how to create a themed dialog.
     */
    public static class DayNightTemeChangeLog extends ChangeLog {
        private static final String THEME_CSS =
                "body { background-color: #FFFFFF; padding-left: 1em; padding-top: 0.7em; padding-right:0.7em; }" + "\n" + DEFAULT_CSS;
        private static final String NIGHT_DARK_THEME_CSS =
                "body { color: white; background-color: #424242; padding-left: 1em; padding-top: 0.7em; padding-right:0.7em; }" + "\n" + DEFAULT_CSS;


        public DayNightTemeChangeLog(Context context, boolean isDarkTheme) {
            super((isDarkTheme) ? new ContextThemeWrapper(context, R.style.DarkDialogTheme): context, (isDarkTheme) ? NIGHT_DARK_THEME_CSS: THEME_CSS);
        }
    }
}
