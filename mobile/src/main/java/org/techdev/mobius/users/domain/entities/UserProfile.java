package org.techdev.mobius.users.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.threeten.bp.LocalDate;

/**
 * Fuente:
 *  . https://stackoverflow.com/questions/40008840/how-to-use-retrofit-2-to-send-file-and-other-params-together
 */
public class UserProfile implements Parcelable {

    @SerializedName("dni")
    @Expose
    private int dni;

    @SerializedName("dni_id")
    @Expose
    private long dniId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;

    @SerializedName("facebook_user_name")
    @Expose
    private String facebookUserName;

    @SerializedName("origin_locality")
    @Expose
    private String originLocality;

    @SerializedName("phone")
    @Expose
    private long phone;

    @SerializedName("birthdate")
    @Expose
    private LocalDate birthdate;

    @SerializedName("nationality")
    @Expose
    private String nationality;

    @SerializedName("gender")
    @Expose
    private char gender;

//    TODO: Checkear si al usar el valor de la cabecera multipart/form-data cómo serializar los atributos
//      No lo serializo pues lo mando como Multipart
    private String avatarPath;

    public UserProfile() {}

    public UserProfile(int dni,
                       long dniId,
                       String name,
                       String surname,
                       String facebookUserName,
                       String originLocality,
                       long phone,
                       LocalDate birthdate,
                       String nationality,
                       char gender,
                       String avatarPath) {
        this.dni = dni;
        this.dniId = dniId;
        this.name = name;
        this.surname = surname;
        this.facebookUserName = facebookUserName;
        this.originLocality = originLocality;
        this.phone = phone;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.gender = gender;
        this.avatarPath = avatarPath;
    }

    protected UserProfile(Parcel in) {
        dni = in.readInt();
        dniId = in.readLong();
        name = in.readString();
        surname = in.readString();
        facebookUserName = in.readString();
        originLocality = in.readString();
        phone = in.readLong();
        nationality = in.readString();
        gender = (char) in.readInt();
        avatarPath = in.readString();
    }

    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public long getDniId() {
        return dniId;
    }

    public void setDniId(long dniId) {
        this.dniId = dniId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getOriginLocality() {
        return originLocality;
    }

    public void setOriginLocality(String originLocality) {
        this.originLocality = originLocality;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getFacebookUserName() {
        return facebookUserName;
    }

    public void setFacebookUserName(String facebookUserName) {
        this.facebookUserName = facebookUserName;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(dni);
        parcel.writeLong(dniId);
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(facebookUserName);
        parcel.writeString(originLocality);
        parcel.writeLong(phone);
        parcel.writeString(nationality);
        parcel.writeInt((int) gender);
        parcel.writeString(avatarPath);
    }
}