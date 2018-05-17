package com.project.cryptonews.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.project.cryptonews.ui.coins.viewmodel.CoinDataViewModel;
import com.project.cryptonews.ui.coins.viewmodel.CryptoViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * View Model Module provider.
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinDataViewModel.class)
    abstract ViewModel bindsCoinDataViewModel(CoinDataViewModel coinDataViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(
            CryptoViewModelFactory viewModelFactory);
}
