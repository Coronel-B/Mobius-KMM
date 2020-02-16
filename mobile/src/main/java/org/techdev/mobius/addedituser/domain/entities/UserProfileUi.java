package org.techdev.mobius.addedituser.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 8/1/2019
 *
 * PRO: Modela la cuenta de usuario para UI
 */
public class UserProfileUi implements Parcelable {

    private String avatarPath;
    private int dni;
    private String name;
    private String surname;

    private UserProfileUi() {}

    public UserProfileUi(String avatarPath, int dni, String name, String surname) {
        this.avatarPath = avatarPath;
        this.dni = dni;
        this.name = name;
        this.surname = surname;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public int getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    private UserProfileUi(Parcel in) {
        avatarPath = in.readString();
        dni = in.readInt();
        name = in.readString();
        surname = in.readString();
    }

    public static final Creator<UserProfileUi> CREATOR = new Creator<UserProfileUi>() {
        @Override
        public UserProfileUi createFromParcel(Parcel in) {
            return new UserProfileUi(in);
        }

        @Override
        public UserProfileUi[] newArray(int size) {
            return new UserProfileUi[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(avatarPath);
        parcel.writeInt(dni);
        parcel.writeString(name);
        parcel.writeString(surname);
    }
}