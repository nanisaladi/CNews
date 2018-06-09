package com.project.cryptonews.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.project.cryptonews.R;
import com.project.cryptonews.ui.coins.view.CalculatorFragment;
import com.project.cryptonews.ui.news.view.NewsFragment;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class HomeActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mScreens = Arrays.asList(
                new NewsFragment(),
                new CalculatorFragment());

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mScreens.get(position);
        }

        @Override
        public int getCount() {
            return mScreens.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mScreens.get(position).toString();
        }
    }
}
