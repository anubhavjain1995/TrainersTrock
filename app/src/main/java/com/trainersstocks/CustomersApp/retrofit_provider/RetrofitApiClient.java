package com.trainersstocks.CustomersApp.retrofit_provider;


import com.trainersstocks.CustomersApp.Models.BuyStockListModel;
import com.trainersstocks.CustomersApp.Models.CustomerPayDetail;
import com.trainersstocks.CustomersApp.Models.CustomerPayListModel;
import com.trainersstocks.CustomersApp.Models.LoginModel;
import com.trainersstocks.CustomersApp.Models.ResponseModel;
import com.trainersstocks.CustomersApp.Models.SellStockListModel;
import com.trainersstocks.CustomersApp.Models.TokenMainModel;
import com.trainersstocks.CustomersApp.Models.UMModel;
import com.trainersstocks.CustomersApp.Models.UserListModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitApiClient {

/*
    @POST("GetRateUserWise")
    @FormUrlEncoded
    Call<MainModel> rateUs(@Field("user_id") String userID, @Field("rate_us") String rate);

    @POST("UpdateProfilePicture")
    @Multipart
    Call<MainModel> UpdateProfilePicture(@Part("user_id") RequestBody user_id,
                                         @Part MultipartBody.Part file);

    @GET("GetAppInstallation")
    Call<ApplicationMainModel> getapplications();

    @GET("GetNotification")
    Call<NotificationMainModel> getNotification();

    @GET("GetOffersSliders")
    Call<SliderMainModel> getOfferSlider();
*/

    @Headers("Content-Type: application/json")
    @GET("user-list")
    Call<UserListModel> getUserList();

    @Headers("Content-Type: application/json")
    @GET("stock-sell-lists/{userid}")
    Call<SellStockListModel> stock_sell_lists(@Path("userid") String userid);

    @Headers("Content-Type: application/json")
    @GET("stock-buy-lists/{userid}")
    Call<BuyStockListModel> stock_buy_lists(@Path("userid") String userid);

    @Headers("Content-Type: application/json")
    @GET("customer-pay-list/{userid}")
    Call<CustomerPayListModel> customer_pay_list(@Path("userid") String userid);


    @Headers("Content-Type: application/json")
    @POST("user-delete")
    Call<ResponseModel> deleteUser(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("get-cashfree-token")
    Call<TokenMainModel> getToken(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("user-add")
    Call<ResponseModel> user_add(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("user-update")
    Call<ResponseModel> user_update(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("user-details")
    Call<UMModel> getUserdetail(@Body RequestBody body);


    @Headers("Content-Type: application/json")
    @POST("stock-buy-add")
    Call<ResponseModel> stock_buy_add(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("stock-buy-update")
    Call<ResponseModel> stock_buy_update(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("stock-buy-delete")
    Call<ResponseModel> stock_buy_delete(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("stock-sell-add")
    Call<ResponseModel> stock_sell_add(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("stock-sell-update")
    Call<ResponseModel> stock_sell_update(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("stock-sell-delete")
    Call<ResponseModel> stock_sell_delete(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("customer-pay-details")
    Call<CustomerPayDetail> customer_pay_details(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("customer-pay-add")
    Call<ResponseModel> customer_pay_add(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("customer-pay-update")
    Call<ResponseModel> customer_pay_update(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("customer-pay-delete")
    Call<ResponseModel> customer_pay_delete(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("user-login")
    Call<LoginModel> admin_login(@Body RequestBody body);

    @Headers("Content-Type: application/json")
    @POST("admin/withdrawalSubmit")
    Call<ResponseModel> withdrawalSubmit(@Body RequestBody body);


}