package com.wildlifeoftianjin.model;

/**
 * Created by presisco on 2017/1/22.
 */

public class Record {
    public String recordID;
    public String userID;
    public String creatureClass;
    public String time;
    public String location;
    public int count;
    public String description;

    public Record() {

    }

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCreatureClass() {
        return creatureClass;
    }

    public void setCreatureClass(String creatureClass) {
        this.creatureClass = creatureClass;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
