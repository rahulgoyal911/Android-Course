package com.example.rahul.a16memorableplaces;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                centerMapOnLocation(lastKnownLocation, "YourLocation");

            }
        }
    }

    public void centerMapOnLocation(Location location, String title) {
        if(location != null){

            try{

                mMap.clear();
                LatLng userLocation = new LatLng(10,10);
                mMap.addMarker(new MarkerOptions().position(userLocation).title(title));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 1));
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getApplicationContext(),"if",Toast.LENGTH_SHORT).show();
        }
        else
        {

            Toast.makeText(getApplicationContext(),"else",Toast.LENGTH_SHORT).show();

            mMap.clear();
            LatLng userLocation = new LatLng(10,10);
            mMap.addMarker(new MarkerOptions().position(userLocation).title(title));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 1));
            //
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent intent = getIntent();
        if (intent.getIntExtra("placeNumber", 0) == 0) {
            //zoon in the user location
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    centerMapOnLocation(location, "Your Location...");

                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                centerMapOnLocation(lastKnownLocation, "YourLocation");
            }





        }
    }
}
