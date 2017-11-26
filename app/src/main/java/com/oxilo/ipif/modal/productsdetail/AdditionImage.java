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
        "image"
})
public class AdditionImage implements Parcelable
{

    @JsonProperty("image")
    private String image;
    public final static Parcelable.Creator<AdditionImage> CREATOR = new Creator<AdditionImage>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AdditionImage createFromParcel(Parcel in) {
            return new AdditionImage(in);
        }

        public AdditionImage[] newArray(int size) {
            return (new AdditionImage[size]);
        }

    }
            ;

    protected AdditionImage(Parcel in) {
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AdditionImage() {
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(image);
    }

    public int describeContents() {
        return 0;
    }

}