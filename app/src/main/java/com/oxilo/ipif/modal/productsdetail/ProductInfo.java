package com.oxilo.ipif.modal.productsdetail;

/**
 * Created by nikk on 23/11/17.
 */

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
public class ProductInfo implements Parcelable
{

    @JsonProperty("success")
    private Integer success;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("product_data")
    private ProductData productData;
    public final static Parcelable.Creator<ProductInfo> CREATOR = new Creator<ProductInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductInfo createFromParcel(Parcel in) {
            return new ProductInfo(in);
        }

        public ProductInfo[] newArray(int size) {
            return (new ProductInfo[size]);
        }

    }
            ;

    protected ProductInfo(Parcel in) {
        this.success = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.productData = ((ProductData) in.readValue((ProductData.class.getClassLoader())));
    }

    public ProductInfo() {
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
    public ProductData getProductData() {
        return productData;
    }

    @JsonProperty("product_data")
    public void setProductData(ProductData productData) {
        this.productData = productData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(status);
        dest.writeValue(productData);
    }

    public int describeContents() {
        return 0;
    }

}
