package com.oxilo.ipif.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxilo.ipif.BaseDrawerActivity;
import com.oxilo.ipif.NavigationController;
import com.oxilo.ipif.R;
import com.oxilo.ipif.adapter.brand.CategoryListAdapter;
import com.oxilo.ipif.adapter.brand.ProductListAdapter;
import com.oxilo.ipif.modal.Brand;
import com.oxilo.ipif.modal.BrandList;
import com.oxilo.ipif.modal.Service;
import com.oxilo.ipif.modal.products.BrandCategoryList;
import com.oxilo.ipif.modal.products.ProductListings;
import com.oxilo.ipif.modal.products.Products;
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
 * Use the {@link ProductListing#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductListing extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recylerview)
    RecyclerView recylerview;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1,mParam2;

    private ProductListAdapter productListAdapter;
    private OnFragmentInteractionListener mListener;
    private ArrayList<ProductListings>productListings;


    public ProductListing() {
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
    public static ProductListing newInstance(String param1, String  param2) {
        ProductListing fragment = new ProductListing();
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
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        productListings = new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClassRefrence();
        loadAllFromApi(mParam1);
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
        productListAdapter = new ProductListAdapter(R.layout.category_row, productListings,getContext());
        LinearLayoutManager li1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        li1.setSmoothScrollbarEnabled(true);
        recylerview.setLayoutManager(li1);
        recylerview.setAdapter(productListAdapter);



        productListAdapter.setOnItemClickListener(new ProductListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                ProductListings productListings = (ProductListings) productListAdapter.dataSet.get(position);
                String id = productListings.getId();
//                ProductInfo productInfo = ProductInfo.newInstance(id,"");
                NavigationController navigationController = new NavigationController((BaseDrawerActivity) getActivity());
                navigationController.navigateToProductInfo(id);
            }
        });
    }

    private ArrayList<Service> loadDummy(){
        ArrayList<Service>services = new ArrayList<>();
        for (int i = 0; i<12;i++){
            Service service = new Service();
            service.setTitle("Shrimp Fondue");
            services.add(service);
        }
        return services;
    }

    private void loadAllFromApi(String category_id) {
        try {
            WebService webService = ServiceFactory.createRetrofitService(WebService.class);
            webService.get_products_list(category_id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Response<ResponseBody>>() {
                        @Override
                        public void accept(@NonNull Response<ResponseBody> responseBodyResponse) throws Exception {

                            try {
                                String sd = new String(responseBodyResponse.body().bytes());
                                JSONObject mapping = new JSONObject(sd);
                                ObjectMapper mapper = new ObjectMapper();
                                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                                ArrayList<ProductListings>productListingses = mapper.readValue(mapping.getString("product_data"), new TypeReference<List<ProductListings>>() {
                                });
//
                                Log.e("SIZE==",""+ productListingses.size());
                                for (ProductListings productListings : productListingses) {
                                    productListAdapter.addItem(productListings);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            throwable.printStackTrace();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.addToBackStack(null);/**Enable this in fragment call not in activity*/
        fragmentTransaction.commit();
    }
}
