package com.project.cryptonews.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.cryptonews.R;
import com.project.cryptonews.data.eventregistry.ERResponse;
import com.project.cryptonews.data.eventregistry.Result;
import com.project.cryptonews.service.eventregistry.EventRegistryService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * News Fragment
 */

public class NewsFragment extends Fragment {

    //Recycler view instance
    @BindView(R.id.list_content)
    RecyclerView recyclerView;

    //Data cache
    private List<Result> data = null;

    //ButterKnife binder
    private Unbinder unbinder;

    //List View Adapter
    ListAdapter adapter;

    /**
     * on create view call back
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = getLayoutInflater().inflate(R.layout.activity_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        data = new ArrayList<>();
        adapter = new ListAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        downloadData();
        return view;
    }

    private void downloadData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://eventregistry.org/json/").addConverterFactory(GsonConverterFactory.create()).build();
        EventRegistryService service = retrofit.create(EventRegistryService.class);

        //TODO Try to send JSON and parameters as a query.
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

        service.getSearchData(map).enqueue(new Callback<ERResponse>() {
            @Override
            public void onResponse(Call<ERResponse> call, Response<ERResponse> response) {
                logInfo("Success");
                logInfo("Status code: "+response.code());
                if(response.body()!=null) {
                    data.clear();
                    ERResponse erResponse = response.body();
                    if(erResponse.getArticles() != null) {
                        data.addAll(response.body().getArticles().getResults());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "No results", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ERResponse> call, Throwable t) {
                logInfo("Failed");
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public String toString() {
        return "News Fragment";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        data.clear();
    }

    /**
     * Adapter
     */
    private static class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemHodler> {

        private List<Result> articles = null;
        Context context;

        ListAdapter(Context context, List<Result> data) {
            this.context = context;
            this.articles = data;
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

    private void logInfo(String format, Object... args) {
        Log.i(NewsFragment.class.getSimpleName(), String.format(format, args));
    }
}
