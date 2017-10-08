package retrofit;

import com.google.gson.JsonObject;


import java.util.List;
import java.util.Map;

import model.CategoriesResponse;
import model.ForgotPasswordResponse;
import model.SignUpResponse;
import model.SubCategoriesResponse;
import model.SubCategoryRequest;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by H177273 on 9/26/2016.
 */
public interface RetrofitInterface {

        @GET("Category/get_category")
        Call<CategoriesResponse> getCategories();

//        @Headers("Content-Type: application/json")
        @POST("Category/get_sub_category")
        Call<SubCategoriesResponse> getSubCategories(@Body JsonObject request);

        /*@POST("Category/get_sub_category")
        Call<SubCategoriesResponse> getSubCategories(@Body JsonObject request);*/

        /*@POST("/api/locations/{locationId}/devices/hub")
        public void addCameraDevice(@Path("locationId") int locationId,
                                    @Body CreateDeviceRequest request,
                                    Callback<CreateAwarenessCameraDeviceResult> cb);
*/
       /* “
        socialtype”:1-Normal,2-facebook,3-google plus
                (in the case of 1 you need to pass password field too)
        “email”: Need to get email id
        “password”:if the value of socialtype=1 then only this field will come
        “socialid”: Need to get based on the social type
        “firstname”: First Name
        “lastname”: Lastname
        “phone”: Phone number
        “device_token”:device token which is used for push notification
        “ostye”: 1-Android,2-IOS}*/

        @POST("user/login")
        Call<SignUpResponse> initSignup(@Body JsonObject request);

        @POST("user/forget")
        Call<ForgotPasswordResponse> forgotPassword(@Body JsonObject request);

}
