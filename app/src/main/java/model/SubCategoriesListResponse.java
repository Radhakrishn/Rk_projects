package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class SubCategoriesListResponse implements Serializable{

    @SerializedName("id")
    @Expose
    private String mSubCategoryId;

    @SerializedName("subcategory")
    @Expose
    private String mSubCategoryName;

    @SerializedName("description")
    @Expose
    private String mSubCategoryDescription;

    @SerializedName("products_count")
    @Expose
    private String mSubCategoryProductCount;


    public String getmSubCategoryId() {
        return mSubCategoryId;
    }

    public void setmSubCategoryId(String mSubCategoryId) {
        this.mSubCategoryId = mSubCategoryId;
    }

    public String getmSubCategoryName() {
        return mSubCategoryName;
    }

    public void setmSubCategoryName(String mSubCategoryName) {
        this.mSubCategoryName = mSubCategoryName;
    }

    public String getmSubCategoryDescription() {
        return mSubCategoryDescription;
    }

    public void setmSubCategoryDescription(String mSubCategoryDescription) {
        this.mSubCategoryDescription = mSubCategoryDescription;
    }

    public String getmSubCategoryProductCount() {
        return mSubCategoryProductCount;
    }

    public void setmSubCategoryProductCount(String mSubCategoryProductCount) {
        this.mSubCategoryProductCount = mSubCategoryProductCount;
    }
}
