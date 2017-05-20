package com.wildlifeoftianjin.network;

/**
 * Created by presisco on 2017/1/22.
 */

public class Constants {
    public static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";

    public static final String HOST = "http://tianjinwildlife.applinzi.com/Home/Message/";
    public static final String PATH_SEARCH_FRONT_PAGE = HOST + "";
    public static final String PATH_SEARCH_CREATURE = HOST + "getAnimalList";
    public static final String PATH_REQUEST_CREATURE = HOST + "getAnimalById";
    public static final String PATH_REQUEST_ARTICLE_LIST = HOST + "";
    public static final String PATH_REQUEST_CREATURE_RECORD_LIST = HOST + "getRecordListByAnimalId";
    public static final String PATH_REQUEST_USER_RECORD_LIST = HOST + "getRecordListByUserId";
    public static final String PATH_REQUEST_RECORD = HOST + "getRecordByRecordID";
    public static final String PATH_UPLOAD_RECORD = HOST + "addRecord";
    public static final String PATH_SIGN_UP = HOST + "registerUser";
    public static final String PATH_SIGN_IN = HOST + "login";
}
