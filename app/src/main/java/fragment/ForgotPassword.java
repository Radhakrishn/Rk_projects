package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.techsmartsolutions.R;
import com.google.gson.JsonObject;

import java.util.HashMap;

import activity.MainActivity;
import model.ForgotPasswordResponse;
import model.SignUpResponse;
import network.ApiGsonRequest;
import network.VolleySingleton;
import retrofit.RetrofitClient;
import retrofit.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
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

    RetrofitInterface apiService;
    EditText mEmailEdit;

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
        apiService = RetrofitClient.getClient().create(RetrofitInterface.class);
        mEmailEdit = (EditText) view.findViewById(R.id.input_forgot_email);
        AppCompatButton sendButton = (AppCompatButton) view.findViewById(R.id.btn_send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(mEmailEdit.getText().toString()) && NetworkUtility.isNetworkAvailable(getActivity())) {
                    forgotPasswordRequest();
                }else{
                    Toast.makeText(getActivity(),"Error. Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void forgotPasswordRequest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email",mEmailEdit.getText().toString());
        Log.i("call",""+jsonObject);
        Call<ForgotPasswordResponse> call = apiService.forgotPassword(jsonObject);

        call.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, retrofit2.Response<ForgotPasswordResponse> response) {
                Log.i("Forgot Password", response.body().getDescription());
                if(response.body().getErrorCode().equals("E05")) {
                    ((MainActivity) getActivity()).loadFragmentWithckStack(SignupSignin.createInstance(false));
                }else{
                    Toast.makeText(getActivity(),"Error. Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"Error. Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
