package com.oxilo.ipif.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxilo.ipif.R;
import com.oxilo.ipif.activity.MyAccountActivity;
import com.oxilo.ipif.adapter.CategoryPagerAdapter;
import com.oxilo.ipif.modal.Brand;
import com.oxilo.ipif.modal.BrandList;
import com.oxilo.ipif.modal.Category;
import com.oxilo.ipif.modal.products.BrandCategory;
import com.oxilo.ipif.modal.products.BrandCategoryList;
import com.oxilo.ipif.network.api.ServiceFactory;
import com.oxilo.ipif.network.api.WebService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {
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
    private String cat_id, mParam2;

    private OnFragmentInteractionListener mListener;

    public MenuFragment() {
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
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
            cat_id = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadCategoryFromApi();
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


    private void initPager(ArrayList<BrandCategoryList> brandCategory) {
        final List<Fragment> fragments = new Vector<Fragment>();
        final Bundle page = new Bundle();
        page.putString("url", "d");
        for (int i = 0; i < brandCategory.size(); i++) {
            ProductListing productListing = ProductListing.newInstance(brandCategory.get(i).getId(), "");
            fragments.add(i, productListing);
        }
        CategoryPagerAdapter servicesPagerAdapter = new CategoryPagerAdapter(getActivity(), ((MenuFragment.this)).getChildFragmentManager(), fragments, brandCategory);
        viewPager.setAdapter(servicesPagerAdapter);
        int limit = (servicesPagerAdapter.getCount() > 1 ? servicesPagerAdapter.getCount() - 1 : 1);
        viewPager.setOffscreenPageLimit(limit);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void loadCategoryFromApi() {
        try {
            WebService webService = ServiceFactory.createRetrofitService(WebService.class);
            webService.getCategoriesByBrandId(cat_id)
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
                                ArrayList<BrandCategoryList>brandCategoryLists = mapper.readValue(mapping.getString("brand_categorys"), new TypeReference<List<BrandCategoryList>>() {
                                });
//
                                Log.e("SIZE==",""+ brandCategoryLists.size());
                                initPager(brandCategoryLists);

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

    private ArrayList<Category> loadDummyCategory() {
        ArrayList<Category> categories = new ArrayList<>();
        Category category1 = new Category();
        Category categor2 = new Category();
        Category categor3 = new Category();
        Category categor4 = new Category();
        Category categor5 = new Category();

        category1.setTitle("Fruits");
        categor2.setTitle("Drinks");
        categor3.setTitle("Eat");
        categor4.setTitle("Lunch");
        categor5.setTitle("Vegetarian");

        categories.add(category1);
        categories.add(categor2);
        categories.add(categor3);
        categories.add(categor4);
        categories.add(categor5);

        return categories;


    }
}
