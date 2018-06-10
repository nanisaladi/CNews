package com.project.cryptonews.dao.tuples;

import android.arch.persistence.room.ColumnInfo;

/**
 * Article tuple to get few items from Article data base.
 */

public class ArticleTuple {
    //Title of the article
    @ColumnInfo(name = "title")
    public String title;

    //Image URL of the article.
    @ColumnInfo(name = "image")
    public String image;


}
