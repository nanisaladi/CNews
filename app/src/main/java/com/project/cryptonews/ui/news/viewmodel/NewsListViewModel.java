package com.project.cryptonews.ui.news.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.project.cryptonews.data.eventregistry.Result;
import com.project.cryptonews.network.util.Resource;
import com.project.cryptonews.repository.EventRepository;
import com.project.cryptonews.utils.logger.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * News list view model to store or retrieve news data
 */

public class NewsListViewModel extends ViewModel {

    /**
     * sub-class to {@link LiveData} and observe and reacts to data streams coming from LiveData.
     */
    private final MediatorLiveData<Resource<List<Result>>> mObservableData;

    /**
     * Constructs view model object
     * @param repository Repository to get data from or store data to.
     */
    @Inject
    public NewsListViewModel(EventRepository repository) {
        Logger.log(NewsListViewModel.class.getSimpleName(), "View Model Created");
        mObservableData = new MediatorLiveData<>();
        mObservableData.setValue(null);
        Log.i(NewsListViewModel.class.getSimpleName(), "Network Request");
        LiveData<Resource<List<Result>>> data = repository.getArticles(createMap());
        if(data!=null && data.getValue() != null) {
            Logger.log(NewsListViewModel.class.getSimpleName(), "Network Request: "+data.getValue());
            mObservableData.addSource(data, mObservableData::setValue);
        }
    }

    /**
     * Get articles from {@link EventRepository} service
     * @return get observable data.
     */
    public LiveData<Resource<List<Result>>> getArticles() {
        return mObservableData;
    }

    /**
     * Creates a map of parameters to get the data
     * @return Map
     */
    private Map<String, String> createMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("query", "{\"$query\":{\"$and\":[{\"conceptUri\":{\"$and\":[\"http://en.wikipedia.org/wiki/Cryptocurrency\"]}},{\"lang\":\"eng\"}]}}");
        map.put("action","getArticles");
        map.put("resultType","articles");
        map.put("articlesSortBy","date");
        map.put("articlesCount",""+50);
        map.put("articlesIncludeArticleEventUri",""+false);
        map.put("articlesIncludeArticleImage",""+true);
        map.put("articlesArticleBodyLen","-1");
        map.put("articlesIncludeConceptImage","true");
        map.put("apiKey", "2cbd8568-57ea-42d9-983b-1d1118d357bb");

        return map;
    }
}
