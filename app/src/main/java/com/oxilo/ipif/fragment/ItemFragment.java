package com.oxilo.ipif.fragment;

import android.content.Context;
import android.net.Uri;
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
import com.oxilo.ipif.fragment.brand.AllFragment;
import com.oxilo.ipif.fragment.brand.BrandListing;
import com.oxilo.ipif.fragment.brand.CategoryFragment;
import com.oxilo.ipif.fragment.brand.LocationListing;
import com.oxilo.ipif.modal.Category;
import com.oxilo.ipif.modal.products.BrandCategoryList;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    // TODO: Rename and change types of parameters
    private String mParam2, mParam1;

    private OnFragmentInteractionListener mListener;

    public ItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemFragment newInstance(String param1, String param2) {
        ItemFragment fragment = new ItemFragment();
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
        View view = inflater.inflate(R.layout.item_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPager();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MyAccountActivity) getActivity()).setUpDrawable(toolbar);

    }


    private void initPager() {
        final List<Fragment> fragments = new Vector<Fragment>();
        final Bundle page = new Bundle();
        page.putString("url", "d");
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                AllFragment brandListing = AllFragment.newInstance("", "");
                fragments.add(i, brandListing);
            }
            if (i == 1) {
                CategoryFragment brandListing = CategoryFragment.newInstance("", "");
                fragments.add(i, brandListing);
            }
            if (i == 2) {
                BrandListing brandListing = BrandListing.newInstance("", "");
                fragments.add(i, brandListing);
            }
            if (i == 3) {
                LocationListing brandListing = LocationListing.newInstance("", "");
                fragments.add(i, brandListing);
            }

        }
        CategoryPagerAdapter servicesPagerAdapter = new CategoryPagerAdapter(getActivity(), ((ItemFragment.this)).getChildFragmentManager(), fragments, loadDummyCategory());
        viewPager.setAdapter(servicesPagerAdapter);
        int limit = (servicesPagerAdapter.getCount() > 1 ? servicesPagerAdapter.getCount() - 1 : 1);
        viewPager.setOffscreenPageLimit(limit);
        tabLayout.setupWithViewPager(viewPager);
    }

    private ArrayList<BrandCategoryList> loadDummyCategory() {
        ArrayList<BrandCategoryList> categories = new ArrayList<>();
        BrandCategoryList category1 = new BrandCategoryList();
        BrandCategoryList categor2 = new BrandCategoryList();
        BrandCategoryList categor3 = new BrandCategoryList();
        BrandCategoryList categor4 = new BrandCategoryList();

        category1.setName("All");
        categor2.setName("Category");
        categor3.setName("Brand");
        categor4.setName("Location");

        categories.add(category1);
        categories.add(categor2);
        categories.add(categor3);
        categories.add(categor4);

        return categories;


    }
}
