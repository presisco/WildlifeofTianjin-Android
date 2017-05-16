package com.wildlifeoftianjin.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.wildlifeoftianjin.data.sqlite.Column;
import com.wildlifeoftianjin.data.sqlite.SQLiteOpener;
import com.wildlifeoftianjin.data.sqlite.Table;
import com.wildlifeoftianjin.model.CreatureOverview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by presisco on 2017/4/18.
 */

public class SearchHistoryHelper {
    private static final String KEY_NAME = "name";
    private static final String KEY_IMAGE_URL = "image_url";
    private static final String KEY_TYPE = "type";
    private static final String TABLE_SEARCH_HISTORY = "search_history";
    private static final String DB_NAME = "search_history";

    private static final String[] COLUMNS = {KEY_NAME, KEY_IMAGE_URL, KEY_TYPE};
    private static final String STATEMENT_INSERT = "insert into "
            + TABLE_SEARCH_HISTORY
            + "(" + KEY_NAME + KEY_IMAGE_URL + KEY_TYPE
            + ") values ("
            + "?,?,?)";

    private static final Table[] TABLES = {
            Table.create(TABLE_SEARCH_HISTORY, new Column[]{
                    Column.create(KEY_NAME, "text primary key"),
                    Column.create(KEY_IMAGE_URL, "text")
            })
    };

    private SQLiteOpener mOpener = null;

    public SearchHistoryHelper(Context context) {
        mOpener = new SQLiteOpener(context, DB_NAME, 1, TABLES);
    }

    public List<CreatureOverview> getAllHistory() {
        List<CreatureOverview> histories = new ArrayList<>();
        Cursor cursor = mOpener.getReadableDatabase().query(TABLE_SEARCH_HISTORY, COLUMNS, null, null, null, null, null);
        while (cursor.moveToNext()) {
            CreatureOverview overview = new CreatureOverview();
            overview.setName(cursor.getString(0));
            overview.setImage_url(cursor.getString(1));
            overview.setType(cursor.getString(2));
            histories.add(overview);
        }
        cursor.close();
        return histories;
    }

    public void appendHistory(CreatureOverview new_history) {
        SQLiteDatabase db = mOpener.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(STATEMENT_INSERT);
        statement.bindString(1, new_history.getName());
        statement.bindString(2, new_history.getImage_url());
        statement.bindString(3, new_history.getType());
        statement.executeInsert();
    }
}
