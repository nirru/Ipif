package com.oxilo.ipif.modal.products;

/**
 * Created by nikk on 19/11/17.
 */

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "status",
        "product_data"
})
public class Products implements Parcelable
{

    @JsonProperty("success")
    private Integer success;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("product_data")
    private ArrayList<ProductListings> productListings = new ArrayList<>();
    public final static Parcelable.Creator<Products> CREATOR = new Creator<Products>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        public Products[] newArray(int size) {
            return (new Products[size]);
        }

    }
            ;

    protected Products(Parcel in) {
        this.success = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.productListings, (ProductListings.class.getClassLoader()));
    }

    public Products() {
    }

    @JsonProperty("success")
    public Integer getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Integer success) {
        this.success = success;
    }

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("product_data")
    public ArrayList<ProductListings> getProductListings() {
        return productListings;
    }

    @JsonProperty("product_data")
    public void setProductListings(ArrayList<ProductListings> productData) {
        this.productListings = productData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(status);
        dest.writeList(productListings);
    }

    public int describeContents() {
        return 0;
    }

}
