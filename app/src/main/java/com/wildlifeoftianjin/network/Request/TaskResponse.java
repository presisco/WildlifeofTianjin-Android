package com.wildlifeoftianjin.network.Request;

/**
 * Created by presisco on 2017/5/16.
 */

public interface TaskResponse<T> {
    void onResponse(T response);
}
