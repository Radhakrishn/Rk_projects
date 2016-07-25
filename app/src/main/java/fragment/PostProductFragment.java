package fragment;

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

import com.app.techsmartsolutions.R;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by user on 7/24/2016.
 */
public class PostProductFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "PostProductFragment";
    private EditText productName;
    private EditText productDescName;
    private List<String> mCategoryList;
    private List<String> mSubCategoryList;
    private Spinner categorySpinner;
    private Spinner subCategoSpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_product, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        categorySpinner = (Spinner) view.findViewById(R.id.spinner_category);
        subCategoSpinner = (Spinner) view.findViewById(R.id.spinner_subcategory);
        productName = (EditText) view.findViewById(R.id.edit_productname);
        productDescName = (EditText) view.findViewById(R.id.edit_product_desc);
        categorySpinner.setOnItemSelectedListener(this);
        subCategoSpinner.setOnItemSelectedListener(this);
    }

    private void showCategoty() {
        mCategoryList = new ArrayList<>();
        ArrayAdapter<String> categoryArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, mCategoryList);
        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryArrayAdapter);

    }

    private void showSubCategory() {
        mSubCategoryList = new ArrayList<>();
        ArrayAdapter<String> categoryArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, mSubCategoryList);

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

    /**
     * Upload Image
     * @param sourceImageFile
     * @return
     */
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
    }
}


