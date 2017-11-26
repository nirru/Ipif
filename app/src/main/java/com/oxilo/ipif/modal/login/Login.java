package com.oxilo.ipif.modal.login;

/**
 * Created by nikk on 26/11/17.
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
        "user_data",
        "status",
        "message"
})
public class Login implements Parcelable
{

    @JsonProperty("success")
    private Integer success;
    @JsonProperty("user_data")
    private UserData userData;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("message")
    private String message;
    public final static Parcelable.Creator<Login> CREATOR = new Creator<Login>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Login createFromParcel(Parcel in) {
            return new Login(in);
        }

        public Login[] newArray(int size) {
            return (new Login[size]);
        }

    }
            ;

    protected Login(Parcel in) {
        this.success = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.userData = ((UserData) in.readValue((UserData.class.getClassLoader())));
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Login() {
    }

    @JsonProperty("success")
    public Integer getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Integer success) {
        this.success = success;
    }

    @JsonProperty("user_data")
    public UserData getUserData() {
        return userData;
    }

    @JsonProperty("user_data")
    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(userData);
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}
