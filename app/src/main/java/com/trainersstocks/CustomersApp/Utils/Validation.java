package com.trainersstocks.CustomersApp.Utils;

import androidx.appcompat.widget.AppCompatEditText;

public class Validation {

    public static boolean TextValidation(String text, String errormsg, AppCompatEditText editText) {
        if (text.isEmpty()) {
            editText.setError(errormsg);
            return false;
        } else {
            return true;
        }
    }

/*
    public static boolean TextValidation(String text, String errormsg, AppCompatEditText editText) {
        if (text.isEmpty()) {
            editText.setError(errormsg);
            return false;
        } else {
            return true;
        }
    }
*/

}
