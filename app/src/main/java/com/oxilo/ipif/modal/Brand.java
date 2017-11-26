package com.oxilo.ipif.modal;

/**
 * Created by nikk on 13/11/17.
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
        "brand_data"
})
public class Brand implements Parcelable
{

    @JsonProperty("success")
    private Integer success;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("brand_data")
    private ArrayList<BrandList> brandData = new ArrayList();
    public final static Parcelable.Creator<Brand> CREATOR = new Creator<Brand>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Brand createFromParcel(Parcel in) {
            return new Brand(in);
        }

        public Brand[] newArray(int size) {
            return (new Brand[size]);
        }

    }
            ;

    protected Brand(Parcel in) {
        this.success = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.brandData, (BrandList.class.getClassLoader()));
    }

    public Brand() {
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

    @JsonProperty("brand_data")
    public ArrayList<BrandList> getBrandData() {
        return brandData;
    }

    @JsonProperty("brand_data")
    public void setBrandData(ArrayList<BrandList> brandData) {
        this.brandData = brandData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(status);
        dest.writeList(brandData);
    }

    public int describeContents() {
        return 0;
    }

}
