package com.project.cryptonews.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.project.cryptonews.data.CoinDao;
import com.project.cryptonews.data.CoinDatabase;
import com.project.cryptonews.service.ApiConstants;
import com.project.cryptonews.service.CoinMarketService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sakethramk on 2/15/18.
 */

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    CoinMarketService provideCmsRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_TICKER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(CoinMarketService.class);
    }

    @Provides
    @Singleton
    CoinDatabase provideCoinDataBase(Application application) {
        return Room.databaseBuilder(application, CoinDatabase.class,
                "crypto.db").build();
    }

    @Provides
    @Singleton
    CoinDao provideCoinDao(CoinDatabase coinDatabase) {
        return coinDatabase.coinDao();
    }
}
