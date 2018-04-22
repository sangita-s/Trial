package generisches.lab.trial;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class GPS extends AppCompatActivity implements LocationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        int permissiongps = 0;

        if (ContextCompat.checkSelfPermission(GPS.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, permissiongps);
            Log.d("permission", Integer.toString(permissiongps));
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10, this);
        } else {
            /*Criteria crit = new Criteria();
            crit.setAccuracy(Criteria.ACCURACY_FINE);
            String best = lm.getBestProvider(crit, false);
            lm.requestLocationUpdates(best, 10000, 10, this);
            */
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10, this);
            //lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, this);
            Toast.makeText(GPS.this, "Requested", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        String s = "Latitude : " + location.getLatitude() + " Longitude : " + location.getLongitude();
        Toast.makeText(GPS.this, s, Toast.LENGTH_SHORT).show();
        TextView latlon = findViewById(R.id.txtlatlon);
        latlon.setText(s);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(GPS.this, "Inside status changed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(GPS.this, "GPS is ON", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(GPS.this, "GPS is OFF", Toast.LENGTH_SHORT).show();

    }
}
