package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.techsmartsolutions.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 7/24/2016.
 */
public class PostProductFragment extends Fragment implements AdapterView.OnItemSelectedListener {
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
}
