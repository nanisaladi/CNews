package com.project.cryptonews;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.project.cryptonews.dao.ArticleDao;
import com.project.cryptonews.data.eventregistry.Result;
import com.project.cryptonews.db.ArticleDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

/**
 * Tests article data base read/write test.
 */
@RunWith(AndroidJUnit4.class)
public class ArticleReadWriteTest {

    private ArticleDao articleDao;
    private ArticleDatabase articleDatabase;

    /**
     * Create DB
     */
    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        articleDatabase = Room.inMemoryDatabaseBuilder(context, ArticleDatabase.class).build();
        articleDao = articleDatabase.articleDao();
    }

    /**
     * Close DB
     */
    @After
    public void closeDb() {
        articleDatabase.close();
    }

    /**
     * Test insert to and read from {@link ArticleDatabase}
     */
    @Test
    public void writeReadArticleTest() {
        Result result = new Result();
        result.setId("1");
        result.setTitle("title");
        result.setImage("image");

        articleDao.insertAll(Arrays.asList(result));
        List<Result> resultSet = articleDao.getArticles();
        Assert.assertEquals(resultSet.get(0).getId(), "1");
    }
}
