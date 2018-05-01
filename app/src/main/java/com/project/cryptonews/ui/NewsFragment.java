package com.project.cryptonews.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.cryptonews.R;
import com.project.cryptonews.pojo.newsapi.Article;
import com.project.cryptonews.service.NewsapiService;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * News Fragment
 */

public class NewsFragment extends Fragment {

//    @BindView(R.id.list_content)
//    RecyclerView recyclerView;

    private List<Article> data = null;
    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = getLayoutInflater().inflate(R.layout.calulator_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        // FIXME: 18/03/18
//        data = new ArrayList<>();
//        recyclerView.setAdapter(new ListAdapter(getContext(), data));
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setVisibility(View.INVISIBLE);
//        downloadData();
        return view;
    }

    // FIXME: 18/03/18 This method should be refactored
    private void downloadData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(NewsapiService.BASE_END_POINT).
                addConverterFactory(GsonConverterFactory.create()).build();

        NewsapiService newsapiService  = retrofit.create(NewsapiService.class);
//        CoinMarketService service = retrofit.create(CoinMarketService.class);
//        service.getBitCoinData().enqueue(new Callback<Coin>() {
//            @Override
//            public void onResponse(Call<Coin> call, Response<Coin> response) {
//                Coin coin = response.body();
//                assert coin!=null;
//                logInfo("Price: ", coin.getPriceUsd());
//                logInfo("Percentage Change: ",  coin.getPercentChange24h());
//            }
//
//            @Override
//            public void onFailure(Call<Coin> call, Throwable t) {
//                logInfo("Failed");
//            }
//        });
//
    }

    @Override
    public String toString() {
        return "News";
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

    /**
     * View Holder
     */
    static class ItemHodler extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;


        ItemHodler(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleView);
            desc = itemView.findViewById(R.id.desc);
        }
    }

    private void logInfo(String format, Object... args) {
        Log.i(NewsFragment.class.getSimpleName(), String.format(format, args));
    }
}
