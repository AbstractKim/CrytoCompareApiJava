package com.sijkinc.abstractkim.retrofitpractice;

public class News {
    private String name;
    private Boolean isFavorite;
    private String src;
    private String thumbnail;

    public News() {
    }

    public News(String name, String src, String thumbnail) {
        this.name = name;
        this.src = src;
        this.thumbnail = thumbnail;
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
