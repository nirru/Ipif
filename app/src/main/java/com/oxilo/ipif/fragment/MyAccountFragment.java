package com.oxilo.ipif.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oxilo.ipif.R;
import com.oxilo.ipif.activity.MyAccountActivity;
import com.oxilo.ipif.adapter.CategoryPagerAdapter;
import com.oxilo.ipif.modal.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyAccountFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Toolbar toolbar;

    public MyAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyAccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyAccountFragment newInstance(String param1, String param2) {
        MyAccountFragment fragment = new MyAccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MyAccountActivity)getActivity()).setUpDrawable(toolbar);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        initPager();
    }

    private void initPager() {
        final List<Fragment> fragments = new Vector<Fragment>();
        final Bundle page = new Bundle();
        page.putString("url", "d");

        for (int i = 0; i < 2; i++) {
            if (i==0){
                SendGIftListing sendGIftListing = SendGIftListing.newInstance("", "");
                fragments.add(i, sendGIftListing);
            }
            if (i==1){
                ReceivedGIftListing receivedGIftListing = ReceivedGIftListing.newInstance("", "");
                fragments.add(i, receivedGIftListing);
            }

        }
        CategoryPagerAdapter servicesPagerAdapter = new CategoryPagerAdapter(getActivity(), ((MyAccountFragment.this)).getChildFragmentManager(), fragments, loadDummyCategory());
        viewPager.setAdapter(servicesPagerAdapter);
        int limit = (servicesPagerAdapter.getCount() > 1 ? servicesPagerAdapter.getCount() - 1 : 1);
        viewPager.setOffscreenPageLimit(limit);
        tabLayout.setupWithViewPager(viewPager);
    }

    private ArrayList<Category> loadDummyCategory(){
        ArrayList<Category> categories = new ArrayList<>();
        Category category1 = new Category();
        Category categor2 = new Category();

        category1.setTitle("Send Gift");
        categor2.setTitle("Received Gift");

        categories.add(category1);
        categories.add(categor2);

        return categories;


    }
}
