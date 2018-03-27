package com.project.cryptonews.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.project.cryptonews.dao.ArticleDao;
import com.project.cryptonews.data.eventregistry.Result;

/**
 * New article data base to store article information.
 */
@Database(entities = {Result.class}, version = 1)
public abstract class ArticleDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
