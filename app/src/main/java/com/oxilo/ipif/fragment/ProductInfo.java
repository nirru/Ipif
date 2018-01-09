package com.oxilo.ipif.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxilo.ipif.ApplicationController;
import com.oxilo.ipif.BaseDrawerActivity;
import com.oxilo.ipif.LoginActivity;
import com.oxilo.ipif.NavigationController;
import com.oxilo.ipif.R;
import com.oxilo.ipif.activity.MyAccountActivity;
import com.oxilo.ipif.adapter.ImagePagerAdapter;
import com.oxilo.ipif.modal.login.UserData;
import com.oxilo.ipif.modal.productsdetail.AdditionImage;
import com.oxilo.ipif.modal.productsdetail.ProductData;
import com.oxilo.ipif.network.api.ServiceFactory;
import com.oxilo.ipif.network.api.WebService;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductInfo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    Unbinder unbinder;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    @BindView(R.id.product_title)
    TextView productTitle;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.product_desc)
    TextView productDesc;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public ProductInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductInfo newInstance(String param1, String param2) {
        ProductInfo fragment = new ProductInfo();
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
        View view = inflater.inflate(R.layout.fragment_product_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadAllFromApi(mParam1);
    }

    private void loadAllFromApi(String category_id) {
        try {
            WebService webService = ServiceFactory.createRetrofitService(WebService.class);
            webService.get_products_detail(category_id)
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
                                ProductData productInfo = mapper.readValue(mapping.getString("product_data"), new TypeReference<ProductData>() {
                                });
//
                                initPager(productInfo.getAdditionImage());
                                setData(productInfo);

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setData(ProductData productInfo) {
        if (productInfo!=null){
            productTitle.setText(productInfo.getName());
            price.setText("$" + productInfo.getPrice());
            productDesc.setText(Html.fromHtml(productInfo.getLongDescription()));
        }
    }


    private void initPager(List<AdditionImage> additionImages) {
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(getActivity(), additionImages);
        viewPager.setAdapter(imagePagerAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.send_gift)
    public void onViewClicked() {
        UserData login = ApplicationController.getInstance().getAppPrefs().getObject("LOGIN", UserData.class);
        if (login != null) {
            NavigationController navigationController = new NavigationController((BaseDrawerActivity) getActivity());
            navigationController.navigateToContact();
        } else {
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((BaseDrawerActivity) getActivity()).setUpDrawable(toolbar);

    }
}
