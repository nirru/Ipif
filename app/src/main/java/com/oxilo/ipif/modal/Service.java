package com.oxilo.ipif.modal;

/**
 * Created by nikk on 24/7/17.
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
        "name",
        "has_childs"
})
public class Service implements Parcelable
{

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("has_childs")
    private String hasChilds;
    private String title;
    public final static Parcelable.Creator<Service> CREATOR = new Creator<Service>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        public Service[] newArray(int size) {
            return (new Service[size]);
        }

    };

    protected Service(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.hasChilds = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Service() {
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

    @JsonProperty("has_childs")
    public String getHasChilds() {
        return hasChilds;
    }

    @JsonProperty("has_childs")
    public void setHasChilds(String hasChilds) {
        this.hasChilds = hasChilds;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(hasChilds);
        dest.writeValue(title);
    }

    public int describeContents() {
        return 0;
    }

}
