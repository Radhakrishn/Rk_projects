package model;

import com.google.gson.annotations.SerializedName;

public class SubCategoryRequest {


    @SerializedName("pg")
    private String mPage;

    @SerializedName("search_cat_id")
    private String mCatId;


    public SubCategoryRequest(String pg, String cat_id){
        this.mPage = pg;
        this.mCatId = cat_id;
    }

    public void setmPage(String mPage) {
        this.mPage = mPage;
    }

    public void setmCatId(String mCatId) {
        this.mCatId = mCatId;
    }

    public String getmPage() {
        return mPage;
    }

    public String getmCatId() {
        return mCatId;
    }
}
