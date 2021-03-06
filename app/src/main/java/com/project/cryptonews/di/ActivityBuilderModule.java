package com.project.cryptonews.di;

import com.project.cryptonews.ui.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by sakethramk on 2/15/18.
 */
@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract HomeActivity homeActivity();
}
