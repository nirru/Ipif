package com.oxilo.ipif.modal;
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
        "category_data"
})
public class Category implements Parcelable
{

    @JsonProperty("success")
    private Integer success;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("category_data")
    private ArrayList<Service> categoryData = new ArrayList<>();
    private String title;
    public final static Parcelable.Creator<Category> CREATOR = new Creator<Category>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        public Category[] newArray(int size) {
            return (new Category[size]);
        }

    }
            ;

    protected Category(Parcel in) {
        this.success = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.categoryData, (Service.class.getClassLoader()));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Category() {
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

    @JsonProperty("category_data")
    public ArrayList<Service> getCategoryData() {
        return categoryData;
    }

    @JsonProperty("category_data")
    public void setCategoryData(ArrayList<Service> categoryData) {
        this.categoryData = categoryData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(status);
        dest.writeList(categoryData);
        dest.writeValue(title);
    }

    public int describeContents() {
        return 0;
    }

}