package com.project.cryptonews.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.project.cryptonews.pojo.coinmarket.Coin;
import com.project.cryptonews.service.CoinMarketService;

import java.util.List;

import retrofit2.Call;

/**
 * Created by sakethramk on 2/13/18.
 */

public class CoinRepository {

    private CoinMarketService mCoinMarketService;

    public CoinRepository(CoinMarketService coinMarketService) {
        mCoinMarketService = coinMarketService;
    }

    public Call<List<Coin>> getCoinData(String coinId) {
        return mCoinMarketService.getCoinWithId(coinId);
    }


}
