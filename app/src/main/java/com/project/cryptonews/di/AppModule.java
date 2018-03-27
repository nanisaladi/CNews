package com.project.cryptonews.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.project.cryptonews.dao.ArticleDao;
import com.project.cryptonews.dao.CoinDao;
import com.project.cryptonews.db.ArticleDatabase;
import com.project.cryptonews.db.CoinDatabase;
import com.project.cryptonews.service.ApiConstants;
import com.project.cryptonews.service.marketcap.CoinMarketService;

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

    /**
     * Provides a db instance to store articles.
     * @param application app instance
     * @return db instance
     */
    @Provides
    @Singleton
    ArticleDatabase provideArticleDatabase(Application application) {
        return Room.databaseBuilder(application, ArticleDatabase.class,
                "articles.db").build();
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

    @Provides
    @Singleton
    CoinDao provideCoinDao(CoinDatabase coinDatabase) {
        return coinDatabase.coinDao();
    }
}
