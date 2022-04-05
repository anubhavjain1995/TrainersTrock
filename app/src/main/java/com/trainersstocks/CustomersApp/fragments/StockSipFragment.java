package com.trainersstocks.CustomersApp.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.trainersstocks.CustomersApp.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockSipFragment extends DialogFragment {

@BindView(R.id.txt)
TextView txt;
@BindView(R.id.btn_ok)
CardView btn_ok;
@BindView(R.id.cv_dialog)
CardView cv_dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_stock_sip, container, false);
        ButterKnife.bind(this,view);
        initView();
        showDialog();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView() {

        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            }

        };
        /*new DatePickerDialog(getContext(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();*/



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showDialog(){
        String text = "Stock SIP is a tool that helps you to create wealth, by investing small amounts periodically instead of lump sums in a disciplined manner. The frequency of Stock SIP investment is daily, weekly or monthly.\n\n";
        String text2 = "The SIP orders will be placed between 11.00 am to 1.00 pm on the SIP day. View SIP ";
        String text_3 = "Terms and Conditions";
        String text_4 = "for more details.";

        SpannableString spannableString = new SpannableString(text+" "+text2+text_3+" "+text_4);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(getContext().getResources().getColor(R.color.black));
                ds.setUnderlineText(false);
            }
        };
        String abc= text+text2;
        spannableString.setSpan(clickableSpan1, abc.length()+1, (abc+text_3).length()+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt.setText(spannableString);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cv_dialog.setVisibility(View.GONE);
            }
        });
    }

}