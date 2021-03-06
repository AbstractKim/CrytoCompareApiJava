package com.sijkinc.abstractkim.retrofitpractice.CrytoCompare;

import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.News.LatestNewsArticles.LatestNewsArticles;
import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.News.ListNewsFeeds.ListNewsFeeds;
import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.Price.MultipleSymbolPrice.MultipleSymbolPrice;
import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.Price.SingleSymbolPrice.SingleSymbolPrice;
import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.Toplists.ByPairVolume.TopListByPairVolume;
import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.Toplists.ByTotalVolume.TopListByTotalVolume;
import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.Toplists.ByTradingPair.TopListByTradingPairs;
import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.Toplists.TopExchangesFullDataByPair.TopExchangesFullDataByPair;
import com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.Toplists.TopExchangesVolumeDataByPair.TopExchangeVolumeDataByPair;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CrytoCompareApi {

    /**********************************************************************************************
     * TopLists APi
     */
    /**
     Get a number of top coins by their total volume across all markets in the last 24 hours.
     default value is first page (0) and the top 10 coins.
     -@param limit  The number of data points to return
     -@param tsym  The currency symbol to convert into
     -@param sing If set to true, the server will sign the requests (be default we don't sign them),
             this is useful for usage in smart contracts
     */
    @GET("data/top/totalvol")
    Call<TopListByTotalVolume> topListByTotalVolume(@Query("limit") Integer limit,
                                                    @Query("tsym") String tsym,
                                                    @Query("sing") Boolean sign);

    /**
     * Get top pairs by volume for a currency (always uses our aggregated data).
     * The number of pairs you get is the minimum of the limit you set (default 5) and the total
     * number of pairs available
     *  -@param fsym  The cryptocurrency symbol of interest [Max character length: 10]
     *  -@param limit  The number of data points to return
     *  -@param extraParams  The name of your application (we recommend you send it)
     *                       [Max character length: 50]
     *  -@param sign  If set to true, the server will sign the requests (be default we don't sign
     *                them), this is useful for usage in smart contracts
     */
    @GET("data/top/pairs")
    Call<TopListByTradingPairs> topListByTradingPairs(@Query("fsym") String fsym,
                                                      @Query("limit")Integer limit,
                                                      @Query("extraParams") String extraParams,
                                                      @Query("Sign") Boolean sign);

    /**
     * Get top coins by volume for the to currency. It returns volume24hto and total supply
     * (where available). The number of coins you get is the minimum of the limit you set (
     * default 50) and the total number of coins available
     *  -@param tsym  The currency symbol to convert into
     *  -@param limit  The number of data points to return
     *  -@param extraParams  The name of your application (we recommend you send it)
     *                       [Max character length: 50]
     *  -@param sign  If set to true, the server will sign the requests (be default we don't sign
     *                them), this is useful for usage in smart contracts
     */
    @GET("data/top/volumes")
    Call<TopListByPairVolume> topListByPairVolume(@Query("tsym") String tsym);

    /**
     * Get top exchanges by volume for a currency pair plus the full CCCAGG data.
     * The number of exchanges you get is the minimum of the limit you set (default 5) and the total
     * number of exchanges available
     *  - @param fsym  The cryptocurrency symbol of interest [Max character length: 10]
     *  - @param tsym  The currency symbol to convert into [Max character length: 10]
     *  -@param limit  The number of data points to return
     *  -@param extraParams  The name of your application (we recommend you send it)
     *                       [Max character length: 50]
     *  -@param sign  If set to true, the server will sign the requests (be default we don't sign
     *                them), this is useful for usage in smart contracts
     */
    @GET("data/top/exchanges/full")
    Call<TopExchangesFullDataByPair> topExchangesFullDataByPair(@Query("fsym") String fsym,
                                                                @Query("tsym") String tsym,
                                                                @Query("limit")Integer limit,
                                                                @Query("extraParams") String extraParams,
                                                                @Query("Sign") Boolean sign);

    /**
     * Get top exchanges by volume for a currency pair. The number of exchanges you get is the
     * minimum of the limit you set (default 5) and the total number of exchanges available
     *  - @param fsym  The cryptocurrency symbol of interest [Max character length: 10]
     *  - @param tsym  The currency symbol to convert into [Max character length: 10]
     *  -@param limit  The number of data points to return
     *  -@param extraParams  The name of your application (we recommend you send it)
     *                       [Max character length: 50]
     *  -@param sign  If set to true, the server will sign the requests (be default we don't sign
     *                them), this is useful for usage in smart contracts
     */
    @GET("data/top/exchanges")
    Call<TopExchangeVolumeDataByPair> topExchangesVolumeDataByPair(@Query("fsym") String fsym,
                                                                 @Query("tsym") String tsym,
                                                                 @Query("limit")Integer limit,
                                                                 @Query("extraParams") String extraParams,
                                                                 @Query("Sign") Boolean sign);

    /**********************************************************************************************
     * Price APi
     */

    /**
     * Get the current price of any cryptocurrency in any other currency that you need.
     * If the crypto does not trade directly into the toSymbol requested, BTC will be used for conversion.
     * If the oposite pair trades we invert it (eg.: BTC-XMR)
     *  - @param fsym  The cryptocurrency symbol of interest [Max character length: 10]
     *  - @param tsyms  Comma separated cryptocurrency symbols list to convert into [Max character length: 500]
     *  - @param e  The exchange to obtain data from (our aggregated average - CCCAGG - by default) [Max character length: 30]
     *  - @param extraParams  The name of your application (we recommend you send it)
     *                       [Max character length: 50]
     *  - @param sign  If set to true, the server will sign the requests (be default we don't sign
     *                them), this is useful for usage in smart contracts
     */

    @GET("data/price")
    Call<SingleSymbolPrice> singleSymbolPrice(@Query("fsym") String fsym,
                                              @Query("tsyms") String tsyms,
                                              @Query("e")String e,
                                              @Query("extraParams") String extraParams,
                                              @Query("Sign") Boolean sign);

    /**********************************************************************************************
     * Price APi
     */

    /**
     * Get the current price of any cryptocurrency in any other currency that you need.
     * If the crypto does not trade directly into the toSymbol requested, BTC will be used for conversion.
     * If the oposite pair trades we invert it (eg.: BTC-XMR)
     *  - @param fsyms  Comma separated cryptocurrency symbols list [Max character length: 300]
     *  - @param tsyms  Comma separated cryptocurrency symbols list to convert into [Max character length: 100]
     *  - @param tryConversion  If set to false, it will try to get only direct trading values
     *  - @param e  The exchange to obtain data from (our aggregated average - CCCAGG - by default) [Max character length: 30]
     *  - @param extraParams  The name of your application (we recommend you send it)
     *                       [Max character length: 50]
     *  - @param sign  If set to true, the server will sign the requests (be default we don't sign
     *                them), this is useful for usage in smart contracts
     */

    @GET("data/pricemulti")
    Call<MultipleSymbolPrice> multipleSymbolPrice(@Query("fsyms") String fsyms,
                                                  @Query("tsyms") String tsyms,
                                                  @Query("tryConversion") Boolean tryConversion,
                                                  @Query("e")String e,
                                                  @Query("extraParams") String extraParams,
                                                  @Query("Sign") Boolean sign);

    /**********************************************************************************************
     * News APi
     */

    /**
     * Returns news articles from the providers that CryptoCompare has integrated with.
     *  - @param feeds  Specific news feeds to retrieve news from [Max character length: 400]
     *  - @param categories  	Category of news articles to return [Max character length: 400]
     *  - @param excludeCategories  	News article categories to exclude from results [Max character length: 400]
     *  - @param lTs  Returns news before that timestamp
     *  - @param sortOrder  The order to return news articles - latest or popular [Max character length: 8]
     *  - @param extraParams  The name of your application (we recommend you send it) [Max character length: 50]
     *  - @param sign  If set to true, the server will sign the requests (be default we don't sign them), this is useful for usage in smart contracts
     */
    @GET("data/v2/news/")
    Call<LatestNewsArticles> latestNewsArticles(@Query("feeds") String feeds,
                                                 @Query("categories") String categories,
                                                 @Query("excludeCategories") String excludeCategories,
                                                 @Query("lTs") Boolean its,
                                                 @Query("lang")String lang,
                                                 @Query("sortOrder") String sortOrder,
                                                 @Query("extraParams") String extraParams,
                                                 @Query("Sign") Boolean sign);

    /**
     * Returns all the news feeds (providers) that CryptoCompare has integrated with.
     *  - @param extraParams  Thee name of your application (we recommend you send it) [Max character length: 50]
     *  - @param sign  If set to true, the server will sign the requests (be default we don't sign them), this is useful for usage in smart contracts
     */
    @GET("data/news/feeds")
    Call<ListNewsFeeds> listNewsFeeds(@Query("extraParams") String extraParams,
                                      @Query("Sign") Boolean sign);

}
