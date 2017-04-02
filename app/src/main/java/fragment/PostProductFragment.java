package fragment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.techsmartsolutions.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapters.CategoryAdapter;
import adapters.SubCategoryAdapter;
import model.CategoriesListResponse;
import model.CategoriesResponse;
import model.SubCategoriesListResponse;
import model.SubCategoriesResponse;
import model.SubCategoryRequest;
import network.ApiGsonRequest;
import network.VolleySingleton;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import retrofit.RetrofitClient;
import retrofit.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import utility.NetworkUtility;

/**
 * Created by user on 7/24/2016.
 */
public class PostProductFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "PostProductFragment";
    private EditText productName;
    private EditText productDescName;
    private List<CategoriesListResponse> mCategoryList;
    private List<SubCategoriesListResponse> mSubCategoryList;
    private Spinner categorySpinner;
    private Spinner subCategoSpinner;
    RetrofitInterface apiService;

    ProgressDialog mProgress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_product, container, false);
        apiService = RetrofitClient.getClient().create(RetrofitInterface.class);
        categorySpinner = (Spinner) view.findViewById(R.id.spinner_category);
        subCategoSpinner = (Spinner) view.findViewById(R.id.spinner_subcategory);
        productName = (EditText) view.findViewById(R.id.edit_productname);
        productDescName = (EditText) view.findViewById(R.id.edit_product_desc);
        categorySpinner.setOnItemSelectedListener(this);
        subCategoSpinner.setOnItemSelectedListener(this);
        mProgress = new ProgressDialog(getContext());
        if(NetworkUtility.isNetworkAvailable(getActivity())) {
            getCategories();
        }else{

        }

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(NetworkUtility.isNetworkAvailable(getActivity())) {
                    getSubCategories(mCategoryList.get(position).getId());
                }else{
                    Toast.makeText(getActivity(),getString(R.string.network_unavailable),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    private  void getSubCategories(String mCatId) {
        SubCategoryRequest mSubCategoryRequest = new SubCategoryRequest("1",mCatId);

        JsonObject json = new JsonObject();
        json.addProperty("pg", "1");
        json.addProperty("search_cat_id",mCatId);

//        FooResponse response = foo.postRawJson(in);


        Call<SubCategoriesResponse> call = apiService.getSubCategories(json);
        Log.i("call",""+json);
        call.enqueue(new Callback<SubCategoriesResponse>() {
            @Override
            public void onResponse(Call<SubCategoriesResponse> call, Response<SubCategoriesResponse> response) {
//                mProgress.hide();
                mSubCategoryList= response.body().getmSubCategoriesList();
                Log.d(TAG, "Number of movies received sub: " +mSubCategoryList.size());
                showSubCategory(mSubCategoryList);
//                getSubCategories();
            }

            @Override
            public void onFailure(Call<SubCategoriesResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                mProgress.hide();
            }
        });

    }
    private void getCategories() {
        mProgress.show();
        Call<CategoriesResponse> call = apiService.getCategories();
        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse>call, Response<CategoriesResponse> response) {
                mProgress.hide();
                mCategoryList= response.body().getmCategoriesList();
                Log.d(TAG, "Number of movies received: " + mCategoryList.size());
                showCategory(mCategoryList);
//                getSubCategories();
            }

            @Override
            public void onFailure(Call<CategoriesResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                mProgress.hide();
            }
        });
    }
    private void showCategory(List<CategoriesListResponse> mCategoryList) {
//        mCategoryList = new ArrayList<>();
        CategoryAdapter mCategoryAdapter = new CategoryAdapter(getContext(), mCategoryList);
        categorySpinner.setAdapter(mCategoryAdapter);

    }

    private void showSubCategory(List<SubCategoriesListResponse> mSubCategoryList) {
        SubCategoryAdapter mSubCategoryAdapter = new SubCategoryAdapter(getContext(), mSubCategoryList);
        subCategoSpinner.setAdapter(mSubCategoryAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // Decodes image and scales it to reduce memory consumption
    private Bitmap decodeFile(File f) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // The new size we want to scale to
            final int REQUIRED_SIZE=70;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }

  /*  *//**
     * Upload Image
     * @param sourceImageFile
     * @return
     *//*
    public static JSONObject uploadImage(String upload_url, List<String> sourceImageFile) {

        try {
            final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

            MultipartBody.Builder buildernew = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("title", "title");

            for (int i = 0; i < sourceImageFile.size(); i++) {
                File f = new File(sourceImageFile.get(i));
                if (f.exists()) {
                    buildernew.addFormDataPart("Picture" + i, f.getName() + ".png", RequestBody.create(MEDIA_TYPE_PNG, f));
                }
            }

            MultipartBody requestBody = buildernew.build();

            Request request = new Request.Builder()
                    .url(upload_url)
                    .post(requestBody)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            return new JSONObject(response.body().string());

        } catch (UnknownHostException | UnsupportedEncodingException e) {
            Log.e(TAG, "Error: " + e.getLocalizedMessage());
        } catch (Exception e) {
            Log.e(TAG, "Other Error: " + e.getLocalizedMessage());
        }
        return null;
    }*/
}


