package com.pokidin.a.wheretogo;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PlaceListActivity extends ListActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listPlaces = getListView();
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Intent intent = new Intent(PlaceListActivity.this, PlaceActivity.class);
        intent.putExtra(PlaceActivity.EXTRA_PLACENO, (int)id);
        startActivity(intent);
    }
}
