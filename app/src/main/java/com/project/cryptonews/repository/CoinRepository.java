package com.project.cryptonews.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.project.cryptonews.data.CoinDao;
import com.project.cryptonews.data.NetworkBoundResource;
import com.project.cryptonews.data.Resource;
import com.project.cryptonews.pojo.coinmarket.Coin;
import com.project.cryptonews.service.CoinMarketService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by sakethramk on 2/13/18.
 */

public class CoinRepository {

    private final CoinMarketService coinMarketService;
    private final CoinDao coinDao;

    @Inject
    public CoinRepository(CoinMarketService coinMarketService, CoinDao coinDao) {
        this.coinMarketService = coinMarketService;
        this.coinDao = coinDao;
    }

    public LiveData<Resource<List<Coin>>> loadCoins() {
        return new NetworkBoundResource<List<Coin>, List<Coin>>() {

            @Override
            protected void saveCallResult(@NonNull List<Coin> body) {
                coinDao.saveCoins(body);
            }

            @Override
            protected Call<List<Coin>> createCall() {
                return coinMarketService.getTopNCoins();
            }

            @Override
            protected boolean shouldFetch(List<Coin> coins) {
                return coins == null;
            }

            @Override
            protected LiveData<List<Coin>> loadFromDb() {
                return coinDao.loadCoins();
            }
        }.getAsLiveData();
    }

    public LiveData<Coin> getCoinData(String id) {
        return coinDao.getCoinData(id);
    }
}
