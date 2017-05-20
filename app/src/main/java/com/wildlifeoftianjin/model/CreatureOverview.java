package com.wildlifeoftianjin.model;

/**
 * Created by presisco on 2017/1/22.
 */

public class CreatureOverview {
    public static final int TYPE_ANIMAL = 0;
    public static final int TYPE_PLANT = 1;

    public int id;
    public String name;
    public String image;
    public String type;

    public CreatureOverview() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String _image_url) {
        image = _image_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String _type) {
        type = _type;
    }
}
