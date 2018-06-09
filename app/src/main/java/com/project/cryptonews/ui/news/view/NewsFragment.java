package com.project.cryptonews.ui.news.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.cryptonews.R;
import com.project.cryptonews.data.eventregistry.Result;
import com.project.cryptonews.ui.news.viewmodel.NewsListViewModel;
import com.project.cryptonews.utils.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * News Fragment
 */

public class NewsFragment extends Fragment {

    @BindView(R.id.list_content)
    RecyclerView recyclerView;

    @BindView(R.id.progress_id)
    ProgressBar progressBar;

    private List<Result> data = null;

    private Unbinder unbinder;
    ListAdapter adapter;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    NewsListViewModel viewModel;

    /**
     * On create
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    /**
     * on create view call back
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.list_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        data = new ArrayList<>();
        adapter = new ListAdapter(getContext().getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    /**
     * On activity created call back.
     * @param savedInstanceState save instance state bundle.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).
                get(NewsListViewModel.class);
        subscribeUi(viewModel);
    }

    /**
     * Subscribes ui to observe data.
     * @param viewModel view model
     */
    private void subscribeUi(NewsListViewModel viewModel) {
        viewModel.getArticles().observe(this, listResource -> {
                logInfo("Subscriber invoked");
                if(listResource != null) {
                    List<Result> results = listResource.data;
                    if(results!=null) {
                        progressBar.setVisibility(View.GONE);
                        logInfo("Set new data of size [%s]", results.size());
                        adapter.setNewsData(results);
                    }
                }
            });
    }

    // FIXME: 05/04/18 This logic should be deleted.
//    private void downloadData() {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://eventregistry.org/json/").addConverterFactory(GsonConverterFactory.create()).build();
//        EventRegistryService service = retrofit.create(EventRegistryService.class);
//
//        //TODO Try to send JSON and parameters as a query.
//        Map<String, String> map = new LinkedHashMap<>();
//        map.put("query", "{\"$query\":{\"$and\":[{\"conceptUri\":{\"$and\":[\"http://en.wikipedia.org/wiki/Cryptocurrency\"]}},{\"lang\":\"eng\"}]}}");
//        map.put("action","getArticles");
//        map.put("resultType","articles");
//        map.put("articlesSortBy","date");
//        map.put("articlesCount",""+50);
//        map.put("articlesIncludeArticleEventUri",""+false);
//        map.put("articlesIncludeArticleImage",""+true);
//        map.put("articlesArticleBodyLen","-1");
//        map.put("articlesIncludeConceptImage","true");
//        map.put("apiKey", "2cbd8568-57ea-42d9-983b-1d1118d357bb");
//
//        service.getSearchData(map).enqueue(new Callback<List<Result>>() {
//            @Override
//            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
//                logInfo("Success : "+response.code());
//                if(response.body()!=null) {
//                    data.clear();
//                    List<Result> erResponse = response.body();
//                    if(erResponse != null) {
//                        data.addAll(erResponse);
//                        adapter.notifyDataSetChanged();
//                    } else {
//                        Toast.makeText(getContext(), "No results", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Result>> call, Throwable t) {
//                logInfo("Failed");
//                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    /**
     * on destroy call back
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        data.clear();
    }

    /**
     * List view adapter to set data. This logic should be moved to {@link NewsAdapter}
     */
    private static class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemHodler> {

        private List<Result> articles = new ArrayList<>();
        Context context;

        ListAdapter(Context context) {
            this.context = context;
        }

        @Override
        public ItemHodler onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            return new ItemHodler(view);
        }

        @Override
        public void onBindViewHolder(ItemHodler holder, int position) {
            holder.title.setText(articles.get(position).getTitle());
            Glide.with(context).load(articles.get(position).getImage()).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return articles.size();
        }

        public void setNewsData(List<Result> results) {
            articles.clear();
            articles.addAll(results);
            notifyDataSetChanged();
        }

        static class ItemHodler extends RecyclerView.ViewHolder {
            TextView title;
            ImageView imageView;

            ItemHodler(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.titleView);
                imageView = itemView.findViewById(R.id.image);
            }
        }
    }

    /**
     * Log information.
     */
    private void logInfo(String format, Object... args) {
        Logger.log(NewsFragment.class.getSimpleName(), String.format(format, args));
    }
}
