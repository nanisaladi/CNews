package com.project.cryptonews.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.project.cryptonews.data.coinmarket.Coin;

import java.util.List;

/**
 * Created by sakethramk on 2/17/18.
 */
@Dao
public interface CoinDao {

    @Query("SELECT * FROM coins")
    LiveData<List<Coin>> loadCoins();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCoins(List<Coin> coinEntities);

    @Query("SELECT * FROM coins WHERE id=:id")
    LiveData<Coin> getCoinData(String id);
}
