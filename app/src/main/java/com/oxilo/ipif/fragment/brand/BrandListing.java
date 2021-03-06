package com.oxilo.ipif.fragment.brand;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
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
import android.widget.ProgressBar;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxilo.ipif.AppConstant;
import com.oxilo.ipif.R;
import com.oxilo.ipif.adapter.brand.AllListAdapter;
import com.oxilo.ipif.adapter.brand.BrandListAdapter;
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
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BrandListing#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BrandListing extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recylerview)
    RecyclerView recylerview;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1,mParam2;

    private BrandListAdapter brandListAdapter;
    private OnFragmentInteractionListener mListener;
    ArrayList<BrandList> brandLists;


    public BrandListing() {
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
    public static BrandListing newInstance(String param1, String  param2) {
        BrandListing fragment = new BrandListing();
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
        loadAllFromApi();
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

    private void initClassRefrence() {
        brandLists = new ArrayList<>();
        brandListAdapter = new BrandListAdapter(R.layout.brand_row, brandLists, getContext());
        LinearLayoutManager li1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        li1.setSmoothScrollbarEnabled(true);
        recylerview.setLayoutManager(li1);
        recylerview.setAdapter(brandListAdapter);


//        recylerview.addOnScrollListener(new EndlessRecyclerOnScrollListener(li1) {
//            @Override
//            public void onLoadMore(int current_page) {
//
//                recylerview.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        brandListAdapter.addItem(null);
//                    }
//                });
//                loadAllFromApi();
//            }
//        });

        brandListAdapter.setOnItemClickListener(new BrandListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                startFragment(MenuFragment.newInstance("", ""));
            }
        });



    }

//    private ArrayList<BrandList> loadDummy() {
//        ArrayList<BrandList> services = new ArrayList<>();
//        try {
//            JSONObject mapping = new JSONObject(AppConstant.BRAND);
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            services = mapper.readValue(mapping.getString("brand_data"), new TypeReference<List<BrandList>>() {
//            });
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return services;
//    }

    private void loadAllFromApi() {
        try {
            showProgress(true);
            WebService webService = ServiceFactory.createRetrofitService(WebService.class);
            webService.getBrand("brands")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Response<ResponseBody>>() {
                        @Override
                        public void accept(@NonNull Response<ResponseBody> brand) throws Exception {
                            try {
                                showProgress(false);
                                String sd = new String(brand.body().bytes());
                                JSONObject mapping = new JSONObject(sd);
                                ObjectMapper mapper = new ObjectMapper();
                                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                                brandLists = mapper.readValue(mapping.getString("brand_data"), new TypeReference<List<BrandList>>() {
                                });

                                for (BrandList brandList : brandLists) {
                                    brandListAdapter.addItem(brandList);
                                }
                                if (brandListAdapter.dataSet.size() > 0 ) {
                                    brandListAdapter.removeItem(brandListAdapter.dataSet.size() - 1);
                                }

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            showProgress(false);
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

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

                recylerview.setVisibility(show ? View.GONE : View.VISIBLE);
                recylerview.animate().setDuration(shortAnimTime).alpha(
                        show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        recylerview.setVisibility(show ? View.GONE : View.VISIBLE);
                    }
                });

                progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                progressBar.animate().setDuration(shortAnimTime).alpha(
                        show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                    }
                });
            } else {
                // The ViewPropertyAnimator APIs are not available, so simply show
                // and hide the relevant UI components.
                recylerview.setVisibility(show ? View.VISIBLE : View.GONE);
                progressBar.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
