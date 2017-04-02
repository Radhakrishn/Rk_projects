package fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.techsmartsolutions.R;
import com.google.gson.JsonObject;

import activity.MainActivity;
import model.CategoriesResponse;
import model.SignUpResponse;
import model.SubCategoriesResponse;
import retrofit.RetrofitClient;
import retrofit.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utility.Constants;
import utility.NetworkUtility;

/**
 * Created by user on 7/9/2016.
 */
public class SignupSignin extends Fragment implements View.OnClickListener {

    private EditText mFirstNameText, mLastNameText;
    private EditText mEmailText, mPhoneText;
    private EditText mPasswordText;
    private AppCompatButton mSignUpBtn;
    private TextView mLinkButton;
//    private TextInputLayout mNameLayout;
    private boolean isSignUp = false;
    private AppCompatButton mForgotPassBtn;
    ProgressDialog mProgress;

    RetrofitInterface apiService;

    public static SignupSignin createInstance(boolean isSignUp) {
        SignupSignin signupSignin = new SignupSignin();
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.IS_SIGNUP, isSignUp);
        signupSignin.setArguments(bundle);
        return signupSignin;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgress = new ProgressDialog(getContext());
        if (getArguments() != null) {
            isSignUp = getArguments().getBoolean(Constants.IS_SIGNUP);
        }
    }

    private void initViews(View view) {
        apiService = RetrofitClient.getClient().create(RetrofitInterface.class);
        mFirstNameText = (EditText) view.findViewById(R.id.input_first_name);
        mLastNameText = (EditText) view.findViewById(R.id.input_last_name);
        mEmailText = (EditText) view.findViewById(R.id.input_email);
        mPhoneText = (EditText) view.findViewById(R.id.input_phone);
        mPasswordText = (EditText) view.findViewById(R.id.input_password);
        mSignUpBtn = (AppCompatButton) view.findViewById(R.id.btn_signup);
        mLinkButton = (TextView) view.findViewById(R.id.link_login);
//        mNameLayout = (TextInputLayout) view.findViewById(R.id.name_layout);
        mForgotPassBtn = (AppCompatButton) view.findViewById(R.id.btn_forgot_password);
        mSignUpBtn.setOnClickListener(this);
        mLinkButton.setOnClickListener(this);
        mForgotPassBtn.setOnClickListener(this);
        changeLayouts();
    }

    private void changeLayouts(){
        if(isSignUp){
            showSignUpLayout();
        } else{
            showLoginLayout();
        }
    }

    private  void clearAllFields () {
        mFirstNameText.setText("");
        mLastNameText.setText("");
        mPhoneText.setText("");
        mEmailText.setText("");
        mPasswordText.setText("");
    }
    private void showSignUpLayout() {
        clearAllFields();
        mLinkButton.setText(getString(R.string.already_a_member_login));
        mForgotPassBtn.setVisibility(View.GONE);
        mFirstNameText.setVisibility(View.VISIBLE);
        mLastNameText.setVisibility(View.VISIBLE);
        mPhoneText.setVisibility(View.VISIBLE);
        mSignUpBtn.setText("Create Account");
    }

    private void showLoginLayout() {
        clearAllFields();
        mFirstNameText.setVisibility(View.GONE);
        mLastNameText.setVisibility(View.GONE);
        mPhoneText.setVisibility(View.GONE);
        mLinkButton.setText(getString(R.string.no_account_yet));
        mForgotPassBtn.setVisibility(View.VISIBLE);
        mForgotPassBtn.setText("Forgot Password?");
        mSignUpBtn.setText("Login");
    }
    public boolean validate() {
        boolean valid = true;

        String name = mFirstNameText.getText().toString();
        String email = mEmailText.getText().toString();
        String password = mPasswordText.getText().toString();
        String phone = mPhoneText.getText().toString();

        if (mFirstNameText.getVisibility() == View.VISIBLE &&(name.isEmpty() || name.length() < 3)) {
            mFirstNameText.setError("at least 3 characters");
            valid = false;
        } else {
            mFirstNameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailText.setError("enter a valid email address");
            valid = false;
        } else {
            mEmailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            mPasswordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            mPasswordText.setError(null);
        }

        if(mPhoneText.getVisibility() == View.VISIBLE && (TextUtils.isEmpty(phone) || phone.length() < 10 || phone.length() > 10)) {
            mPhoneText.setError("Phone number should be of 10 digits");
            valid = false;
        } else{
            mPhoneText.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_signup) {
            signup();
        } else if (v.getId() == R.id.link_login) {
            if (mLinkButton.getText().toString().equals(getString(R.string.no_account_yet))) {
                mLinkButton.setText(getString(R.string.already_a_member_login));
                showSignUpLayout();
            } else if(mLinkButton.getText().toString().equals(getString(R.string.already_a_member_login))) {
                mLinkButton.setText(getString(R.string.no_account_yet));
                showLoginLayout();
            }
        } else if (v.getId() == R.id.btn_forgot_password) {
            ((MainActivity) getActivity()).loadFragmentWithckStack(ForgotPassword.createInstance());
        }
    }

    private void signup() {
        mProgress.show();
        if (!validate()) {
            onSignupFailed();
            return;
        }else{
            if(NetworkUtility.isNetworkAvailable(getContext())) {
                signUpRequest();
            }else{
                onSignupFailed();
                return;
            }
        }
    }

    private void signUpRequest() {
        JsonObject json = new JsonObject();
        json.addProperty("socialtype", "1");
        json.addProperty("email",mEmailText.getText().toString());
        json.addProperty("password",mPasswordText.getText().toString());
        json.addProperty("socialid","");
        json.addProperty("firstname",mFirstNameText.getText().toString());
        json.addProperty("lastname",mLastNameText.getText().toString());
        json.addProperty("phone",mPhoneText.getText().toString());
        json.addProperty("device_token","");
        json.addProperty("ostye","Android");


        Call<SignUpResponse> call = apiService.initSignup(json);
        Log.i("call",""+json);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("SignIn", response.body().getmErrorCode());
                if(response.body().getmErrorCode().equals("E05")) {
                    mProgress.dismiss();
                    showLoginLayout();
                }else{
                    onSignupFailed();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("SignIn", t.toString());
                onSignupFailed();
            }
        });
    }
    public void onSignupFailed() {
        mProgress.dismiss();
        Toast.makeText(getContext(), "Cannot create an account. Please try again later", Toast.LENGTH_LONG).show();
        mSignUpBtn.setEnabled(true);
    }

    private void hideNameLayout() {
        if (isSignUp) {
//            mNameLayout.setVisibility(View.VISIBLE);
            mForgotPassBtn.setVisibility(View.GONE);
        } else {
//            mNameLayout.setVisibility(View.GONE);
            mForgotPassBtn.setVisibility(View.VISIBLE);
        }
    }
}
