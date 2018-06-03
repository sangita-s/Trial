package generisches.lab.trial;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapp extends FragmentActivity implements OnMapReadyCallback,
LocationListener{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapp);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapstyle));


            if (!success) {
                Log.e("Mapp.java", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("Mapp.java", "Can't find style. Error: ", e);
        }

        LatLng current = getCurrentLatLng();
        mMap.addMarker(new MarkerOptions().position(current).title("Current location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private LatLng getCurrentLatLng() {
        double lat=0, lng=0;

        LocationManager lm;
        Location location;
        int permissiongps = 0;

        //if (ContextCompat.checkSelfPermission(Mapp.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, permissiongps);
            //Log.d("permission", Integer.toString(permissiongps));
            //lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            //location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            //lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10, this);
        //} else {
            /*Criteria crit = new Criteria();
            crit.setAccuracy(Criteria.ACCURACY_FINE);
            String best = lm.getBestProvider(crit, false);
            lm.requestLocationUpdates(best, 10000, 10, this);
            */
            //lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10, this);
            //lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, this);
            //Toast.makeText(Mapp.this, "Requested", Toast.LENGTH_SHORT).show();
            //lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            //lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, this);
            //location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        //}
        //lng = location.getLongitude();
        //lat = location.getLatitude();

        LatLng crt_loc = new LatLng(lat, lng);
        return crt_loc;
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
