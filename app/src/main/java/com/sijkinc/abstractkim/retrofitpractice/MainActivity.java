package com.sijkinc.abstractkim.retrofitpractice;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.CrytoCompareApi;
import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.CrytoCompareApiRxJava;
import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.CrytoCompareService;
import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.News.LatestNewsArticles.Data;
import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.News.LatestNewsArticles.Promoted;
import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.News.LatestNewsArticles.LatestNewsArticles;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "PeterLuke";
    //private ListView listView;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> newsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        newsList = new ArrayList<>();
        adapter = new NewsAdapter(this, newsList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        /**
         * Retrofit Builder Part
         *   - add base url
         *   - add GsonConvertor for parsing json
         *
         *   get an interface for request as client
         */
        CrytoCompareApi api = CrytoCompareService.getCrytoCompareApi();
        CrytoCompareApiRxJava apiRxJava = CrytoCompareService.getCrytoCompareApiRxJava();

//        /**
//         * Using Generic Method
//         * request query using interface which is created by Retrofit
//         */
//
//        CrytoCompareService.requestAndConsume(api.topListByTotalVolume(10, "USD",null),
//                new ExcuteForResponse<Response<TopListByTotalVolume>>() {
//            @Override
//            public void apply(Response<TopListByTotalVolume> response) {
//                Log.d(TAG, response.body().toString());
//            }
//        }, new ExcuteForResponse<Throwable>() {
//            @Override
//            public void apply(Throwable t) {
//                Log.d(TAG, t.toString());
//            }
//        });
//
//        /**
//         * Using Generic Method (Namda)
//         * request query using interface which is created by Retrofit
//         */
//        CrytoCompareService.requestAndConsume(api.topListByTotalVolume(10, "USD", null),
//                response -> Log.d(TAG, "Nanma topListByTotalVolume -->" + response.body().toString()),
//                t-> Log.d(TAG, t.toString()));
//
//        /**
//         * Using RxJava
//         * request query using interface which is created by Retrofit
//         */
//
//        Observable<TopListByTotalVolume> observableTopListByTotalVolume = apiRxJava.topListByTotalVolume(10, "USD", null);
//        final Disposable subscribe = observableTopListByTotalVolume
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(topListByTotalVolume -> Log.d(TAG, "RXJAVA--> " + topListByTotalVolume.toString()));
//
//
//
//
//        CrytoCompareService.requestAndConsume(api.topListByTradingPairs("BTC", null, null, null),
//                response -> Log.d(TAG, "Nanma topListByTradingPairs -->" + response.body().toString()),
//                t-> Log.d(TAG, t.toString()));
//
//
//
//        Observable<TopListByPairVolume> observableTopListByPairVolume = apiRxJava.topListByPairVolume("BTC", null, null,null);
//        observableTopListByPairVolume
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(topListByPairVolume -> Log.d(TAG, "RXJAVA--> " + topListByPairVolume.toString()));

//        Observable<TopExchangesFullDataByPair> observableTopExchangesFullDataByPair = apiRxJava.topExchangesFullDataByPair("BTC", "USD", null, null,null);
//        observableTopExchangesFullDataByPair
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(topExchangesFullDataByPair -> Log.d(TAG, "RXJAVA--> " + topExchangesFullDataByPair.toString()));



//        Observable<TopExchangeVolumeDataByPair> observableTopExchangeVolumeDataByPair = apiRxJava.topExchangesVolumeDataByPair("BTC", "USD", null, null,null);
//        observableTopExchangeVolumeDataByPair
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(topExchangesVolumeByPair -> Log.d(TAG, "RXJAVA--> " + topExchangesVolumeByPair.toString()));

//        Observable<SingleSymbolPrice> observableSingleSymbolPrice = apiRxJava.singleSymbolPrice("BTC", "USD,EUR,JPY", null, null,null);
//        observableSingleSymbolPrice
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(singleSymbolPrice -> Log.d(TAG, "RXJAVA--> " + singleSymbolPrice.toString()));

//        Observable<MultipleSymbolPrice> observableMultipleSymbolPrice = apiRxJava.multipleSymbolPrice("BTC,ETH", "USD,EUR,JPY", null, null,null, null);
//        observableMultipleSymbolPrice
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(multipleSymbolPrice -> Log.d(TAG, "RXJAVA--> " + multipleSymbolPrice.toString()));

//        Observable<ListNewsFeeds> observableListNewsFeeds = apiRxJava.listNewsFeeds(null, null);
//        final Disposable subscribe = observableListNewsFeeds
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(listNewsFeeds -> Log.d(TAG, "RXJAVA--> " + listNewsFeeds.toString()));

//        CrytoCompareService.requestAndConsume(api.listNewsFeeds(null, null),
//                response -> Log.d(TAG, "Nanma listNewsFeeds -->" + response.body().toString()),
//                t-> Log.d(TAG, t.toString()));


         Observable<LatestNewsArticles> observableLatestNewsArticles = apiRxJava.latestNewsArticles(null, null, null, null, "EN", null, null, null);
         final Disposable subscribe = observableLatestNewsArticles
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(latestNewsArticles -> {
                    Log.d(TAG, "NEWS_COUNT:" + latestNewsArticles.data.size());
                    prepareNews(latestNewsArticles.promoted, latestNewsArticles.data);
                }
                );

//        CrytoCompareService.requestAndConsume(api.latestNewsArticles(null, null, null, null, "EN", null, null, null),
//                response -> Log.d(TAG, "Nanma latestNewsArticles -->" + response.body().toString()),
//                t-> Log.d(TAG, t.toString()));
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private void prepareNews(ArrayList<Promoted> p, ArrayList<Data> d) {

        try {
            Glide.with(this).load(p.get(0).imageurl).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

        d.forEach( dd -> {
            News a = new News(dd.title, dd.source, dd.imageurl);
            newsList.add(a);
        });

        adapter.notifyDataSetChanged();
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
