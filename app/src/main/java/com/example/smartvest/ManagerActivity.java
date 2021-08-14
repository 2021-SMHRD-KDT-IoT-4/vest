package com.example.smartvest;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.smartvest.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.smartvest.databinding.ActivityMapsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManagerActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    UserVO vo ;
    List<UserVO> data;
    LatLng smart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        data = new ArrayList<>();

        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        Log.v("한국어박성민", String.valueOf(vo.getLongitude()));
//        Log.v("한국어박성민1", String.valueOf(vo.getLatitude()));
//        Log.v("한국어박성민2", String.valueOf(vo.getAltitude()));


//        String db_url="https://smartvest-2881b-default-rtdb.firebaseio.com/";
//        FirebaseDatabase database = FirebaseDatabase.getInstance(db_url);
//        DatabaseReference myRef = database.getReference("user");
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//               UserVO vo = snapshot.getValue(UserVO.class);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            Log.w("오류",error.toException());
//            }
//        });




    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent intent =getIntent();
        double latitude= intent.getExtras().getFloat("latitude");
        final  Double longitude= intent.getExtras().getDouble("longitude");
        final Float altitude= intent.getExtras().getFloat("altitude");
        final String provider= intent.getExtras().getString("provider");

        // Add a marker in Sydney and move the camera
        for( double i = 0; i < 0.005; i+=0.0005){
            smart = new LatLng(latitude + i,longitude+i);
            if(latitude == latitude+i){
                mMap.addMarker(new MarkerOptions().position(smart).title("사용자" + i*2000)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }else{
                mMap.addMarker(new MarkerOptions().position(smart).title("사용자" + i*2000)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            }
        }


        mMap.moveCamera(CameraUpdateFactory.newLatLng(smart));
        CameraUpdate cUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude),altitude);
//        mMap.setMaxZoomPreference(15);

        mMap.moveCamera(cUpdate);

    }


    LocationManager mLocationManager;

    private Location getLastKnownLocation() {
        mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {

            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ManagerActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        0);
            }else{
                Location l = mLocationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }


        }
        return bestLocation;
    }






}