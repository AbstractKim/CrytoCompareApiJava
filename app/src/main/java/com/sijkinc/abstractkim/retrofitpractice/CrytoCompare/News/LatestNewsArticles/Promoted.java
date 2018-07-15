package com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.News.LatestNewsArticles;

import com.google.gson.annotations.SerializedName;

@lombok.Data
public class Promoted {
    @SerializedName("id")
    private String id;
    @SerializedName("guid")
    private String guid;
    @SerializedName("published_on")
    private String published_on;
    @SerializedName("imageurl")
    public String imageurl;
    @SerializedName("title")
    public String title;
    @SerializedName("url")
    public String url;
    @SerializedName("source")
    private String source;
    @SerializedName("body")
    private String body;
    @SerializedName("tags")
    private String tags;
    @SerializedName("categories")
    private String categories;
    @SerializedName("lang")
    private String lang;
    @SerializedName("source_info")
    private SourceInfo source_info;

}
