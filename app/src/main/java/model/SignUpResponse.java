package model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class SignUpResponse implements Serializable {

    @SerializedName("status")
    private String mStatus;

    @SerializedName("description")
    private String mStatusDesc;

    @SerializedName("social")
    private String mSocial;

    @SerializedName("userid")
    private String mSUserId;

    @SerializedName("error_code")
    private String mErrorCode;

    @SerializedName("token")
    private String mToken;

    @SerializedName("userdetails")
    private UserDetails mUserDetails;

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmStatusDesc() {
        return mStatusDesc;
    }

    public void setmStatusDesc(String mStatusDesc) {
        this.mStatusDesc = mStatusDesc;
    }

    public String getmSocial() {
        return mSocial;
    }

    public void setmSocial(String mSocial) {
        this.mSocial = mSocial;
    }

    public String getmSUserId() {
        return mSUserId;
    }

    public void setmSUserId(String mSUserId) {
        this.mSUserId = mSUserId;
    }

    public String getmErrorCode() {
        return mErrorCode;
    }

    public void setmErrorCode(String mErrorCode) {
        this.mErrorCode = mErrorCode;
    }

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }

    public UserDetails getmUserDetails() {
        return mUserDetails;
    }

    public void setmUserDetails(UserDetails mUserDetails) {
        this.mUserDetails = mUserDetails;
    }
}

