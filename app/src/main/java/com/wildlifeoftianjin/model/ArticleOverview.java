package com.wildlifeoftianjin.model;

/**
 * Created by presisco on 2017/1/22.
 */

public class ArticleOverview {
    private String title;
    private String overview;
    private String date;
    private String author;
    private String image_url;

    public ArticleOverview() {
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String _image_url) {
        image_url = _image_url;
    }
}
