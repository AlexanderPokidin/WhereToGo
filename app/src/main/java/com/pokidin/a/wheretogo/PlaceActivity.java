package com.pokidin.a.wheretogo;

import android.app.Activity;
import android.os.Bundle;

public class PlaceActivity extends Activity {
    public static final String EXTRA_PLACENO = "placeNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        int placeNo = (Integer) getIntent().getExtras().get(EXTRA_PLACENO);
    }
}
