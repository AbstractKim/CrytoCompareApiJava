package com.sijkinc.abstractkim.retrofitpractice;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class CoinNewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> newsList;

    public CoinNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coin_news, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_news);

        newsList = new ArrayList<>();
        adapter = new NewsAdapter(getActivity(), newsList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
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

        Observable<LatestNewsArticles> observableLatestNewsArticles = apiRxJava.latestNewsArticles(null, null, null, null, "EN", null, null, null);
        final Disposable subscribe = observableLatestNewsArticles
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(latestNewsArticles -> {
                            //Log.d(TAG, "NEWS_COUNT:" + latestNewsArticles.data.size());
                            prepareNews(latestNewsArticles.promoted, latestNewsArticles.data);
                        }
                );

        return view;
    }

    private void prepareNews(ArrayList<Promoted> p, ArrayList<Data> d) {

//        try {
//            Glide.with(this).load(p.get(0).imageurl).into((ImageView) recyclerView.findViewById(R.id.backdrop));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        d.forEach( dd -> {
            News a = new News(dd.title, dd.source, dd.imageurl, dd.body, dd.url);
            newsList.add(a);
        });

        adapter.notifyDataSetChanged();
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
