package model;

import com.google.gson.annotations.SerializedName;


public class UserDetails {

    @SerializedName("firstname")
    private String mFirstName;

    @SerializedName("lastname")
    private String mLastName;

    @SerializedName("phone")
    private String mPhone;

    @SerializedName("status")
    private String mUserDetailStatus;

    @SerializedName("phone_verified")
    private String mPhoneVerified;

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmUserDetailStatus() {
        return mUserDetailStatus;
    }

    public void setmUserDetailStatus(String mUserDetailStatus) {
        this.mUserDetailStatus = mUserDetailStatus;
    }

    public String getmPhoneVerified() {
        return mPhoneVerified;
    }

    public void setmPhoneVerified(String mPhoneVerified) {
        this.mPhoneVerified = mPhoneVerified;
    }
}
