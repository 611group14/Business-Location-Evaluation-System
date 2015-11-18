package com.uwin.bles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class EvaluationDetailsActivity extends AppCompatActivity {

    private LatLng userSelectedPosition;
    // To be deleted
    private static final String LAST_POSITION_LATLNG = "LatLng - last selected position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_details);

        this.initDataFromMainMap();

        ((TextView)findViewById(R.id.eval_details_text_view))
                .setText(this.userSelectedPosition.toString());
    }

    // Init data that delivered by main map activity
    private void initDataFromMainMap() {
        Intent intent = getIntent();

        this.userSelectedPosition = intent.getParcelableExtra(LAST_POSITION_LATLNG);
    }
}
