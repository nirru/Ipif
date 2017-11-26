package com.oxilo.ipif.modal.login;

/**
 * Created by nikk on 26/11/17.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "user_id",
        "name",
        "country_code",
        "image_url",
        "is_verified",
        "verfication_tmp_test",
        "mpin_status"
})
public class UserData implements Parcelable
{

    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("is_verified")
    private String isVerified;
    @JsonIgnoreProperties("verfication_tmp_test")
    private String verficationTmpTest;
    @JsonProperty("mpin_status")
    private String mpinStatus;
    public final static Parcelable.Creator<UserData> CREATOR = new Creator<UserData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        public UserData[] newArray(int size) {
            return (new UserData[size]);
        }

    }
            ;

    protected UserData(Parcel in) {
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.countryCode = ((String) in.readValue((String.class.getClassLoader())));
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.isVerified = ((String) in.readValue((String.class.getClassLoader())));
        this.verficationTmpTest = ((String) in.readValue((String.class.getClassLoader())));
        this.mpinStatus = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UserData() {
    }

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("country_code")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("country_code")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("is_verified")
    public String getIsVerified() {
        return isVerified;
    }

    @JsonProperty("is_verified")
    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }

    @JsonProperty("verfication_tmp_test")
    public String getVerficationTmpTest() {
        return verficationTmpTest;
    }

    @JsonProperty("verfication_tmp_test")
    public void setVerficationTmpTest(String verficationTmpTest) {
        this.verficationTmpTest = verficationTmpTest;
    }

    @JsonProperty("mpin_status")
    public String getMpinStatus() {
        return mpinStatus;
    }

    @JsonProperty("mpin_status")
    public void setMpinStatus(String mpinStatus) {
        this.mpinStatus = mpinStatus;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userId);
        dest.writeValue(name);
        dest.writeValue(countryCode);
        dest.writeValue(imageUrl);
        dest.writeValue(isVerified);
        dest.writeValue(verficationTmpTest);
        dest.writeValue(mpinStatus);
    }

    public int describeContents() {
        return 0;
    }

}
