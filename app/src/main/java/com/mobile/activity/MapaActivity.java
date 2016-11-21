package com.mobile.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobile.R;

import java.util.ArrayList;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static ArrayList<Activity> activities=new ArrayList<Activity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        activities.add(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button btnVoltarDoMapa = (Button) findViewById(R.id.btnVoltarDoMapa);
        btnVoltarDoMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapaActivity.this, PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng fortaleza = new LatLng(-3.735967, -38.534558);
        mMap.addMarker(new MarkerOptions()
                .position(fortaleza)
                .title("Habitat Pet")
                .snippet("Av. da Universidade, 2057, Benfica, Fortaleza - CE"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fortaleza, 18));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        activities.remove(this);
    }

    // Impede o caboco voltar pelo android.
    @Override
    public void onBackPressed() {
        //return nothing
        return;
    }
}
