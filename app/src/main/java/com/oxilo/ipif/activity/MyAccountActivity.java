package com.oxilo.ipif.activity;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.oxilo.ipif.BaseDrawerActivity;
import com.oxilo.ipif.R;
import com.oxilo.ipif.fragment.BrandListing;
import com.oxilo.ipif.fragment.CategoryFragment;
import com.oxilo.ipif.fragment.ItemFragment;
import com.oxilo.ipif.fragment.MenuFragment;
import com.oxilo.ipif.fragment.MyAccountFragment;
import com.oxilo.ipif.fragment.ProductListing;
import com.oxilo.ipif.fragment.ReceivedGIftListing;
import com.oxilo.ipif.fragment.SendGIftListing;

public class MyAccountActivity extends BaseDrawerActivity implements SendGIftListing.OnFragmentInteractionListener,ReceivedGIftListing.OnFragmentInteractionListener,ItemFragment.OnFragmentInteractionListener
,BrandListing.OnFragmentInteractionListener,MenuFragment.OnFragmentInteractionListener,ProductListing.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        if (savedInstanceState==null)
            startFragment(MyAccountFragment.newInstance("",""));
    }

    public void startFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content, fragment);
//        fragmentTransaction.addToBackStack(null);/**Enable this in fragment call not in activity*/
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
