package com.sijkinc.abstractkim.retrofitpractice.CrytoCompare.News.LatestNewsArticles;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@lombok.Data
public class LatestNewsArticles {
    @SerializedName("Type")
    private String type;
    @SerializedName("Message")
    private String message;
    @SerializedName("Promoted")
    public ArrayList<Promoted> promoted;
    @SerializedName("Data")
    public ArrayList<Data> data;
}
