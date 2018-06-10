package com.project.cryptonews.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.project.cryptonews.dao.CoinDao;
import com.project.cryptonews.network.NetworkBoundResource;
import com.project.cryptonews.network.util.Resource;
import com.project.cryptonews.data.coinmarket.Coin;
import com.project.cryptonews.service.marketcap.CoinMarketService;

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

    public LiveData<Resource<Coin>> getCoinData(String id) {

        return new NetworkBoundResource<Coin, List<Coin>>() {

            @Override
            protected void saveCallResult(@NonNull List<Coin> body) {
                Log.d(CoinRepository.class.getSimpleName(), "saving call result from coin repository: " + body);
                coinDao.saveCoins(body);
            }

            @Override
            protected Call<List<Coin>> createCall() {
                Log.d(CoinRepository.class.getSimpleName(), "creating call from coin repository");
                return coinMarketService.getCoinWithId(id);
            }

            @Override
            protected boolean shouldFetch(Coin coins) {
                return coins == null;
            }

            @Override
            protected LiveData<Coin> loadFromDb() {
                Log.d(CoinRepository.class.getSimpleName(), "loading data from dao in coin repository");
                return coinDao.getCoinData(id);
            }
        }.getAsLiveData();
    }
}
