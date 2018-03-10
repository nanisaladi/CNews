package com.project.cryptonews.service;

import com.project.cryptonews.pojo.coinmarket.Coin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Coin Market Service.
 */

public interface CoinMarketService {

    @GET("/bitcoin/")
    Call<Coin> getBitCoinData();

    // Hardcoded for now.
    @GET("/?limit=100")
    Call<List<Coin>> getTopNCoins();

    @GET("{id}")
    Call<List<Coin>> getCoinWithId(@Path("id") String id);
}
