package com.trainersstocks.CustomersApp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.trainersstocks.CustomersApp.Models.UserDetailsModel;
import com.google.gson.Gson;

import static com.trainersstocks.CustomersApp.Utils.PrefKeys.LAST_LOGIN;
import static com.trainersstocks.CustomersApp.Utils.PrefKeys.REFERRAL_ID;
import static com.trainersstocks.CustomersApp.Utils.PrefKeys.SHOWAPPINTRO;
import static com.trainersstocks.CustomersApp.Utils.PrefKeys.USERDEATIL;

import java.text.DateFormat;
import java.util.Date;

public final class PreferenceManger {
    public static PreferenceManger preferenceManger;
    public static SharedPreferences sharedPreferences;

    public static PreferenceManger getPreferenceManger() {
        return preferenceManger;
    }


    public static void Logout(Context context) {
        sharedPreferences.edit().clear().apply();
    }

    public static void initPreference(Context context) {
        if (preferenceManger == null) {
            sharedPreferences = context.getSharedPreferences(PrefKeys.PREFERENCENAME, Context.MODE_PRIVATE);
            preferenceManger = new PreferenceManger();
        }
    }
    public static void setUserDetailS(UserDetailsModel model) {
        sharedPreferences.edit().putString(USERDEATIL, new Gson().toJson(model)).apply();
    }

    public static UserDetailsModel getUserDetailS() {
        UserDetailsModel model = new Gson().fromJson(sharedPreferences.getString(USERDEATIL, null), UserDetailsModel.class);
        return model;
    }

    public static void showappintro(boolean b) {
        sharedPreferences.edit().putBoolean(SHOWAPPINTRO, b).apply();
    }


    public static boolean hasAPPINTRO() {
        return sharedPreferences.getBoolean(SHOWAPPINTRO, false);
    }

    public static void setReferralID(String referralID) {
        sharedPreferences.edit().putString(REFERRAL_ID, referralID).apply();
    }

    public static String getReferralID() {
        return sharedPreferences.getString(REFERRAL_ID, "");
    }

    public static void setLastLoginn(Date date) {
        sharedPreferences.edit().putString(LAST_LOGIN,""+ DateFormat.getDateTimeInstance().format(date)).apply();
    }

    public static String getLastLoginn() {
        return sharedPreferences.getString(LAST_LOGIN, null);
    }
}
