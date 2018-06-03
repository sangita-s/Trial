package generisches.lab.trial;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import generisches.lab.trial.activities.Wifi;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private CardView bankingCard, wifiCard, addCard, ideaCard, linkCard, mapCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //CollapsingToolbarLayout c_toolbar = findViewById(R.id.collapsingtoolbar);
        //c_toolbar.setTitle("Helooo");

        bankingCard = findViewById(R.id.banking_card);
        wifiCard = findViewById(R.id.wifi_card);
        addCard = findViewById(R.id.add_card);
        ideaCard = findViewById(R.id.ideas_card);
        linkCard = findViewById(R.id.links_card);
        mapCard = findViewById(R.id.map_card);

        bankingCard.setOnClickListener(this);
        wifiCard.setOnClickListener(this);
        addCard.setOnClickListener(this);
        linkCard.setOnClickListener(this);
        ideaCard.setOnClickListener(this);
        mapCard.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent i = new Intent(MainActivity.this, ChangeFontSizeColor.class);
            startActivity(i);

        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(MainActivity.this, UpperLower.class);
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(MainActivity.this, Graphic.class);
            startActivity(i);
        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(MainActivity.this, Calculator.class);
            startActivity(i);
        } else if (id == R.id.nav_tab) {
            Intent i = new Intent(MainActivity.this, FragmentTabs.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {
            Intent i = new Intent(MainActivity.this, GraphicPrimitive.class);
            startActivity(i);
        } else if (id == R.id.nav_send) {
            Intent i = new Intent(MainActivity.this, DBMain.class);
            startActivity(i);
        }
        else if (id == R.id.nav_RSS) {
            Intent i = new Intent(MainActivity.this, RSSFeed.class);
            startActivity(i);
        }
        else if (id == R.id.nav_Alarm) {
            Intent i = new Intent(MainActivity.this, SetAlarm.class);
            startActivity(i);
        }
        else if (id == R.id.nav_GPS) {
            Intent i = new Intent(MainActivity.this, GPS.class);
            startActivity(i);
        }
        else if (id == R.id.nav_multi) {
            Intent i = new Intent(MainActivity.this, MultiThread.class);
            startActivity(i);
        }
        else if (id == R.id.nav_SMS) {
            Intent i = new Intent(MainActivity.this, SMSRecieve.class);
            startActivity(i);
        }
        else if (id == R.id.nav_SD) {
            Intent i = new Intent(MainActivity.this, SDCard.class);
            startActivity(i);
        }
        else if(id == R.id.nav_notif){
            Intent i = new Intent(MainActivity.this, NofityMe.class);
            startActivity(i);
        }
        else if(id == R.id.nav_firebase){
            Intent i = new Intent(MainActivity.this, FirebaseAuthBasics.class);
            startActivity(i);
        }
        else if(id == R.id.nav_pb){
            Intent i = new Intent(MainActivity.this, Phonebook.class);
            startActivity(i);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.banking_card : i = new Intent(this, Bank.class); startActivity(i); break;
            case R.id.ideas_card : i = new Intent(this, Ideas.class); startActivity(i); break;
            case R.id.add_card : i = new Intent(this,Add.class); startActivity(i); break;
            case R.id.links_card : i = new Intent(this, Links.class); startActivity(i); break;
            case R.id.wifi_card : i = new Intent(this, Wifi.class); startActivity(i); break;
            case R.id.map_card : i = new Intent(this, Mapp.class); startActivity(i); break;
            default:break;
        }
    }
}
