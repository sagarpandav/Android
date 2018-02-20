package com.example.sagarpandav.navigationdrawer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by panda on 21-Feb-18.
 */

public class SigninFragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    Button login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_in_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        login = view.findViewById(R.id.login_signin_fragment);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new LoginFragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment  , "loginFragment");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                Log.i("Log","login Fragment Created");
            }
        });
    }
}
