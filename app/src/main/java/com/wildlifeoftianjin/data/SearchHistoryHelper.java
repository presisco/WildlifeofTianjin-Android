package com.wildlifeoftianjin.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.wildlifeoftianjin.data.sqlite.Column;
import com.wildlifeoftianjin.data.sqlite.SQLiteOpener;
import com.wildlifeoftianjin.data.sqlite.Table;

/**
 * Created by presisco on 2017/4/18.
 */

public class SearchHistoryHelper {
    private static final String KEY_ID = "_id";
    private static final String KEY_CONTENT = "search_content";
    private static final String TABLE_SEARCH_HISTORY = "search_history";

    private static final String[] COLUMNS = {KEY_ID, KEY_CONTENT};
    private static final String STATEMENT_INSERT = "insert into "
            + TABLE_SEARCH_HISTORY
            + "(" + KEY_CONTENT
            + ") values (?)";

    private static final Table[] TABLES = {
            Table.create(TABLE_SEARCH_HISTORY, new Column[]{
                    Column.create(KEY_ID, "integer primary key autoincrement"),
                    Column.create(KEY_CONTENT, "text")
            })
    };

    private SQLiteOpener mOpener = null;

    public SearchHistoryHelper(Context context) {
        mOpener = new SQLiteOpener(context, Constant.APP_DATABASE, 1, TABLES);
    }

    public Cursor getRecentHistoryCursor() {
        return mOpener.getReadableDatabase().query(TABLE_SEARCH_HISTORY, new String[]{KEY_ID, KEY_CONTENT}, null, null, null, null, KEY_ID + " DESC", "6");
    }

    public void addHistory(String search_content) {
        SQLiteDatabase db = mOpener.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(STATEMENT_INSERT);
        statement.bindString(1, search_content);
        statement.executeInsert();
    }
}
