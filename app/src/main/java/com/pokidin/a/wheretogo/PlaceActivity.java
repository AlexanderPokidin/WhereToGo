package com.pokidin.a.wheretogo;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceActivity extends Activity {
    public static final String EXTRA_PLACENO = "placeNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        int placeNo = (Integer) getIntent().getExtras().get(EXTRA_PLACENO);

        try {
            SQLiteOpenHelper placesDatabaseHelper = new PlacesDatabaseHelper(this);
            SQLiteDatabase db = placesDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("PLACES",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_ID", "ROUTE"},
                    "_id = ?",
                    new String[]{Integer.toString(placeNo)},
                    null, null, null);
            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                String routeText = cursor.getString(3);

                TextView name = (TextView) findViewById(R.id.name);
                name.setText(nameText);
                TextView description = (TextView) findViewById(R.id.description);
                description.setText(descriptionText);
                ImageView photo = (ImageView) findViewById(R.id.photo);
                photo.setImageResource(photoId);
                TextView route = (TextView) findViewById(R.id.route);
                route.setText(routeText);
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
