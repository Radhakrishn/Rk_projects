package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.techsmartsolutions.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Created by user on 6/2/2016.
 */
public class LoginBaseFragment extends Fragment implements View.OnClickListener{

    private LoginButton fbLoginButton;
    private SignInButton googleLoginBtn;
    private CallbackManager callbackManager;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;

    public static LoginBaseFragment createInstance(){
        LoginBaseFragment loginBaseFragment =  new LoginBaseFragment();
        return loginBaseFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lyt_loginbase_fragment, container, false);
        initViews(view);
        fbRegisterCallBack();

        return view;
    }

    private void initViews(View view) {
        fbLoginButton = (LoginButton) view.findViewById(R.id.fb_login_button);
        googleLoginBtn = (SignInButton) view.findViewById(R.id.google_sign_in_button);
        googleLoginBtn.setOnClickListener(this);
    }

    private void fbRegisterCallBack() {
        fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() ==  R.id.google_sign_in_button){
            signIn();
        }

    }

    private void setUpGoogleApiCLient(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage((FragmentActivity) getActivity() /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        googleLoginBtn.setSize(SignInButton.SIZE_STANDARD);
        googleLoginBtn.setScopes(gso.getScopeArray());
    }

    // [START signIn]
    private void signIn() {
        setUpGoogleApiCLient();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signIn]

    // [START signOut]
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                    }
                });
    }
    // [END signOut]
}
