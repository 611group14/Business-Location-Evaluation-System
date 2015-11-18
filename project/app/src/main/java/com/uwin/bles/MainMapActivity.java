package com.uwin.bles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.uwin.bles.utility.LatLngPosition;

public class MainMapActivity extends AppCompatActivity implements
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnInfoWindowClickListener,
        OnMapReadyCallback {

    // Constant strings for Extra keys
    private static final String LAST_POSITION_LATLNG = "LatLng - last selected position";

    // Main map
    private GoogleMap mMap;
    private LatLng defaultPosition = LatLngPosition.WINDSOR;
    private LatLng lastSelectedPosition = this.defaultPosition;
    private float defaultZoom = 12f;

    // Main map components
    private Marker currentVisibleMarker;

    // Other UI components
    private TextView instructionTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.main_map_fragment);
        mapFragment.getMapAsync(this);

        // Init instruction box.
        this.instructionTextView = (TextView)findViewById(R.id.main_map_instruction_text_view);

        // Initial instruction texts
        this.updateInstruction(R.string.main_map_instr_pick_position);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Init main map and action listeners.
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

        // Move the map so that it is centered on the initial position
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(this.defaultPosition, this.defaultZoom));

    }

    @Override
    public void onMapLongClick(LatLng point) {

        // Save the selected position
        this.lastSelectedPosition = point;

        mMap.clear();
        this.currentVisibleMarker = mMap.addMarker(new MarkerOptions()
                .position(point).draggable(true)
                .title("Evaluate This Location"));
        this.currentVisibleMarker.showInfoWindow();
        this.updateInstruction(R.string.main_map_instr_drag_marker);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        //Toast.makeText(this, "Evaluation page not ready", Toast.LENGTH_SHORT).show();

        marker.hideInfoWindow();
        this.goToEvaluationDetails();
    }


    // Instruction texts manipulation
    private void updateInstruction(int instrTextResid) {
        this.instructionTextView.setText(instrTextResid);
    }

    private void clearInstruction() {
        this.instructionTextView.setText("");
    }


    // Call for Evaluation Details Page
    private void goToEvaluationDetails() {
        Intent intent = new Intent(this, EvaluationDetailsActivity.class);
        intent.putExtra(LAST_POSITION_LATLNG, this.lastSelectedPosition);
        startActivity(intent);
    }

}
