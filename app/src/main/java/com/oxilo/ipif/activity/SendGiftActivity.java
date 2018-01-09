package com.oxilo.ipif.activity;

import android.net.Uri;
import android.os.Bundle;

import com.oxilo.ipif.BaseDrawerActivity;
import com.oxilo.ipif.NavigationController;
import com.oxilo.ipif.R;
import com.oxilo.ipif.fragment.ContactsFragment;
import com.oxilo.ipif.fragment.ItemFragment;
import com.oxilo.ipif.fragment.MenuFragment;
import com.oxilo.ipif.fragment.ProductListing;
import com.oxilo.ipif.fragment.ReceivedGIftListing;
import com.oxilo.ipif.fragment.SendGIftListing;
import com.oxilo.ipif.fragment.brand.BrandListing;
import com.oxilo.ipif.modal.login.UserData;

public class SendGiftActivity extends BaseDrawerActivity implements SendGIftListing.OnFragmentInteractionListener,ReceivedGIftListing.OnFragmentInteractionListener,ItemFragment.OnFragmentInteractionListener
,BrandListing.OnFragmentInteractionListener,MenuFragment.OnFragmentInteractionListener,ProductListing.OnFragmentInteractionListener,ContactsFragment.OnFragmentInteractionListener{

    int k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_gift);

        if (getIntent()!=null){
            k = getIntent().getIntExtra("A",0);

        }

        if (savedInstanceState==null){
            NavigationController navigationController = new NavigationController(SendGiftActivity.this);
            if (k==1){
                UserData userData = getIntent().getParcelableExtra("USER");
                navigationController.navigateToContact(userData);
            }else {
                navigationController.navigateToItems();
            }


        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
