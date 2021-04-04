package com.trainersstocks.CustomersApp.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.trainersstocks.CustomersApp.R;
import com.trainersstocks.CustomersApp.Utils.AppHelper;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.savvyapps.togglebuttonlayout.ToggleButtonLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {


    @BindView(R.id.civ_dp)
    CircleImageView civ_dp;

    @BindView(R.id.tv_change_picture)
    AppCompatTextView tv_change_picture;

    @BindView(R.id.tv_name)
    AppCompatTextView tv_name;

    @BindView(R.id.et_email)
    AppCompatEditText et_email;

    @BindView(R.id.et_mobile)
    AppCompatEditText et_mobile;

    @BindView(R.id.et_dob)
    AppCompatEditText et_dob;


    @BindView(R.id.arc_progress)
    ArcProgress arc_progress;

    @BindView(R.id.bar)
    ProgressBar bar;

    @BindView(R.id.toggle_text)
    ToggleButtonLayout toggle_text;


    private int requestCodefile = 239;
    private String file_path = null;
    private String gender = "male";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        listner();
        return view;
    }

    private void listner() {
        toggle_text.setToggled(R.id.male, true);
        toggle_text.setOnToggledListener((t, b) -> {
            switch (t.getId()) {
                case R.id.male:
                    gender = "male";
                    break;
                case R.id.female:
                    gender = "female";
                    break;
                case R.id.others:
                    gender = "others";
                    break;
            }
            return null;
        });

    }


    @OnClick(R.id.tv_change_picture)
    public void ChangeProfilePicture() {
        ImagePicker.Companion.with(this)
                .galleryOnly()
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start(123);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 123) {
            String path = ImagePicker.Companion.getFilePath(data);
            file_path = path;
            AppHelper.setImage(getContext(), civ_dp, path);
            checkPermisson(path);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(getContext(), "Failed to open", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkPermisson(String path) {
        if (path == null) {
            return;
        }
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    requestCodefile);
        } else {
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCodefile == requestCode && grantResults != null) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkPermisson(file_path);
            }
        }
    }

    @OnClick(R.id.tv_bank_details)
    public void OnBankDetail() {
    }

    @OnClick(R.id.tv_paytm_details)
    public void OnPaytmDetail() {

    }

    @OnClick(R.id.tv_upi_details)
    public void OnUpiDetail() {
    }


    @OnClick(R.id.edit)
    public void EditProfile() {

    }

}
