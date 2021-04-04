package com.trainersstocks.CustomersApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetailsMModel {

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
}
