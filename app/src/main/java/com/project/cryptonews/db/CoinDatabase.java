package com.project.cryptonews.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.project.cryptonews.dao.CoinDao;
import com.project.cryptonews.data.coinmarket.Coin;

/**
 * Coins Data base.
 */
@Database(entities = {Coin.class}, version = 1)
public abstract class CoinDatabase extends RoomDatabase {
    public abstract CoinDao coinDao();
}
