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
    // https://api.coinmarketcap.com/v1/ticker/?start=100&limit=10
    String BASE_TICKER_URL = "https://api.coinmarketcap.com/v1/ticker/";

    @GET("/bitcoin/")
    Call<Coin> getBitCoinData();

    // Hardcoded for now.
    @GET("/?limit=100")
    Call<List<Coin>> getTopNCoins();

    @GET("{id}")
    Call<List<Coin>> getCoinWithId(@Path("id") String id);
}
