package com.example.sagarpandav.navigationdrawer;


import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by sagar.pandav on 23/01/18.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {

    MapView mapView;
    private GoogleMap mMap;


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);

        mapView = (MapView) view.findViewById(R.id.map_fragment_1);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map_fragment_1)  ;
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng india = new LatLng(22.552573, 72.923813);
        mMap.addMarker(new MarkerOptions().position(india).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(india, 15f));
    }
}