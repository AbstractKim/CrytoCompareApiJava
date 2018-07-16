package com.sijkinc.abstractkim.retrofitpractice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.CrytoCompareApiRxJava;
import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.CrytoCompareService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CoinListFragment extends Fragment {
    private static final String TAG = CoinListFragment.class.getSimpleName();
    public CoinListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @SuppressLint("CheckResult")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coin_list, container, false);

        CoinInfoForCardView.COMPLETED_NUM = 0;

        CrytoCompareApiRxJava crytoCompareApiRxJava = CrytoCompareService.getCrytoCompareApiRxJava();
        crytoCompareApiRxJava.topListByTotalVolume(10, "USD",null)
                .subscribeOn(Schedulers.io())
                .map(topListByTotalVolume ->
                {
                    CoinInfoForCardView.COIN_TOTAL_NUMBER = topListByTotalVolume.getData().size();
                    return  topListByTotalVolume.getData();
                 })
                .flatMapIterable(data -> data)
                .map(item -> new CoinInfoForCardView(
                        item.getCoinInfo().getName(),
                        item.getCoinInfo().getFullName()
                        ))
                .toList()
                .subscribe(coinInfoForCardViews -> {
                    CoinInfoForCardView.getMyCompletable().observeOn(AndroidSchedulers.mainThread())
                            .subscribe(()->
                            {
                                Log.d(TAG, "create recycle view");
                                RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                                        LinearLayoutManager.VERTICAL, false));

                                CoinCardRecyclerViewAdapter adapter = new CoinCardRecyclerViewAdapter(
                                        coinInfoForCardViews);

                                recyclerView.setAdapter(adapter);
                            });
                });



//        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
//                LinearLayoutManager.VERTICAL, false));
//
//
//        CoinInfo coinInfo1 = new CoinInfo();
//        coinInfo1.setId("BTC");
//        CoinInfo coinInfo2 = new CoinInfo();
//        coinInfo2.setId("ETH");
//
//        CoinCardRecyclerViewAdapter adapter = new CoinCardRecyclerViewAdapter(
//                Arrays.asList(coinInfo1, coinInfo2));
//
//        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }

}
