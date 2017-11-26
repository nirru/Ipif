package com.oxilo.ipif.fragment.brand;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxilo.ipif.AppConstant;
import com.oxilo.ipif.R;
import com.oxilo.ipif.adapter.brand.BrandListAdapter;
import com.oxilo.ipif.adapter.brand.LocationListAdapter;
import com.oxilo.ipif.fragment.MenuFragment;
import com.oxilo.ipif.modal.Brand;
import com.oxilo.ipif.modal.BrandList;
import com.oxilo.ipif.network.api.ServiceFactory;
import com.oxilo.ipif.network.api.WebService;
import com.oxilo.ipif.util.EndlessRecyclerOnScrollListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LocationListing#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationListing extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recylerview)
    RecyclerView recylerview;
    Unbinder unbinder;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    // TODO: Rename and change types of parameters
    private String mParam1,mParam2;

    private LocationListAdapter locationListAdapter;


    public LocationListing() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServiceFragments.
     */
    // TODO: Rename and change types and number of parameters
    public static LocationListing newInstance(String param1, String  param2) {
        LocationListing fragment = new LocationListing();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,  param1);
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
        View view = inflater.inflate(R.layout.fragment_brand_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClassRefrence();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        loadAllFromApi();
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

    private void initClassRefrence() {
        locationListAdapter = new LocationListAdapter(R.layout.brand_row, loadDummy(), getContext());
        LinearLayoutManager li1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        li1.setSmoothScrollbarEnabled(true);
        recylerview.setLayoutManager(li1);
        recylerview.setAdapter(locationListAdapter);


        recylerview.addOnScrollListener(new EndlessRecyclerOnScrollListener(li1) {
            @Override
            public void onLoadMore(int current_page) {
                recylerview.post(new Runnable() {
                    @Override
                    public void run() {
                        locationListAdapter.addItem(null);
                    }
                });
                loadAllFromApi();
            }
        });

        locationListAdapter.setOnItemClickListener(new LocationListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                startFragment(MenuFragment.newInstance("", ""));
            }
        });

        // SwipeRefreshLayout
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

//        /**
//         * Showing Swipe Refresh animation on activity create
//         * As animation won't start on onCreate, post runnable is used
//         */
//        mSwipeRefreshLayout.post(new Runnable() {
//
//            @Override
//            public void run() {
//
//                mSwipeRefreshLayout.setRefreshing(true);
//                // Fetching data from server
//                if (allListAdapter!=null){
//                    allListAdapter.clearItem();
//                    allListAdapter.notifyDataSetChanged();
//                }
//                loadAllFromApi();
//            }
//        });

    }

    private ArrayList<BrandList> loadDummy() {
        ArrayList<BrandList> services = new ArrayList<>();
        try {
            JSONObject mapping = new JSONObject(AppConstant.LOCATION);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            services = mapper.readValue(mapping.getString("brand_data"), new TypeReference<List<BrandList>>() {
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return services;
    }

    private void loadAllFromApi() {
        try {
            WebService webService = ServiceFactory.createRetrofitService(WebService.class);
            webService.getBrandsByLocations("location","27.216981","77.489515","50")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Brand>() {
                        @Override
                        public void accept(@NonNull Brand brand) throws Exception {
                            for (BrandList brandList : brand.getBrandData()) {
                                locationListAdapter.addItem(brandList);
                            }
                            if (locationListAdapter.dataSet.size() > 0) {
                                locationListAdapter.removeItem(locationListAdapter.dataSet.size() - 1);
                            }
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            mSwipeRefreshLayout.setRefreshing(false);
                            throwable.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.addToBackStack(null);/**Enable this in fragment call not in activity*/
        fragmentTransaction.commit();
    }

}
