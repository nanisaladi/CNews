package com.project.cryptonews.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.cryptonews.dao.ArticleDao;
import com.project.cryptonews.dao.CoinDao;
import com.project.cryptonews.db.ArticleDatabase;
import com.project.cryptonews.db.CoinDatabase;
import com.project.cryptonews.service.ApiConstants;
import com.project.cryptonews.service.eventregistry.EventRegistryService;
import com.project.cryptonews.service.marketcap.CoinMarketService;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * App Module to provide singleton instance of objects are stored at app context level.
 */

@Module(includes = ViewModelModule.class)
public class AppModule {

    //Provides CoinMarketCap retrofit object.
    @Provides
    @Singleton
    CoinMarketService provideCmsRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_TICKER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(CoinMarketService.class);
    }



//    Two Gson instances giving a compile time error = Gson is bound multiple times.
//    @Provides
//    @Singleton
//    Gson provideGson() {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        return gsonBuilder.create();
//    }

    //Provides custom Gson provider for event registry service.
    @Provides
    @Singleton
    Gson provideERGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    //Provides Event Registry service retrofit object for network calls.
    @Provides
    @Singleton
    EventRegistryService provideERserviceRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EventRegistryService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EventRegistryService.class);
    }

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    //Provides Db to store coins data.
    @Provides
    @Singleton
    CoinDatabase provideCoinDataBase(Application application) {
        return Room.databaseBuilder(application, CoinDatabase.class,
                "crypto.db").build();
    }

    /**
     * Provides a db instance to store articles.
     * @param application app instance
     * @return db instance
     */
    @Provides
    @Singleton
    ArticleDatabase provideArticleDatabase(Application application) {
        return Room.databaseBuilder(application, ArticleDatabase.class,
                "articles.db").fallbackToDestructiveMigration().build();
    }

    /**
     * Provides article data access object.
     * @param articleDatabase article db
     * @return dao instance
     */
    @Provides
    @Singleton
    ArticleDao provideArticleDao(ArticleDatabase articleDatabase) {
        return articleDatabase.articleDao();
    }

    /**
     * Provides coin data access object.
     * @param coinDatabase
     * @return object.
     */
    @Provides
    @Singleton
    CoinDao provideCoinDao(CoinDatabase coinDatabase) {
        return coinDatabase.coinDao();
    }
}
