package com.example.sagarpandav.navigationdrawer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.SearchView;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagar.pandav on 23/01/18.
 */

public class AllFragment extends Fragment implements SearchView.OnQueryTextListener {


    RecyclerView recyclerView;
    SearchView searchView;
    private AllDataAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        adapter = new AllDataAdapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(getActivity()).getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);

        searchView = view.findViewById(R.id.searchViewAll);
        searchView.setOnQueryTextListener(this);

        return view;

    }

    public static List<Data> getData(){
        List<Data> data = new ArrayList<>();
        int []icons = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine, R.drawable.ten};
        String []title = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};

        for (int i=0; i<title.length && i<icons.length; i++){

            Data current = new Data();
            current.iconId = icons[i];
            current.title = title[i];
            data.add(current);
        }

        return data;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        searchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.getFilter().filter(s);
        return true;
    }
}
