package com.wildlifeoftianjin.UI.Frame.ClickTabsLayout;

import android.content.Context;

import java.util.Random;

/**
 * Created by presisco on 2016/4/11.
 */
public class ColorGenerator {
    int[] colors;

    public void initColorsFromResArray(Context context, int id) {
        colors = context.getResources().getIntArray(id);
    }

    public int getRandomColor() {
        Random r = new Random();
        return colors[r.nextInt(colors.length)];
    }
}
