package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.techsmartsolutions.R;

import java.util.HashMap;

import model.ForgotPasswordResponse;
import network.ApiGsonRequest;
import network.VolleySingleton;
import utility.Constants;
import utility.DialogUtility;
import utility.IJRDataModel;
import utility.Logger;
import utility.NetworkUtility;
import utility.Utils;

/**
 * Created by radhakrishna on 22/7/16.
 */
public class ForgotPassword extends Fragment {

    public static ForgotPassword createInstance(){
        ForgotPassword forgotPassword = new ForgotPassword();
        return forgotPassword;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password,container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
         final EditText editText = (EditText) view.findViewById(R.id.input_forgot_email);
        AppCompatButton sendButton = (AppCompatButton) view.findViewById(R.id.btn_send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editEmail = editText.getText().toString().trim();
                if(Utils.isValidEmail(editEmail) && NetworkUtility.isNetworkAvailable(getActivity())){
                    HashMap<String,String> stringMap = new HashMap();
                    stringMap.put("email",editEmail);
                    String url = Constants.BASE_URL + "user/forget";
                    DialogUtility.showProgressDialog(getContext(),true);
                    VolleySingleton.getRequestQueue(getContext()).add(new ApiGsonRequest(ApiGsonRequest.MethodType.POST,url,successListener,errorListener,new ForgotPasswordResponse(),null, Utils.getPostDataString(stringMap)));
                }
            }
        });
    }

    Response.Listener<IJRDataModel> successListener = new Response.Listener<IJRDataModel>() {
        @Override
        public void onResponse(IJRDataModel response) {
            DialogUtility.cancelProgressDialog();
            if(response instanceof ForgotPasswordResponse){
                ForgotPasswordResponse forgotPasswordResponse = (ForgotPasswordResponse)response;
                Logger.d("RESPONSE ",">>"+forgotPasswordResponse.getDescription());
            }

        }
    };

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            DialogUtility.cancelProgressDialog();
        }

    };
}
