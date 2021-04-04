package com.trainersstocks.CustomersApp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel implements Parcelable {

    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("dob")
    @Expose
    public String dob;
    @SerializedName("pan_card")
    @Expose
    public String panCard;
    @SerializedName("account_no")
    @Expose
    public String accountNo;
    @SerializedName("aadhar_no")
    @Expose
    public String aadharNo;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("view_password")
    @Expose
    public String viewPassword;
    @SerializedName("role")
    @Expose
    public String role;
    @SerializedName("role_id")
    @Expose
    public String roleId;
    @SerializedName("auth_token")
    @Expose
    public Object authToken;
    @SerializedName("device_id")
    @Expose
    public Object deviceId;
    @SerializedName("device_type")
    @Expose
    public Object deviceType;
    @SerializedName("status_detail")
    @Expose
    public String statusDetail;
    @SerializedName("is_active")
    @Expose
    public String isActive;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    protected UserModel(Parcel in) {
        userId = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        phone = in.readString();
        dob = in.readString();
        panCard = in.readString();
        accountNo = in.readString();
        aadharNo = in.readString();
        userName = in.readString();
        email = in.readString();
        password = in.readString();
        viewPassword = in.readString();
        role = in.readString();
        roleId = in.readString();
        statusDetail = in.readString();
        isActive = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    public String getPanCard() {
        return panCard;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getViewPassword() {
        return viewPassword;
    }

    public String getRole() {
        return role;
    }

    public String getRoleId() {
        return roleId;
    }

    public Object getAuthToken() {
        return authToken;
    }

    public Object getDeviceId() {
        return deviceId;
    }

    public Object getDeviceType() {
        return deviceType;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userId);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(phone);
        parcel.writeString(dob);
        parcel.writeString(panCard);
        parcel.writeString(accountNo);
        parcel.writeString(aadharNo);
        parcel.writeString(userName);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(viewPassword);
        parcel.writeString(role);
        parcel.writeString(roleId);
        parcel.writeString(statusDetail);
        parcel.writeString(isActive);
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
    }
}
