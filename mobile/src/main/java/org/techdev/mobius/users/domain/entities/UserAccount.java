package org.techdev.mobius.users.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * PRO: Modela al objeto de dominio para la cuenta de usuario
 * OBS: Este objeto es el que se prepara para la petición
 */
public class UserAccount implements Parcelable {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("hash_password")
    @Expose
    private String password;
    private String passwordSecurity;

    public UserAccount() {}

    public UserAccount(String email, String hashPassword, String passwordSecurity) {
        this.email = email;
        this.password = hashPassword;
        this.passwordSecurity = passwordSecurity;
    }

    public UserAccount(String email, String hashPassword) {
        this.email = email;
        this.password = hashPassword;
    }

    protected UserAccount(Parcel in) {
        email = in.readString();
        password = in.readString();
        passwordSecurity = in.readString();
    }

    public static final Creator<UserAccount> CREATOR = new Creator<UserAccount>() {
        @Override
        public UserAccount createFromParcel(Parcel in) {
            return new UserAccount(in);
        }

        @Override
        public UserAccount[] newArray(int size) {
            return new UserAccount[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordSecurity() {
        return passwordSecurity;
    }

    public void setPasswordSecurity(String passwordSecurity) {
        this.passwordSecurity = passwordSecurity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(passwordSecurity);
    }
}