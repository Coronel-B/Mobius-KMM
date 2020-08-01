package org.techdev.mobius.addedituser.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 8/1/2019
 *
 * PRO: Modela la cuenta de usuario para UI
 */
public class UserAccountUi implements Parcelable {

    private String email;
    private String passwordSecurity;

    public UserAccountUi() {}

    private UserAccountUi(Parcel in) {
        email = in.readString();
        passwordSecurity = in.readString();
    }

    public UserAccountUi(String email, String passwordSecurity) {
        this.email = email;
        this.passwordSecurity = passwordSecurity;
    }

    public static final Creator<UserAccountUi> CREATOR = new Creator<UserAccountUi>() {
        @Override
        public UserAccountUi createFromParcel(Parcel in) {
            return new UserAccountUi(in);
        }

        @Override
        public UserAccountUi[] newArray(int size) {
            return new UserAccountUi[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordSecurity(String passwordSecurity) {
        this.passwordSecurity = passwordSecurity;
    }

    public String getPasswordSecurity() {
        return passwordSecurity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(passwordSecurity);
    }
}