package com.pokidin.a.wheretogo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alexander Pokidin on 26.08.2017.
 */

class PlacesDatabaseHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "places";
    private final static int DB_VERSION = 1;

    public PlacesDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PLACES (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "DESCRIPTION TEXT, "
                + "IMAGE_ID INTEGER, "
                + "ROUTE TEXT, "
                + "CATEGORY TEXT);");
        insertPlace(db, "Victory Park", "Victory Park in Kiev is a green area with a garden of stones " +
                        "and various attractions. On the main avenue of Victory Park is the Mound of Immortality.",
                R.drawable.park_pobeda_image, "Near metro station Darnitsa", "park");
        insertPlace(db, "Park of Partisan Glory", "Most of the park is a pine forest, " +
                        "which has been preserved almost intact. In the park zone there are lakes.",
                R.drawable.park_partisan_image, "Near the metro station Red Khutor", "park");
        insertPlace(db, "Kiev-Pechersk Lavra", "Built by Kiev monks back in 1051, " +
                        "this striking building is the most holy place in the Ukraine. " +
                        "The caves on the property serve as burial grounds for monks.", R.drawable.lavra_image,
                "Near the metro station Arsenalnaya", "church");
        insertPlace(db, "Saint Sophia Cathedral", "The cathedral was built over nine centuries " +
                        "and is a great example of Byzantine and Ukrainian Baroque architecture. " +
                        "The interior contains mosaics and frescoes dating back to the 11th century.",
                R.drawable.sofijskij_sobor_image, "Near the metro station Golden gate", "church");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertPlace(SQLiteDatabase db, String name, String description, int imageId,
                                    String route, String category) {
        ContentValues plaseValues = new ContentValues();
        plaseValues.put("NAME", name);
        plaseValues.put("DESCRIPTION", description);
        plaseValues.put("IMAGE_ID", imageId);
        plaseValues.put("ROUTE", route);
        plaseValues.put("CATEGORY", category);
        db.insert("PLACES", null, plaseValues);
    }
}
