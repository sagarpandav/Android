package com.example.sagarpandav.navigationdrawer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

     private Toolbar toolbar;
     private LoginFragment loginFragment;
     private MapFragment mapFragment;
     private AllFragment allFragment;
     private FindFragment findFragment;
     private FragmentQRView qrFragment;
     FragmentManager fragmentManager;
     FragmentTransaction fragmentTransaction;
     DrawerLayout drawer;
     ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        //HomeFragment homeFragment = new HomeFragment();
        //FindFragment findFragment = new FindFragment();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.fragment_container, loginFragment, "loginFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount()==1){
            finish();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        setTitle(String.valueOf(item));

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        loginFragment = new LoginFragment();
        mapFragment = new MapFragment();
        allFragment = new AllFragment();
        findFragment = new FindFragment();
        qrFragment = new FragmentQRView();

        if (id == R.id.Login)
        {
            Log.i("Log","loginFragent Created");

            fragmentTransaction.replace(R.id.fragment_container, loginFragment, "loginFragment");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else if (id == R.id.Map) {

            //Log.i("Log","mapFragent Created");

            //fragmentTransaction.replace(R.id.fragment_container, mapFragment, "mapFragment");
            //fragmentTransaction.addToBackStack(null);

            //fragmentTransaction.commit();

            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);


        } else if (id == R.id.All_Station) {

            fragmentTransaction.replace(R.id.fragment_container, allFragment, "allFragment");
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.commit();

            Log.i("Log","allFragment Created");


        } else if (id == R.id.Find) {

            fragmentTransaction.replace(R.id.fragment_container, findFragment, "findFragment");
            fragmentTransaction.addToBackStack(String.valueOf(loginFragment));

            fragmentTransaction.commit();

            Log.i("Log","findFragment Created");

        }else if (id == R.id.QR){

            fragmentTransaction.replace(R.id.fragment_container, qrFragment, "qrFragment");
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.commit();

            Log.e("Log", "Qr Fragment Created");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
