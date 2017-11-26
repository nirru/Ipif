package com.oxilo.ipif.modal.products;

/**
 * Created by nikk on 19/11/17.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name"
})
public class BrandCategoryList implements Parcelable
{

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    public final static Parcelable.Creator<BrandCategoryList> CREATOR = new Creator<BrandCategoryList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BrandCategoryList createFromParcel(Parcel in) {
            return new BrandCategoryList(in);
        }

        public BrandCategoryList[] newArray(int size) {
            return (new BrandCategoryList[size]);
        }

    }
            ;

    protected BrandCategoryList(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public BrandCategoryList() {
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
    }

    public int describeContents() {
        return 0;
    }

}
