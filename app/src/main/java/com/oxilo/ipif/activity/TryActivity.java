package com.oxilo.ipif.activity;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.oxilo.ipif.R;
import com.oxilo.ipif.fragment.CartFragment;
import com.oxilo.ipif.fragment.ContactsFragment;

/**
 * Created by Harshita on 11/18/2017.
 */

public class TryActivity extends AppCompatActivity implements ContactsFragment.OnFragmentInteractionListener,CartFragment.OnFragmentInteractionListener{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.try_layout);

        CartFragment fragment = CartFragment.newInstance("","");
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.frame_layout,fragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
