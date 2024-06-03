package com.example.wolfys;

import android.os.Parcel;
import android.os.Parcelable;

public class CartItem implements Parcelable {
    private int id;
    private String itemName;
    private int imageResourceId;
    private boolean isSelected;

    public CartItem(int id, String itemName, int imageResourceId) {
        this.id = id;
        this.itemName = itemName;
        this.imageResourceId = imageResourceId;
    }

    protected CartItem(Parcel in) {
        id = in.readInt();
        itemName = in.readString();
        imageResourceId = in.readInt();
        isSelected = in.readByte() != 0; // Read the byte and convert to boolean
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getImageResId() {
        return imageResourceId;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(itemName);
        dest.writeInt(imageResourceId);
        dest.writeByte((byte) (isSelected ? 1 : 0)); // Write the boolean as a byte
    }


}
