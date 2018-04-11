package com.example.sagarpandav.navigationdrawer;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sagarpandav.navigationdrawer.api.client.MyClient;
import com.example.sagarpandav.navigationdrawer.api.response.GoogleDirectionResponse;
import com.example.sagarpandav.navigationdrawer.api.response.Route;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String origin = "22.556209,72.92538";
    private String destination = "22.690912,72.863851";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        // Add a marker in Sydney and move the camera
        LatLng india = new LatLng(22.552573, 72.923813);
        mMap.addMarker(new MarkerOptions().position(india).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(india, 15f));

        drawline();
    }

    private void drawline() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyClient myClient = retrofit.create(MyClient.class);

        Call<GoogleDirectionResponse> call = myClient.getDirections(origin, destination, getResources().getString(R.string.google_maps_key));

//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                Log.e("Response :", "yes");
//
//                String dataString = response.body().toString();
//                Log.e("Data From Url", dataString);
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//
//            }
//        });

        call.enqueue(new Callback<GoogleDirectionResponse>() {
            @Override
            public void onResponse(Call<GoogleDirectionResponse> call, Response<GoogleDirectionResponse> response) {
                Log.e("Response", "Yes");

                GoogleDirectionResponse directionResponse = response.body();
                List<Route> routes = directionResponse.getRoutes();
                for (int i =0; i<routes.size(); i++){
                    String distance = routes.get(i).getLegs().get(i).getDistance().getText();
                    Log.e("Distance", distance);
                    String time = routes.get(i).getLegs().get(i).getDuration().getText();
                    String poly = routes.get(0).getOverviewPolyline().getPoints();
                    List<LatLng> latLngList = decodePoly(poly);
                    Polyline polyline = mMap.addPolyline(new PolylineOptions()
                    .addAll(latLngList)
                    .width(10)
                    .color(Color.RED)
                    .geodesic(true));
                }
            }

            @Override
            public void onFailure(Call<GoogleDirectionResponse> call, Throwable t) {
                Log.e("Response", "No");
            }
        });

    }

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }
}
