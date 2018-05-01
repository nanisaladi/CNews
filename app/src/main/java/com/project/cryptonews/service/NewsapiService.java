package com.project.cryptonews.service;

import com.project.cryptonews.pojo.newsapi.DataResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * News API service
 */

public interface NewsapiService {


    /**
     * Top Head lines end point
     */
    String BASE_END_POINT = "https://newsapi.org/v2/";

    /**
     * Top Head lines end point
     */
    String TOPNEWS_END_POINT = "https://newsapi.org/v2/top-headlines";

    /**
     * All news end point.
     */
    String ALLNEWS_END_POINT = "https://newsapi.org/v2/everything";

    /**
     * End point to search news from selected sources.
     * Not using for now.
     */
    String SOURCE_END_POINT = "https://newsapi.org/v2/sources";

    @GET("everything/")
    Call<DataResponse> getData(@QueryMap Map<String, String> params);
}
