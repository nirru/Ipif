package com.oxilo.ipif.network.api;

import com.oxilo.ipif.modal.Brand;
import com.oxilo.ipif.modal.Category;
import com.oxilo.ipif.modal.products.BrandCategory;
import com.oxilo.ipif.modal.products.Products;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by ericbasendra on 10/06/16.
 */
public interface WebService {

    @GET("ipif_block/index.php/api/get_categories_list")
    io.reactivex.Observable<Response<ResponseBody>> getCategory();

    @FormUrlEncoded
    @POST("ipif_block/index.php/api/login")
    io.reactivex.Observable<Response<ResponseBody>> login(
            @Field("phone_num") String phone,
            @Field("device_token") String device_token);

    @GET("ipif_block/index.php/api/get_brands_list")
    io.reactivex.Observable<Response<ResponseBody>> getAllProduct(
            @Query("tag") String popular);

    @GET("ipif_block/index.php/api/get_brands_list")
    io.reactivex.Observable<Response<ResponseBody>> getBrand(
            @Query("tag") String brand);

    @GET("ipif_block/index.php/api/get_brands_list")
    io.reactivex.Observable<Response<ResponseBody>> getBrandsByLocations(
            @Query("tag") String location,
            @Query("latitude") String latitude,
            @Query("longitude") String longitude,
            @Query("miles") String miles);

    @GET("ipif_block/index.php/api/get_categories_by_brand_id")
    io.reactivex.Observable<Response<ResponseBody>> getCategoriesByBrandId(
            @Query("brand_id") String brand_id);

    @GET("ipif_block/index.php/api/get_products_list")
    io.reactivex.Observable<Response<ResponseBody>> get_products_list(
            @Query("category_id") String category_id);

    @GET("ipif_block/index.php/api/get_products_detail")
    io.reactivex.Observable<Response<ResponseBody>> get_products_detail(
            @Query("product_id") String product_id);

    @Headers("Accept: multipart/form-data")
    @Multipart
    @POST("ipif_block/index.php/api/userprofile")
    io.reactivex.Observable<Response<ResponseBody>> uploadProfile(@Part MultipartBody.Part image,
                                                     @Part("name") RequestBody name,
                                                     @Part("user_id") RequestBody user_id);
}
