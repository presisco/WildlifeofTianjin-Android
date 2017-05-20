package com.wildlifeoftianjin.model;

/**
 * Created by presisco on 2017/5/16.
 */

public class RecordOverview {
    public String recordID;
    public String time;
    public String location;
    public String count;
    public String creatureID;
    public String scientificName;
    public String username;

    public RecordOverview() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCreatureID() {
        return creatureID;
    }

    public void setCreatureID(String creatureID) {
        this.creatureID = creatureID;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
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
}
