package com.example.sagarpandav.navigationdrawer;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sagar.pandav on 18/01/18.
 */

public class FragmentA extends Fragment {
    TextView textView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("fragment", "onAttach invoked");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("fragment", "onCreate invoked");

    }

    @Nullable


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        Log.e("fragment", "onCreateView invoked");

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView =(TextView) view.findViewById(R.id.fragmentTextView);
        Bundle b = getArguments();

        //Log.e("item_id", b.getInt("item")+"");

        textView.setText(b.getInt("item")+"");

        Log.e("fragment", "onViewCreated invoked");

    }

    public void changeData(int index){
        String []description = getResources().getStringArray(R.array.description);
        textView.setText(description[index]);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("fragment", "onActivityCreated invoked");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("fragment", "onStart invoked");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("fragment", "onResume invoked");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("fragment", "onPause invoked");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("fragment", "onSavedInstanceState invoked");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("fragment", "onStop invoked");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("fragment", "onDestroyView invoked");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("fragment", "onDestroy invoked");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("fragment", "onDetach invoked");
    }
}
