package com.example.sagarpandav.navigationdrawer;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sagar.pandav on 23/01/18.
 */

public class FindFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_fragment, container, false);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try{
            String urlString = "http://localhost:57239/api/Bus/getroute?from=first&to=fourth&bus=105";
            JSONObject jsonObject = getJSONObjectFromURL(urlString);
            JSONArray data = jsonObject.getJSONArray("");
            if(data != null) {
                String[] time = new String[data.length()];
                String[] stationName = new String[data.length()];
                for (int i = 0; i < data.length(); i++) {
                    time[i] = data.getString(i);
                    stationName[i] = data.getString(i);
                    Log.e("Data", stationName[i]);
                }
            }
            } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            Log.e("Other Exception", String.valueOf(e));
        }
    }

    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }
}
