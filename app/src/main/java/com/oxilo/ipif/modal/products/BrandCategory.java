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
        "brand_categorys"
})
public class BrandCategory implements Parcelable
{

    @JsonProperty("success")
    private Integer success;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("brand_categorys")
    private ArrayList<BrandCategoryList> brandCategorysList = new ArrayList<>();
    public final static Parcelable.Creator<BrandCategory> CREATOR = new Creator<BrandCategory>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BrandCategory createFromParcel(Parcel in) {
            return new BrandCategory(in);
        }

        public BrandCategory[] newArray(int size) {
            return (new BrandCategory[size]);
        }

    }
            ;

    protected BrandCategory(Parcel in) {
        this.success = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.brandCategorysList, (BrandCategoryList.class.getClassLoader()));
    }

    public BrandCategory() {
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

    @JsonProperty("brand_categorys")
    public ArrayList<BrandCategoryList> getBrandCategorys() {
        return brandCategorysList;
    }

    @JsonProperty("brand_categorys")
    public void setBrandCategorys(ArrayList<BrandCategoryList> brandCategorys) {
        this.brandCategorysList = brandCategorys;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(status);
        dest.writeList(brandCategorysList);
    }

    public int describeContents() {
        return 0;
    }

}