package com.project.cryptonews.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.cryptonews.R;
import com.project.cryptonews.pojo.coinmarket.Coin;
import com.project.cryptonews.pojo.newsapi.Article;
import com.project.cryptonews.service.ApiConstants;
import com.project.cryptonews.service.CoinMarketService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView = null;
    ListAdapter adapter = null;
    RecyclerView.LayoutManager layoutManager = null;
    Map<String, String> maps = null;
    List<Article> data = null;

    @Inject
    CoinMarketService coinMarketService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list_content);
        data = new ArrayList<>();
        adapter = new ListAdapter(this, data);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        downloadData();
    }

    private void downloadData() {
        Log.d(MainActivity.class.getSimpleName(), "Getting data");
        coinMarketService.getCoinWithId("bitcoin").enqueue(new Callback<List<Coin>>() {
            @Override
            public void onResponse(Call<List<Coin>> call, Response<List<Coin>> response) {
                List<Coin> coin = response.body();
                assert coin!=null;
                Log.d(MainActivity.class.getSimpleName(), coin.get(0).getPriceUsd());
                Log.d(MainActivity.class.getSimpleName(), coin.get(0).getPercentChange24h());
            }

            @Override
            public void onFailure(Call<List<Coin>> call, Throwable t) {
                Log.d(MainActivity.class.getSimpleName(), "Failed");
                Log.d(MainActivity.class.getSimpleName(), t.getMessage());
            }
        });
    }

    private static class ListAdapter extends RecyclerView.Adapter<ItemHodler> {

        private List<Article> articles = null;
        Context context;
        ListAdapter(Context context, List<Article> data) {
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
//            RequestOptions ro = new RequestOptions()
//                    .fitCenter()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL);
//            Glide.with(context).asBitmap().load(articles.get(position).getUrlToImage()).apply(ro).into(holder.image);
            holder.desc.setText(articles.get(position).getDescription());
        }

        @Override
        public int getItemCount() {
            return articles.size();
        }
    }

    static class ItemHodler extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;


        ItemHodler(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleView);
            desc = itemView.findViewById(R.id.desc);
        }
    }
}
