package com.project.cryptonews.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.project.cryptonews.data.eventregistry.Result;

import java.util.List;

/**
 * Article data access object.
 */
@Dao
public interface ArticleDao {

    @Query("SELECT * from articles")
    LiveData<List<Result>> getArticles();
}
