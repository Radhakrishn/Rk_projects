package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import utility.IJRDataModel;


public class ForgotPasswordResponse extends IJRDataModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("error_code")
    @Expose
    private String errorCode;

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode The error_code
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}