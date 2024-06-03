package com.example.cat3;

import android.os.Parcel;
import android.os.Parcelable;

public class MathModel implements Parcelable {
    private String subjectName;
    private boolean isChecked;

    public MathModel(String subjectName, boolean isChecked) {
        this.subjectName = subjectName;
        this.isChecked = isChecked;
    }

    protected MathModel(Parcel in) {
        subjectName = in.readString();
        isChecked = in.readByte() != 0;
    }

    public static final Creator<MathModel> CREATOR = new Creator<MathModel>() {
        @Override
        public MathModel createFromParcel(Parcel in) {
            return new MathModel(in);
        }

        @Override
        public MathModel[] newArray(int size) {
            return new MathModel[size];
        }
    };

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(subjectName);
        dest.writeByte((byte) (isChecked ? 1 : 0));
    }
}
