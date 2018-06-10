package com.project.cryptonews.di;

import com.project.cryptonews.ui.news.view.NewsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Venkat on 04/06/18.
 */
@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract NewsFragment contributeNewsFragment();

    //Need to create coin fragment
//    @ContributesAndroidInjector
//    abstract CoinFragment contributeCoinFragmet();

}
