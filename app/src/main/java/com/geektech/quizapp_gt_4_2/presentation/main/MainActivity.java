package com.geektech.quizapp_gt_4_2.presentation.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.presentation.history.HistoryFragment;
import com.geektech.quizapp_gt_4_2.presentation.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    private MainPagerAdapter mAdapter;
    private BottomNavigationView mBottomNav;

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mViewPager.setCurrentItem(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.main_view_pager);
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mBottomNav.getMenu().getItem(position).setChecked(true);
            }
        });

        mBottomNav = findViewById(R.id.main_bottom_nav);
        mBottomNav.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int page = 0;

        switch (menuItem.getItemId()) {
            case R.id.nav_history:
                page = 1;
                break;
            case R.id.nav_settings:
                page = 2;
                break;
        }

        mViewPager.setCurrentItem(page);
        return true;
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {

        MainPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment;

            switch (position) {
                case 0:
                    fragment = MainFragment.newInstance();
                    break;
                case 1:
                    fragment = HistoryFragment.newInstance();
                    break;
                default:
                    fragment = SettingsFragment.newInstance();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
