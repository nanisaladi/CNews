package com.project.cryptonews.service.eventregistry;

import com.project.cryptonews.data.eventregistry.Result;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * News service from EventRegistry Domain
 */
public interface EventRegistryService {

//    //Event Registry API token
//    public static final String ER_API = "2cbd8568-57ea-42d9-983b-1d1118d357bb";
//
//    public static final String LATEST_NEWS = "http://eventregistry.org/json/minuteStreamArticles?apiKey=2cbd8568-57ea-42d9-983b-1d1118d357bb&callback=JSON_CALLBACK";
//    public static final String SEARCH_CRYPTO = "http://eventregistry.org/json/article?query={\"$query\":{\"$and\":[{\"conceptUri\":{\"$and\":[\"http://en.wikipedia.org/wiki/Cryptocurrency\"]}},{\"lang\":\"eng\"}]}}&action=getArticles&resultType=articles&articlesSortBy=date&articlesCount=50&articlesIncludeArticleEventUri=false&articlesIncludeArticleImage=true&articlesArticleBodyLen=-1&articlesIncludeConceptImage=true&apiKey=2cbd8568-57ea-42d9-983b-1d1118d357bb";

    /**
     * End point to event registry service.
     */
    String END_POINT = "http://eventregistry.org/json/";

    @GET("article")
    Call<List<Result>> getSearchData(@QueryMap(encoded = true) Map<String, String> query);
}
