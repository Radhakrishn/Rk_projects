package adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.app.techsmartsolutions.R;

import java.util.ArrayList;
import java.util.List;

import model.CategoriesListResponse;


public class CategoryAdapter extends BaseAdapter {

    private Context mCntxt;
    private List<CategoriesListResponse> mCategoriesList = new ArrayList<>();

    public CategoryAdapter(Context mCntxt, List<CategoriesListResponse> mCategoriesList) {
        this.mCntxt = mCntxt;
        this.mCategoriesList = mCategoriesList;
    }

    @Override
    public int getCount() {
        return mCategoriesList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder {
        TextView mListTextView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater mInflater = (LayoutInflater) mCntxt.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.category_list_layout, null);
            holder = new ViewHolder();
            holder.mListTextView = (TextView) convertView.findViewById(R.id.list_item);
            holder.mListTextView.setText(mCategoriesList.get(position).getCategory());
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;

    }
}
