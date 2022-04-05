package com.trainersstocks.CustomersApp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.trainersstocks.CustomersApp.Models.LoginModel;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.AppHelper;
import com.trainersstocks.CustomersApp.Utils.PreferenceManger;
import com.trainersstocks.CustomersApp.abstractactivity.MyAbstractActivity;
import com.trainersstocks.CustomersApp.retrofit_provider.RetrofitService;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends MyAbstractActivity {
    @BindView(R.id.txt_fname)
    TextInputLayout txt_fname;
    @BindView(R.id.txt_lname)
    TextInputLayout txt_lname;
    @BindView(R.id.txt_u_name)
    TextInputLayout txt_u_name;
    @BindView(R.id.txt_email)
    TextInputLayout txt_email;
    @BindView(R.id.txt_password)
    TextInputLayout txt_password;
    @BindView(R.id.txt_pan)
    TextInputLayout txt_pan;
    @BindView(R.id.txt_aadhar)
    TextInputLayout txt_aadhar;
    @BindView(R.id.txt_ac_no)
    TextInputLayout txt_ac_no;
    @BindView(R.id.txt_phone)
    TextInputLayout txt_phone;
    @BindView(R.id.txt_dob)
    TextInputLayout txt_dob;
    @BindView(R.id.txt_ifsc)
    TextInputLayout txt_ifsc;
    @BindView(R.id.edt_f_name)
    TextInputEditText edt_f_name;
    @BindView(R.id.edt_l_name)
    TextInputEditText edt_l_name;
    @BindView(R.id.edt_email)
    TextInputEditText edt_email;
    @BindView(R.id.edt_password)
    TextInputEditText edt_password;
    @BindView(R.id.edt_dob)
    TextInputEditText edt_dob;
    @BindView(R.id.edt_uname)
    TextInputEditText edt_uname;
    @BindView(R.id.edt_phone)
    TextInputEditText edt_phone;
    @BindView(R.id.edt_ifsc)
    TextInputEditText edt_ifsc;
    @BindView(R.id.edt_ac_no)
    TextInputEditText edt_ac_no;
    @BindView(R.id.edt_pan)
    TextInputEditText edt_pan;
    @BindView(R.id.edt_aadhar)
    TextInputEditText edt_aadhar;
    @BindView(R.id.cv_registration)
    CardView cv_registration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        initview();
        listners();
    }

    @Override
    public void initview() {

    }

    @OnClick(R.id.cv_registration)
    public void register(){
        if (isVerified()){
            callRegistration();
        }else{
            Toasty.error(RegistrationActivity.this,"Something went wrong");
        }
    }

    private boolean isVerified() {
        boolean isVerified = true;
        if (Objects.requireNonNull(edt_aadhar.getText()).toString().length() == 0){
            txt_aadhar.setError("Field can't be blank");
            isVerified = false;
        }
        if (Objects.requireNonNull(edt_ac_no.getText()).toString().length() == 0){
            txt_ac_no.setError("Field can't be blank");
            isVerified = false;
        }
        if (Objects.requireNonNull(edt_ifsc.getText()).toString().length() == 0){
            txt_ifsc.setError("Field can't be blank");
            isVerified = false;
        }
        if (Objects.requireNonNull(edt_pan.getText()).toString().length() == 0){
            txt_pan.setError("Field can't be blank");
            isVerified = false;
        }
        if (Objects.requireNonNull(edt_password.getText()).toString().length() == 0){
            txt_password.setError("Field can't be blank");
            isVerified = false;
        }
        if (Objects.requireNonNull(edt_dob.getText()).toString().length() == 0){
            txt_dob.setError("Field can't be blank");
            isVerified = false;
        }

        isVerified = AppHelper.Validate(edt_email.getText().toString(),"Please enter a valid email",txt_email);

        if (Objects.requireNonNull(edt_f_name.getText()).toString().length() == 0){
            txt_fname.setError("Field can't be blank");
            isVerified = false;
        }
        if (Objects.requireNonNull(edt_l_name.getText()).toString().length() == 0){
            txt_lname.setError("Field can't be blank");
            isVerified = false;
        }
        if (Objects.requireNonNull(edt_uname.getText()).toString().length() == 0){
            txt_u_name.setError("Field can't be blank");
            isVerified = false;
        }
        if (Objects.requireNonNull(edt_phone.getText()).toString().length() == 0){
            txt_phone.setError("Field can't be blank");
            isVerified = false;
        }

        if (Objects.requireNonNull(edt_phone.getText()).toString().length() < 10){
            txt_phone.setError("Enter a valid mobile no.");
            isVerified = false;
        }

        return isVerified;
    }



    private void callRegistration() {
        getDialog().show();
        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("first_name", edt_f_name.getText().toString().trim());
        jsonParams.put("last_name", edt_l_name.getText().toString().trim());
        jsonParams.put("password", edt_password.getText().toString().trim());
        jsonParams.put("email", edt_email.getText().toString().trim());
        jsonParams.put("phone", edt_phone.getText().toString().trim());
        jsonParams.put("dob", edt_dob.getText().toString().trim());
        jsonParams.put("pan_card", edt_pan.getText().toString().trim());
        jsonParams.put("account_no", edt_ac_no.getText().toString().trim());
        jsonParams.put("ifsc_code", edt_ifsc.getText().toString().trim());
        jsonParams.put("aadhar_no", edt_ac_no.getText().toString().trim());
        jsonParams.put("status_detail", "active");
        jsonParams.put("is_active", ""+true);
        Log.e("TAG", ">>  " + new JSONObject(jsonParams));
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());
        Call<LoginModel> call = RetrofitService.RetrofitService1().registration(body);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Log.e("TAG", ">>  " + new Gson().toJson(response.body()));
                Log.e("TAG", ">>  code " + response.code());
                getDialog().dismiss();
                if (response.body() != null && !response.body().getError()) {
                    Toasty.success(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT, true).show();
                    PreferenceManger.setLastLoginn(new Date());
                    PreferenceManger.setUserDetailS(response.body().getData());
                    startActivity(new Intent(RegistrationActivity.this, MainLoginUserActivity.class));
                    finish();
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                } else {
                    Toasty.error(RegistrationActivity.this, "Something went wrong, please try again.", Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.e("TAG", ">>  " + t.getMessage());
                getDialog().dismiss();
                Toasty.error(RegistrationActivity.this, "Something went wrong, please try again.", Toast.LENGTH_SHORT, true).show();
            }
        });

    }

    @Override
    public void listners() {

    }

    public void logIn(View view) {
    }

    @OnClick(R.id.edt_dob)
    public void openCalender() {
        Calendar mCalendar = new GregorianCalendar();
        mCalendar.setTime(new Date());
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(RegistrationActivity.this, R.style.my_dialog_theme, (view, year1, monthOfYear, dayOfMonth) -> {
            //do something with the date
            String finalDay = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
            String finalMonth = (monthOfYear + 1) < 10 ? "0" + (monthOfYear + 1) : String.valueOf((monthOfYear + 1));
            edt_dob.setText(finalDay+"/"+ finalMonth + "/" +year1 );
        }, year, month, day);

        Calendar maxCal = Calendar.getInstance();
        maxCal.add(Calendar.YEAR, -12);
        Calendar minCal = Calendar.getInstance();
        minCal.add(Calendar.YEAR, -99);
        dialog.getDatePicker().setMaxDate(maxCal.getTimeInMillis());
        dialog.getDatePicker().setMinDate(minCal.getTimeInMillis());
        dialog.show();
    }
}