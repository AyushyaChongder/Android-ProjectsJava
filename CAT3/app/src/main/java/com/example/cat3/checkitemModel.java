package com.example.cat3;

import android.os.Parcel;
import android.os.Parcelable;

public class checkitemModel implements Parcelable {
    private String name;

    public checkitemModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Parcelable implementation
    protected checkitemModel(Parcel in) {
        name = in.readString();
    }

    public static final Creator<checkitemModel> CREATOR = new Creator<checkitemModel>() {
        @Override
        public checkitemModel createFromParcel(Parcel in) {
            return new checkitemModel(in);
        }

        @Override
        public checkitemModel[] newArray(int size) {
            return new checkitemModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
