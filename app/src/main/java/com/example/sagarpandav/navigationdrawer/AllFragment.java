package com.example.sagarpandav.navigationdrawer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sagar.pandav on 23/01/18.
 */

public class AllFragment extends Fragment {


    RecyclerView recyclerView;
    private AllDataAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_fragment, container, false);

        recyclerView =  (RecyclerView) view.findViewById(R.id.recyclerView);

        return view;
    }
}
