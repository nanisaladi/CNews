package com.project.cryptonews.ui.coins.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.project.cryptonews.network.util.Resource;
import com.project.cryptonews.data.coinmarket.Coin;
import com.project.cryptonews.repository.CoinRepository;

import javax.inject.Inject;

/**
 * View model to fetch coin data.
 */

public class CoinDataViewModel extends ViewModel {
    private final CoinRepository coinRepository;

    @Inject
    public CoinDataViewModel(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
        coinRepository.loadCoins();
    }

    public LiveData<Resource<Coin>> getCoinData(String id) {
        return coinRepository.getCoinData(id);
    }
}
