package com.example.rahul.a15hikerswatch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView lonTextView;
    TextView latTextView;
    TextView altTextView;
    TextView accTextView;
    TextView addTextView;
    String address;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addTextView = (TextView)findViewById(R.id.addTextView);
        lonTextView = (TextView)findViewById(R.id.lonTextView);
        latTextView = (TextView)findViewById(R.id.latTextView);
        altTextView = (TextView)findViewById(R.id.altTextView);
        accTextView = (TextView)findViewById(R.id.accTextView);



        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

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


        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(lastKnownLocation != null){
                updateLocationInfo(lastKnownLocation);
            }

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            startListening();
        }
    }
    public void startListening(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    public void updateLocationInfo(Location location){
        latTextView.setText("Latitude: "+ Double.toString(location.getLatitude()));
        lonTextView.setText("Longitude: "+ Double.toString(location.getLongitude()));
        altTextView.setText("Altitude: "+ Double.toString(location.getAltitude()));
        accTextView.setText("Accuracy: "+ Double.toString(location.getAccuracy()));
       // addTextView.setText("Address: "+ Double.toString(location.getA));

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try{
            List<Address> listAddress = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            if(listAddress != null && listAddress.size() >0){
                address = "Address:\n";
                if(listAddress.get(0).getSubLocality() != null){
                    address += listAddress.get(0).getSubLocality() + "\n";
                }
                if(listAddress.get(0).getThoroughfare() != null){
                    address += listAddress.get(0).getThoroughfare() + "\n";
                }
                if(listAddress.get(0).getLocality() != null){
                    address += listAddress.get(0).getLocality() + "\n";
                }
                if(listAddress.get(0).getPostalCode() != null){
                    address += listAddress.get(0).getPostalCode() + "\n";
                }
                if(listAddress.get(0).getAdminArea() != null){
                    address += listAddress.get(0).getAdminArea() ;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        addTextView.setText(address);


    }
}
