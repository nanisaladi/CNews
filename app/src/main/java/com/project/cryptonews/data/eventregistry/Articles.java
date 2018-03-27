package com.project.cryptonews.data.eventregistry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Venkat on 24/03/18.
 */

public class Articles {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("pages")
    @Expose
    private Integer pages;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
