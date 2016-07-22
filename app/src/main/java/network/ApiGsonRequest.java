
package network;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import utility.IJRDataModel;


public class ApiGsonRequest extends Request<IJRDataModel> {

    private final Listener<IJRDataModel> mListener;
    private IJRDataModel mDataModel;
    private final Gson mGson;
    private Map<String, String> mHeaders;
    private String TAG = ApiGsonRequest.class.getName();
    public static final int MY_SOCKET_TIMEOUT_MS = 60000;
    private String mUrl;
    private String mRequestBody;

    public enum MethodType {
        GET, POST,PUT,DEL
    }

    /** Charset for request. */
    private static final String PROTOCOL_CHARSET = "utf-8";

    /** Content type for request. */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);
    /**
     * Charset for request.
     */

    public ApiGsonRequest(MethodType methodType, String url, Listener<IJRDataModel> listener, ErrorListener errorListener, IJRDataModel model) {
        super(getVolleyMethodType(methodType), url, errorListener);
        mListener = listener;
        mDataModel = model;
        mGson = new Gson();
        mUrl = url;
    }

    public ApiGsonRequest(MethodType methodType, String url, Listener<IJRDataModel> listener, ErrorListener errorListener, IJRDataModel model, Map<String, String> headers) {
        super(getVolleyMethodType(methodType), url, errorListener);
        mListener = listener;
        mDataModel = model;
        mGson = new Gson();
        mHeaders = headers;
        mUrl = url;
    }

    public ApiGsonRequest(MethodType methodType, String url, Listener<IJRDataModel> listener, ErrorListener errorListener, IJRDataModel model, Map<String, String> headers, String requestBody) {
        super(getVolleyMethodType(methodType), url, errorListener);
        mListener = listener;
        mDataModel = model;
        mGson = new Gson();
        mHeaders = headers;
        mUrl = url;
        mRequestBody = requestBody;
    }

    @Override
    protected void deliverResponse(IJRDataModel model) {
        mListener.onResponse(model);
    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
    }

    @Override
    public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
        retryPolicy = new DefaultRetryPolicy(MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        return super.setRetryPolicy(retryPolicy);
    }

    @Override
    protected Response<IJRDataModel> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data);
            int statusCode = response.statusCode;
			/*if(statusCode == CJRConstants.ERROR_CODE_401 || statusCode == CJRConstants.ERROR_CODE_410){
				throw new IllegalErrorCode(jsonString);
			}
			if(statusCode == CJRConstants.ERROR_CODE_499  || statusCode == CJRConstants.ERROR_CODE_502
					|| statusCode == CJRConstants.ERROR_CODE_503 || statusCode == CJRConstants.ERROR_CODE_504 ) {
				throw new IllegalErrorCode(jsonString);
			}*/
            return Response.success((IJRDataModel) mGson.fromJson(jsonString, mDataModel.getClass()), getCacheEntry());
        } catch (Exception ex) {
            return null;
        }


    }

    @Override
    public Map<String, String> getHeaders() {
        try {
            return mHeaders != null ? mHeaders : super.getHeaders();
        } catch (AuthFailureError exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException uee) {

            return null;
        }
    }


    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }
    private static int getVolleyMethodType(MethodType methodType) {
        if (methodType.equals(MethodType.GET)) return Method.GET;
        else if (methodType.equals(MethodType.POST)) return Method.POST;
        else if (methodType.equals(MethodType.DEL)) return Method.DELETE;
        else if (methodType.equals(MethodType.PUT)) return Method.PUT;
        else return Method.GET;
    }


}
