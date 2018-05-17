package com.project.cryptonews.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.project.cryptonews.dao.ArticleDao;
import com.project.cryptonews.data.eventregistry.Result;
import com.project.cryptonews.network.NetworkBoundResource;
import com.project.cryptonews.network.util.Resource;
import com.project.cryptonews.service.eventregistry.EventRegistryService;
import com.project.cryptonews.utils.logger.Logger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Event Repository Network class.
 */

public class EventRepository {

    // EventRegistry Service
    private EventRegistryService service;
    // Article DataAccessObject
    private ArticleDao dao;
    //Executor to execute network calls.
    private Executor executor;

    @Inject
    public EventRepository(EventRegistryService registryService,
                           ArticleDao articleDao,
                           Executor executor) {
        Logger.log(EventRepository.class.getSimpleName(), "Repository Created");
        this.service = registryService;
        this.dao = articleDao;
        this.executor = executor;
    }

    public LiveData<Resource<List<Result>>> getArticles(Map<String, String> query) {

        LiveData<Resource<List<Result>>> data = new NetworkBoundResource<List<Result>, List<Result>>() {
            @Override
            protected void saveCallResult(@NonNull List<Result> body) {
                Log.i(EventRepository.class.getSimpleName(), "Results: "+body.size());
                dao.insertAll(body);
            }

            @NonNull
            @Override
            protected Call<List<Result>> createCall() {
                return service.getSearchData(query);
            }

            @Override
            protected boolean shouldFetch(List<Result> results) {
                return results == null;
            }

            @Override
            protected LiveData<List<Result>> loadFromDb() {
                return dao.getArticles();
            }
        }.getAsLiveData();

        return data;
    }
}
