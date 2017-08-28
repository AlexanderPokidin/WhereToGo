package com.pokidin.a.wheretogo;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class PlaceListActivity extends ListActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listPlaces = getListView();

        try {
            SQLiteOpenHelper placesDatabaseHelper = new PlacesDatabaseHelper(this);
            db = placesDatabaseHelper.getReadableDatabase();

            cursor = db.query("PLACES",
                    new String[] {"_id", "NAME"},
                    "CATEGORY = ?",
                    new String[] {"park"},
                    null, null, null);
            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[] {"NAME"},
                    new int[] {android.R.id.text1},
                    0);
            listPlaces.setAdapter(listAdapter);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Intent intent = new Intent(PlaceListActivity.this, PlaceActivity.class);
        intent.putExtra(PlaceActivity.EXTRA_PLACENO, (int)id);
        startActivity(intent);
    }
}
