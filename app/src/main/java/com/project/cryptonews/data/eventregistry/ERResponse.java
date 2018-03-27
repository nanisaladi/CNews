package com.project.cryptonews.data.eventregistry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Venkat on 24/03/18.
 */

public class ERResponse {
    @SerializedName("articles")
    @Expose
    private Articles articles;

    public Articles getArticles() {
        return articles;
    }

    public void setArticles(Articles articles) {
        this.articles = articles;
    }

}
