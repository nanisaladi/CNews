package com.project.cryptonews.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.project.cryptonews.dao.tuples.ArticleTuple;
import com.project.cryptonews.data.eventregistry.Result;

import java.util.List;

/**
 * Article data access object.
 */
@Dao
public interface ArticleDao {

    @Query("SELECT * from articles")
    LiveData<List<Result>> getArticles();

    @Insert
    void insertAll(List<Result> articles);

    @Delete
    void delete(Result article);

    @Query("SELECT title, image FROM articles")
    List<ArticleTuple> getData();
}
