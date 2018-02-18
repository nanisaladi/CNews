package com.project.cryptonews.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.project.cryptonews.pojo.coinmarket.Coin;

/**
 * Created by sakethramk on 2/18/18.
 */
@Database(entities = {Coin.class}, version = 1)
public abstract class CoinDatabase extends RoomDatabase {
    public abstract CoinDao coinDao();
}
