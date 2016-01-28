package com.packtpub.apps.rxjava_essentials;

import com.packtpub.apps.rxjava_essentials.chapter4.DistinctExampleFragment;
import com.packtpub.apps.rxjava_essentials.chapter4.FilterExampleFragment;
import com.packtpub.apps.rxjava_essentials.chapter4.TakeExampleFragment;
import com.packtpub.apps.rxjava_essentials.chapter5.GroupByExampleFragment;
import com.packtpub.apps.rxjava_essentials.chapter5.MapExampleFragment;
import com.packtpub.apps.rxjava_essentials.chapter5.ScanExampleFragment;
import com.packtpub.apps.rxjava_essentials.chapter6.AndThenWhenExampleFragment;
import com.packtpub.apps.rxjava_essentials.chapter6.CombineLatestExampleFragment;
import com.packtpub.apps.rxjava_essentials.chapter6.JoinExampleFragment;
import com.packtpub.apps.rxjava_essentials.chapter6.MergeExampleFragment;
import com.packtpub.apps.rxjava_essentials.chapter6.ZipExampleFragment;
import com.packtpub.apps.rxjava_essentials.chapter7.LongTaskFragment;
import com.packtpub.apps.rxjava_essentials.chapter7.NetworkTaskFragment;
import com.packtpub.apps.rxjava_essentials.chapter7.SharedPreferencesListFragment;
import com.packtpub.apps.rxjava_essentials.chapter8.SoActivity;
import com.packtpub.apps.rxjava_essentials.example1.FirstExampleFragment;
import com.packtpub.apps.rxjava_essentials.example2.SecondExampleFragment;
import com.packtpub.apps.rxjava_essentials.example3.ThirdExampleFragment;
import com.packtpub.apps.rxjava_essentials.navigation_drawer.NavigationDrawerCallbacks;
import com.packtpub.apps.rxjava_essentials.navigation_drawer.NavigationDrawerFragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity implements NavigationDrawerCallbacks {

    @InjectView(R.id.toolbar_actionbar)
    Toolbar mToolbar;

    @InjectView(R.id.drawer)
    DrawerLayout mDrawerLayout;

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, mDrawerLayout, mToolbar);

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.packtpub.apps.rxjava_essentials.R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (position) {
            case 0:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new FirstExampleFragment())
                    .commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new SecondExampleFragment())
                    .commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new ThirdExampleFragment())
                    .commit();
                break;
            case 3:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new FilterExampleFragment())
                    .commit();
                break;
            case 4:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new TakeExampleFragment())
                    .commit();
                break;
            case 5:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new DistinctExampleFragment())
                    .commit();
                break;
            case 6:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new MapExampleFragment())
                    .commit();
                break;
            case 7:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new ScanExampleFragment())
                    .commit();
                break;
            case 8:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new GroupByExampleFragment())
                    .commit();
                break;
            case 9:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new MergeExampleFragment())
                    .commit();
                break;
            case 10:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new ZipExampleFragment())
                    .commit();
                break;
            case 11:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new JoinExampleFragment())
                    .commit();
                break;
            case 12:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new CombineLatestExampleFragment())
                    .commit();
                break;
            case 13:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new AndThenWhenExampleFragment())
                    .commit();
                break;
            case 14:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new SharedPreferencesListFragment())
                    .commit();
                break;
            case 15:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new LongTaskFragment())
                    .commit();
                break;
            case 16:
                fragmentManager.beginTransaction()
                    .replace(R.id.container, new NetworkTaskFragment())
                    .commit();
                break;
            case 17:
                startActivity(new Intent(this, SoActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
