package com.wildlifeoftianjin.model;

/**
 * Created by presisco on 2017/1/22.
 */

public class CreatureOverview {
    public static final int TYPE_ANIMAL = 0;
    public static final int TYPE_PLANT = 1;

    private String name;
    private String image_url;
    private int type;

    public CreatureOverview() {
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String _image_url) {
        image_url = _image_url;
    }

    public int getType() {
        return type;
    }

    public void setType(int _type) {
        type = _type;
    }
}
