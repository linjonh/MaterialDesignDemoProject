package com.jaysen.mddp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * <p>This Activity demonstrate DrawerLayout immersive mode.</p>
 * <p>
 * Use this feature, set fitSystemWindows of DrawerLayout and NavigationView xml properties as true to
 * show immersive mode.
 * But you must set {@link android.support.v7.widget.Toolbar} as Activity's ActionBar,
 * at the same time set the theme as NoActionBar style.
 * </p>
 * <p>use new {@link android.support.v7.app.ActionBarDrawerToggle} as DrawerLayout listener</p>
 */
public class ToolbarNavigationView extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_navigation_view);
        setUpDrawerLayout();
        setUpNavMenu();
    }

    private void setUpDrawerLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setHomeButtonEnabled(true);
//        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setStatusBarBackgroundColor(
                getResources().getColor(R.color.color_primary_dark));
    }

    private void setUpNavMenu() {
        mNavigation = (NavigationView) findViewById(R.id.nav_menu);
        mNavigation.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        switch (id) {
                            case R.id.navItem1:
                                break;
                            case R.id.navItem2:
                                break;
                            case R.id.navItem3:
                                break;
                        }
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawer(mNavigation);
                        return true;
                    }
                });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar_navigation_view, menu);
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

        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
