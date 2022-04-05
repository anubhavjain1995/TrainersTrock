package com.trainersstocks.CustomersApp.fragments;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;
import com.payu.base.models.ErrorResponse;
import com.payu.base.models.PayUPaymentParams;
import com.payu.checkoutpro.PayUCheckoutPro;
import com.payu.checkoutpro.models.PayUCheckoutProConfig;
import com.payu.checkoutpro.utils.PayUCheckoutProConstants;
import com.payu.ui.model.listeners.PayUCheckoutProListener;
import com.payu.ui.model.listeners.PayUHashGenerationListener;

import com.trainersstocks.CustomersApp.Models.Payment;
import com.trainersstocks.CustomersApp.Models.PaymentModel;
import com.trainersstocks.CustomersApp.Models.UserDetailsModel;
import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.PreferenceManger;
import com.trainersstocks.CustomersApp.retrofit_provider.RetrofitService;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Key;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EnterAmountFragment extends DialogFragment {


    @BindView(R.id.edt_amount)
    EditText edt_amount;
    @BindView(R.id.btn_proceed)
    Button btn_proceed;
    public ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);

    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_enter_amount, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btn_proceed)
    public void startPayment(){
        String amt = edt_amount.getText().toString().trim();
        if (amt != null) {
            btn_proceed.setEnabled(false);
            generateHash(amt);
        }else {
            Toasty.error(getContext(),"Please enter an amount");
        }
    }


    public void generateHash(String amt){
        showDialog();
        Map<String, String> jsonParams = new ArrayMap<>();
        jsonParams.put("user_id", PreferenceManger.getUserDetailS().getUserDetails().getId());
        jsonParams.put("payble_amount",amt);

        Log.e("TAG", ">>  " + new JSONObject(jsonParams));
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                (new JSONObject(jsonParams)).toString());
        Call<PaymentModel> call = RetrofitService.RetrofitService1().getHash(body);
        call.enqueue(new Callback<PaymentModel>() {
            @Override
            public void onResponse(Call<PaymentModel> call, Response<PaymentModel> response) {
                dismissDialog();
                Log.e("TAG", ">>  " + new Gson().toJson(response.body()));
                Log.e("TAG", ">>  code " + response.code());
                if (response.body() != null && !response.body().getError()) {
                    Log.e("TAG", ">>  " + new Gson().toJson(response.body()));
                    Payment model = response.body().getData();
                    initPayUMoney(model.getHash(),model.getMkey(),"",amt,
                            model.getTid(),model.getPhoneno(),model.getProductinfo(),
                            model.getName(),model.getMailid(),model.getSurl(),model.getFurl()
                    );
                } else {
                    Log.e("TAG", ">>  " + new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<PaymentModel> call, Throwable t) {
                btn_proceed.setEnabled(true);
                dismissDialog();
                Log.e("TAG", ">>  " + t.getMessage());
                Toasty.error(getContext(), "Failed to login.", Toast.LENGTH_SHORT, true).show();
            }
        });
    }

    private void initPayUMoney(String hash_kay,String mer_key,String mer_id,String amount,
                               String txnId,String phone,String productName,String firstName,String email,
                               String surl,String furl) {
        PayUPaymentParams.Builder builder = new PayUPaymentParams.Builder();
        builder.setAmount(amount)                          // Payment amount
                .setTransactionId(txnId)                                             // Transaction ID
                .setPhone(phone)                                           // User Phone number
                .setProductInfo(productName)                   // Product Name or description
                .setFirstName(firstName)                              // User First name
                .setEmail(email)                                            // User Email ID
                .setSurl("https://payuresponse.firebaseapp.com/success")
                .setFurl("https://payuresponse.firebaseapp.com/failure")
                .setUserCredential("gtKFFx" + ":"+email)//Failure URL (furl)
                .setIsProduction(false)                              // Integration environment - true (Debug)/ false(Production)
                .setKey("gtKFFx")  ;                      // Merchant key
        // .setMerchantId(mer_id);


        PayUPaymentParams payUPaymentParams = builder.build();
        /*PayUCheckoutProConfig payUCheckoutProConfig = new PayUCheckoutProConfig();
        payUCheckoutProConfig.setMerchantName("Trainers Stock");*/

        PayUCheckoutPro.open(
                (Activity) getContext(),
                payUPaymentParams,
                getCheckoutProConfig(),
                new PayUCheckoutProListener() {

                    @Override
                    public void onPaymentSuccess(Object response) {
                        //Cast response object to HashMap
                        HashMap<String,Object> result = (HashMap<String, Object>) response;
                        String payuResponse = (String)result.get(PayUCheckoutProConstants.CP_PAYU_RESPONSE);
                        String merchantResponse = (String) result.get(PayUCheckoutProConstants.CP_MERCHANT_RESPONSE);
                        Log.w("payments",""+merchantResponse);

                    }

                    @Override
                    public void onPaymentFailure(Object response) {
                        //Cast response object to HashMap
                        HashMap<String,Object> result = (HashMap<String, Object>) response;
                        String payuResponse = (String)result.get(PayUCheckoutProConstants.CP_PAYU_RESPONSE);
                        String merchantResponse = (String) result.get(PayUCheckoutProConstants.CP_MERCHANT_RESPONSE);
                        Log.w("paymentf",""+merchantResponse);
                    }

                    @Override
                    public void onPaymentCancel(boolean isTxnInitiated) {
                    }

                    @Override
                    public void onError(ErrorResponse errorResponse) {
                        String errorMessage = errorResponse.getErrorMessage();
                        Log.w("paymente",""+errorMessage);
                    }

                    @Override
                    public void setWebViewProperties(@Nullable WebView webView, @Nullable Object o) {
                        //For setting webview properties, if any. Check Customized Integration section for more details on this
                    }

                    @Override
                    public void generateHash(HashMap<String, String> valueMap, PayUHashGenerationListener hashGenerationListener) {
                        String hashName = valueMap.get(PayUCheckoutProConstants.CP_HASH_NAME);
                        String hashData = valueMap.get(PayUCheckoutProConstants.CP_HASH_STRING);
                        if (!TextUtils.isEmpty(hashName) && !TextUtils.isEmpty(hashData)) {
                            //Do not generate hash from local, it needs to be calculated from server side only. Here, hashString contains hash created from your server side.
                            //String hash = h;
                            String hash = null;
                            if (hashName.equalsIgnoreCase(PayUCheckoutProConstants.CP_LOOKUP_API_HASH)){
                                //Calculate HmacSHA1 HASH for calculating Lookup API Hash
                                ///Do not generate hash from local, it needs to be calculated from server side only. Here, hashString contains hash created from your server side.

                                hash = calculateHmacSHA1Hash(hashData, "gtKFFx");//mkey
                            } else {

                                //Calculate SHA-512 Hash here
                                hash = calculateHash(hashData + "wia56q6O");//salt RVQuWVHk9BAwUOOaHM14nLyQPTKw9xec
                            }

                            HashMap<String, String> dataMap = new HashMap<>();
                            dataMap.put(hashName, hash);
                            hashGenerationListener.onHashGenerated(dataMap);
                        }
                    }
                }
        );
       /* PayUmoneySdkInitializer.PaymentParam.Builder builder = new
                PayUmoneySdkInitializer.PaymentParam.Builder();
        builder.setAmount(amount)                          // Payment amount
                .setTxnId(txnId)                                             // Transaction ID
                .setPhone(phone)                                           // User Phone number
                .setProductName(productName)                   // Product Name or description
                .setFirstName(firstName)                              // User First name
                .setEmail(email)                                            // User Email ID
                .setsUrl("https://www.payumoney.com/mobileapp/payumoney/success.php")                    // Success URL (surl)
                .setfUrl("https://www.payumoney.com/mobileapp/payumoney/failure.php")
                .setKey("BYKGkY")
                .setMerchantId("8357229");

        PayUmoneySdkInitializer.PaymentParam paymentParam = null;
        try {
            paymentParam = builder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
//set the hash
        paymentParam.setMerchantHash(hash_kay);

        PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam,
                (Activity) getContext(), R.style.AppTheme_default, true);*/
    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MainActivity", "request code " + requestCode + " resultcode " + resultCode);
        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {
            TransactionResponse transactionResponse = data.getParcelableExtra( PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE );

            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {

                if(transactionResponse.getTransactionStatus().equals( TransactionResponse.TransactionStatus.SUCCESSFUL )){
                    //Success Transaction
                    Log.d("Payment", "success");

                } else{
                    Log.d("Payment", "failure");
                    //Failure Transaction
                }

                // Response from Payumoney
                String payuResponse = transactionResponse.getPayuResponse();
                Log.d("Payment", payuResponse);

                // Response from SURl and FURL
                String merchantResponse = transactionResponse.getTransactionDetails();
                Log.d("Payment", merchantResponse);

            }  *//*else if (resultModel != null && resultModel.getError() != null) {
                Log.d("Payment", "Error response : " + resultModel.getError().getTransactionResponse());
            } *//*else {
                Log.d("Payment", "Both objects are null!");
            }
        }
    }*/

    private String calculateHmacSHA1Hash(String data, String key) {
        String HMAC_SHA1_ALGORITHM = "HmacSHA1";
        String result = null;

        try {
            Key signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            result = getHexString(rawHmac);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    private String calculateHash(String hashString) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(hashString.getBytes());
            byte[] mdbytes = messageDigest.digest();
            return getHexString(mdbytes);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    private String getHexString(byte[] array){
        StringBuilder hash = new StringBuilder();
        for (byte hashByte : array) {
            hash.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
        }
        return hash.toString();
    }

    private PayUCheckoutProConfig getCheckoutProConfig() {
        PayUCheckoutProConfig checkoutProConfig = new PayUCheckoutProConfig();
//        checkoutProConfig.setEnforcePaymentList(getEnforcePaymentList());
       // checkoutProConfig.setShowCbToolbar(!binding.switchHideCbToolBar.isChecked());
      //  checkoutProConfig.setAutoSelectOtp(binding.switchAutoSelectOtp.isChecked());
       // checkoutProConfig.setAutoApprove(binding.switchAutoApprove.isChecked());
       // checkoutProConfig.setSurePayCount(Integer.parseInt(binding.etSurePayCount.getText().toString()));
        checkoutProConfig.setShowExitConfirmationOnPaymentScreen(true);
        checkoutProConfig.setShowExitConfirmationOnCheckoutScreen(true);
        checkoutProConfig.setMerchantName("Trainers Stocks");
        checkoutProConfig.setMerchantLogo(R.drawable.logo);
        checkoutProConfig.setWaitingTime(30000);
        checkoutProConfig.setMerchantResponseTimeout(30000);
       // checkoutProConfig.setCustomNoteDetails(getCustomeNoteList());
       /* if (reviewOrderAdapter != null)
            checkoutProConfig.setCartDetails(reviewOrderAdapter.getOrderDetailsList());*/
        return checkoutProConfig;
    }
}