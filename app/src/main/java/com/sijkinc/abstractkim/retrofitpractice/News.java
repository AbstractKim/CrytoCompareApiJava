package com.sijkinc.abstractkim.retrofitpractice;

public class News {
     private String name;
     private Boolean isFavorite;
     private String src;
     private String body;
     private String url;
     private String thumbnail;
 
             public News() {
             }
 
             public News(String name, String src, String thumbnail,  String body, String url) {
                 this.name = name;
                 this.src = src;
                 this.thumbnail = thumbnail;
                 this.body = body;
                 this.url = url;
             }
 
             public String getName() {
                 return name;
             }
 
             public void setName(String name) {
                 this.name = name;
             }
 
             public String getSource() {
                 return src;
             }

             public String getBody() {
                 return body;
             }

             public String getUrl() {
                 return url;
             }
 
             public void setSource(String src) {
                 this.src = src;
             }
 
             public String getThumbnail() {
                 return thumbnail;
             }
 
             public void setThumbnail(String thumbnail) {
                 this.thumbnail = thumbnail;
             }
     public boolean isFavorite(){
               return isFavorite;
             }
 
             public void setFavorite(boolean favorite){
               isFavorite = favorite;
             }
}
