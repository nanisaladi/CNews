package com.project.cryptonews.service;

import com.project.cryptonews.pojo.coinmarket.Coin;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Coin Market Service.
 */

public interface CoinMarketService {
    // https://api.coinmarketcap.com/v1/ticker/?start=100&limit=10
    String BASE_URL = "https://api.coinmarketcap.com/";

    @GET("/v1/ticker/bitcoin")
    Call<Coin> getCoins();
}
