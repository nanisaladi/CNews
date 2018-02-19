package com.project.cryptonews.service;

import com.project.cryptonews.coins.model.Coin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Coin Market Service.
 */

public interface CoinMarketService {
    // https://api.coinmarketcap.com/v1/ticker/?start=100&limit=10
    String BASE_URL = "https://api.coinmarketcap.com/v1/";

    @GET("/ticker/bitcoin/")
    Call<Coin> getBitCoinData();

    // Hardcoded for now.
    @GET("/?limit=100")
    Call<List<Coin>> getTopNCoins();
}
