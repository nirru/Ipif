package com.oxilo.ipif.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.oxilo.ipif.BaseDrawerActivity;
import com.oxilo.ipif.NavigationController;
import com.oxilo.ipif.ProfileActivity;
import com.oxilo.ipif.R;
import com.oxilo.ipif.fragment.ContactsFragment;
import com.oxilo.ipif.fragment.brand.BrandListing;
import com.oxilo.ipif.fragment.ItemFragment;
import com.oxilo.ipif.fragment.MenuFragment;
import com.oxilo.ipif.fragment.MyAccountFragment;
import com.oxilo.ipif.fragment.ProductListing;
import com.oxilo.ipif.fragment.ReceivedGIftListing;
import com.oxilo.ipif.fragment.SendGIftListing;
import com.oxilo.ipif.modal.login.UserData;
import com.oxilo.ipif.network.api.ServiceFactory;
import com.oxilo.ipif.network.api.WebService;

import org.json.JSONObject;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class MyAccountActivity extends BaseDrawerActivity implements SendGIftListing.OnFragmentInteractionListener,ReceivedGIftListing.OnFragmentInteractionListener,ItemFragment.OnFragmentInteractionListener
,BrandListing.OnFragmentInteractionListener,MenuFragment.OnFragmentInteractionListener,ProductListing.OnFragmentInteractionListener,ContactsFragment.OnFragmentInteractionListener{

    int k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        if (getIntent()!=null){
            k = getIntent().getIntExtra("A",0);

        }

        if (savedInstanceState==null){
            NavigationController navigationController = new NavigationController(MyAccountActivity.this);
            if (k==1){
                UserData userData = getIntent().getParcelableExtra("USER");
                navigationController.navigateToContact(userData);
            }else {
                navigationController.navigateToMyAccount();
            }


        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
