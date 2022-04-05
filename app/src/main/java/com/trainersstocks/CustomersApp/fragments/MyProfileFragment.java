package com.trainersstocks.CustomersApp.fragments;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;

import com.trainersstocks.CustomersApp.Models.Payment;
import com.trainersstocks.CustomersApp.Models.PaymentModel;
import com.trainersstocks.CustomersApp.Models.ResponseModel;
import com.trainersstocks.CustomersApp.Models.UMModel;
import com.trainersstocks.CustomersApp.Models.UserDetails;
import com.trainersstocks.CustomersApp.Models.UserDetailsMModel;
import com.trainersstocks.CustomersApp.Models.UserDetailsModel;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.MyLog;
import com.trainersstocks.CustomersApp.Utils.PreferenceManger;
import com.trainersstocks.CustomersApp.Utils.ProgressLoader;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractFragment;
import com.trainersstocks.CustomersApp.retrofit_provider.RetrofitService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyProfileFragment extends MyAbstractFragment {

    @BindView(R.id.txt_name)
    TextView txt_name;
    @BindView(R.id.txt_id)
    TextView txt_id;
    @BindView(R.id.tv_emailname)
    AppCompatEditText tv_emailname;
    @BindView(R.id.tv_mobile)
    AppCompatEditText tv_mobile;
    @BindView(R.id.tv_pannu)
    AppCompatEditText tv_pannu;
    @BindView(R.id.tv_aadhar)
    AppCompatEditText tv_aadhar;
    @BindView(R.id.tv_dob)
    AppCompatEditText tv_dob;
    @BindView(R.id.civ)
    CircleImageView civ;

    @BindView(R.id.rl_edit_btn)
    RelativeLayout rl_edit_btn;

    @BindView(R.id.rl_submit_btn)
    RelativeLayout rl_submit_btn;

    private UserDetailsMModel userDetail = null;
    private String SELECT_BASE_64_PATH="";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        ButterKnife.bind(this, view);
        Glide.with(getContext()).load(R.drawable.ic_male_avatar).into(civ);
        initViews(view);
        initlistners();
        showDialog();
        getUserDeatils();
        /*if (userDetail.getUserId() != null){
            generateHash();
        }*/

        return view;
    }

    private void getUserDeatils() {
        Map<String, String> jsonParams = new ArrayMap<>();

        jsonParams.put("id", PreferenceManger.getUserDetailS().getUserDetails().getId());
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

        Call<UMModel> call = RetrofitService.RetrofitService().getUserdetail(body);
        call.enqueue(new Callback<UMModel>() {
            @Override
            public void onResponse(Call<UMModel> call, Response<UMModel> response) {
                Log.e("TAG", ">>onResponse onResponse " + new Gson().toJson(response.body()));
                dismissDialog();
                if (response.body() != null && !response.body().getError()) {
                    userDetail = response.body().getModel();
                    setData(response.body().getModel());
                } else {
                    Toasty.error(getContext(), "Failed to load data", Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UMModel> call, Throwable t) {
                Toasty.error(getContext(), "Failed to load data", Toasty.LENGTH_LONG).show();
               dismissDialog();
            }
        });
    }

    private void setData(UserDetailsMModel model) {
        if (model == null) {
            return;
        }
        MyLog.LogE("TAG", ">>   " + new Gson().toJson(model));
        txt_name.setText(model.getFirstName() + " "+ model.getLastName());
        txt_id.setText(model.getUserName());
        tv_emailname.setText(model.getEmail());
        tv_mobile.setText(model.getPhone());
        tv_pannu.setText(model.getPanCard());
        tv_dob.setText(model.getDob());
        tv_aadhar.setText(model.getAadharNo());

    }


    public static ProgressDialog dialog;



    public void showDialog() {
        dialog = new android.app.ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.show();
    }

    public void dismissDialog() {
        if (dialog != null){
            dialog.cancel();
        }
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void initlistners() {

    }
    @OnClick(R.id.back)
    public void back(){
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @OnClick(R.id.rl_edit_btn)
    public void editProfile(){
        tv_aadhar.setEnabled(true);
        tv_emailname.setEnabled(true);
        tv_dob.setEnabled(true);
        tv_mobile.setEnabled(true);
        tv_pannu.setEnabled(true);
        tv_emailname.setFocusable(true);
        rl_edit_btn.setVisibility(View.GONE);
        rl_submit_btn.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.rl_submit_btn)
    public void submitDetails(){
        tv_mobile.setEnabled(false);
        tv_aadhar.setEnabled(false);
        tv_dob.setEnabled(false);
        tv_pannu.setEnabled(false);
        tv_emailname.setEnabled(false);
        rl_edit_btn.setVisibility(View.VISIBLE);
        rl_submit_btn.setVisibility(View.GONE);

        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("id", userDetail.getUserId());
        jsonParams.put("first_name", userDetail.getFirstName());
        jsonParams.put("last_name", userDetail.getLastName());
        jsonParams.put("email", tv_emailname.getText().toString());
        jsonParams.put("phone", tv_mobile.getText().toString());
        jsonParams.put("dob", tv_dob.getText().toString());
        jsonParams.put("pan_card", tv_pannu.getText().toString());
        jsonParams.put("account_no", userDetail.getAccountNo());
        jsonParams.put("status_detail", userDetail.getStatusDetail());
        jsonParams.put("is_active", userDetail.getIsActive());
        jsonParams.put("view_password", userDetail.getViewPassword());
        Log.e("TAG", ">>  " + new JSONObject(jsonParams));
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());
        Call<ResponseModel> call = RetrofitService.RetrofitService1().user_update(body);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.e("TAG", ">>  " + new Gson().toJson(response.body()));
                Log.e("TAG", ">>  code " + response.code());
                if (response.body() != null && !response.body().getError()) {
                    Toasty.success(getContext(), "Update Success", Toast.LENGTH_SHORT, true).show();

                } else {
                    Toasty.error(getContext(), "Failed to update.", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("TAG", ">>  " + t.getMessage());
                Toasty.error(getContext(), "Failed to login.", Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    @OnClick(R.id.tv_dob)
    public void openCalender() {
        Calendar mCalendar = new GregorianCalendar();
        mCalendar.setTime(new Date());
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), R.style.my_dialog_theme, (view, year1, monthOfYear, dayOfMonth) -> {
            //do something with the date
            String finalDay = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
            String finalMonth = (monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : String.valueOf((monthOfYear + 1));
            tv_dob.setText(finalDay+"/"+ finalMonth + "/" +year1 );
        }, year, month, day);

        Calendar maxCal = Calendar.getInstance();
        maxCal.add(Calendar.YEAR, -12);
        Calendar minCal = Calendar.getInstance();
        minCal.add(Calendar.YEAR, -99);
        dialog.getDatePicker().setMaxDate(maxCal.getTimeInMillis());
        dialog.getDatePicker().setMinDate(minCal.getTimeInMillis());
        dialog.show();
    }

    @OnClick(R.id.civ)
    public void selectProfile(){
        ImagePicker.Companion.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String uri = ImagePicker.Companion.getFilePath(data);
            Uri s_uri = Uri.parse(uri);
            civ.setImageURI(s_uri);
            this.SELECT_BASE_64_PATH = getBase64FromFile(uri);

            uploadPic();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("profile_image", "data:image/jpeg;base64," + SELECT_BASE_64_PATH);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Log.d("MainActivity", "request code " + requestCode + " resultcode " + resultCode);
    }
    public String getBase64FromFile(String path) {
        Bitmap bmp = null;
        ByteArrayOutputStream baos = null;
        byte[] baat = null;
        String encodeString = null;
        try {
            bmp = BitmapFactory.decodeFile(path);
            baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            baat = baos.toByteArray();
            encodeString = Base64.encodeToString(baat, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encodeString;
    }
   /* public void generateHash(){

        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("user_id", userDetail.getUserId());
        jsonParams.put("payble_amount","1");

        Log.e("TAG", ">>  " + new JSONObject(jsonParams));
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());
        Call<PaymentModel> call = RetrofitService.RetrofitService1().getHash(body);
        call.enqueue(new Callback<PaymentModel>() {
            @Override
            public void onResponse(Call<PaymentModel> call, Response<PaymentModel> response) {
                Log.e("TAG", ">>  " + new Gson().toJson(response.body()));
                Log.e("TAG", ">>  code " + response.code());
                if (response.body() != null && !response.body().getError()) {
                    Log.e("TAG", ">>  " + new Gson().toJson(response.body()));
                    Payment model = response.body().getData();
                    initPayUMoney(model.getHash(),model.getMkey(),"","1",
                            model.getTid(),"9479739973","Trainer Stock",
                            "Anubhav","anubhavjain1995@gmail.com"
                            );
                } else {
                    Log.e("TAG", ">>  " + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<PaymentModel> call, Throwable t) {
                Log.e("TAG", ">>  " + t.getMessage());
                Toasty.error(getContext(), "Failed to login.", Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    private void initPayUMoney(String hash,String mer_key,String mer_id,String amount,String txnId,String phone,String productName,String firstName,String email) {
          PayUmoneySdkInitializer.PaymentParam.Builder builder = new
                PayUmoneySdkInitializer.PaymentParam.Builder();
        builder.setAmount(amount)                          // Payment amount
                .setTxnId(txnId)                                             // Transaction ID
                .setPhone(phone)                                           // User Phone number
                .setProductName(productName)                   // Product Name or description
                .setFirstName(firstName)                              // User First name
                .setEmail(email)                                            // User Email ID
                //.setsUrl(appEnvironment.surl())                    // Success URL (surl)
                //.setfUrl(appEnvironment.furl())                     //Failure URL (furl)
                .setIsDebug(true)                              // Integration environment - true (Debug)/ false(Production)
                .setKey(mer_key)  ;                      // Merchant key
               // .setMerchantId(mer_id);

        //declare paymentParam object
        PayUmoneySdkInitializer.PaymentParam paymentParam = null;
        try {
            paymentParam = builder.build();
            paymentParam.setMerchantHash(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Invoke the following function to open the checkout page.
        PayUmoneyFlowManager.startPayUMoneyFlow(
                paymentParam,
                (Activity) getContext(),
                R.style.AppTheme_default,
        false);

    }*/

 public void uploadPic(){
        showDialog();
        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("user_id", userDetail.getUserId());
        jsonParams.put("profile_picture","data:image/jpeg;base64," + SELECT_BASE_64_PATH);

        Log.e("TAG", ">>  " + new JSONObject(jsonParams));
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());
        Call<ResponseModel> call = RetrofitService.RetrofitService1().uploadProfilePic(body);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                dismissDialog();
                Log.e("TAG", ">>  " + new Gson().toJson(response.body()));
                Log.e("TAG", ">>  code " + response.code());
                if (response.body() != null && !response.body().getError()) {
                    Log.e("TAG", ">>  " + new Gson().toJson(response.body()));

                } else {
                    Log.e("TAG", ">>  " + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                dismissDialog();
                Log.e("TAG", ">>  " + t.getMessage());
                Toasty.error(getContext(), "Failed to login.", Toast.LENGTH_SHORT, true).show();
            }
        });
    }

}