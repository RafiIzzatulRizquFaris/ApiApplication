package com.toughput.apiapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Puguh implements Parcelable {
    String usernme, email, password;

    public String getUsernme() {
        return usernme;
    }

    public void setUsernme(String usernme) {
        this.usernme = usernme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.usernme);
        dest.writeString(this.email);
        dest.writeString(this.password);
    }

    public Puguh() {
    }

    protected Puguh(Parcel in) {
        this.usernme = in.readString();
        this.email = in.readString();
        this.password = in.readString();
    }

    public static final Parcelable.Creator<Puguh> CREATOR = new Parcelable.Creator<Puguh>() {
        @Override
        public Puguh createFromParcel(Parcel source) {
            return new Puguh(source);
        }

        @Override
        public Puguh[] newArray(int size) {
            return new Puguh[size];
        }
    };
}
