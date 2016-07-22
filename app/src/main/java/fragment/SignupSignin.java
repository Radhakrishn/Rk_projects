package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.techsmartsolutions.R;

import activity.MainActivity;
import utility.Constants;

/**
 * Created by user on 7/9/2016.
 */
public class SignupSignin extends Fragment implements View.OnClickListener {

    private EditText mNameText;
    private EditText mEmailText;
    private EditText mPasswordText;
    private AppCompatButton mSignUpBtn;
    private TextView mLinkButton;
    private TextInputLayout mNameLayout;
    private boolean isSignUp = false;
    private AppCompatButton mForgotPassBtn;

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
        if (getArguments() != null) {
            isSignUp = getArguments().getBoolean(Constants.IS_SIGNUP);
        }
    }

    private void initViews(View view) {
        mNameText = (EditText) view.findViewById(R.id.input_name);
        mEmailText = (EditText) view.findViewById(R.id.input_email);
        mPasswordText = (EditText) view.findViewById(R.id.input_password);
        mSignUpBtn = (AppCompatButton) view.findViewById(R.id.btn_signup);
        mLinkButton = (TextView) view.findViewById(R.id.link_login);
        mNameLayout = (TextInputLayout) view.findViewById(R.id.name_layout);
        mForgotPassBtn = (AppCompatButton) view.findViewById(R.id.btn_forgot_password);
        mSignUpBtn.setOnClickListener(this);
        mLinkButton.setOnClickListener(this);
        mForgotPassBtn.setOnClickListener(this);
        hideNameLayout();
    }

    public boolean validate() {
        boolean valid = true;

        String name = mNameText.getText().toString();
        String email = mEmailText.getText().toString();
        String password = mPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            mNameText.setError("at least 3 characters");
            valid = false;
        } else {
            mNameText.setError(null);
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

        return valid;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_signup) {
            signup();
        } else if (v.getId() == R.id.link_login) {
            if (isSignUp) {
                mLinkButton.setText(getString(R.string.already_a_member_login));
                isSignUp = false;
            } else {
                mLinkButton.setText(getString(R.string.no_account_yet));
                isSignUp = true;
            }
            hideNameLayout();
        } else if (v.getId() == R.id.btn_forgot_password) {
            ((MainActivity) getActivity()).loadFragmentWithckStack(ForgotPassword.createInstance());
        }
    }

    private void signup() {
        if (!validate()) {
            onSignupFailed();
            return;
        }
        //TODO call sign up api
    }

    public void onSignupFailed() {
        Toast.makeText(getContext(), "Login failed", Toast.LENGTH_LONG).show();
        mSignUpBtn.setEnabled(true);
    }

    private void hideNameLayout() {
        if (isSignUp) {
            mNameLayout.setVisibility(View.VISIBLE);
            mForgotPassBtn.setVisibility(View.GONE);
        } else {
            mNameLayout.setVisibility(View.GONE);
            mForgotPassBtn.setVisibility(View.VISIBLE);
        }
    }
}
