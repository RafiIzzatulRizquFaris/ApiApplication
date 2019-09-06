package com.toughput.apiapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Local implements Parcelable {
    String namalengkap, email, tempatlahir;

    public String getNamalengkap() {
        return namalengkap;
    }

    public void setNamalengkap(String namalengkap) {
        this.namalengkap = namalengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTempatlahir() {
        return tempatlahir;
    }

    public void setTempatlahir(String tempatlahir) {
        this.tempatlahir = tempatlahir;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.namalengkap);
        dest.writeString(this.email);
        dest.writeString(this.tempatlahir);
    }

    public Local() {
    }

    protected Local(Parcel in) {
        this.namalengkap = in.readString();
        this.email = in.readString();
        this.tempatlahir = in.readString();
    }

    public static final Parcelable.Creator<Local> CREATOR = new Parcelable.Creator<Local>() {
        @Override
        public Local createFromParcel(Parcel source) {
            return new Local(source);
        }

        @Override
        public Local[] newArray(int size) {
            return new Local[size];
        }
    };
}
