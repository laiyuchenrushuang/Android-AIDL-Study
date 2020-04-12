package com.yey.ok;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private int mBookId;
    private String mName;

    public Book(int mBookId, String mName) {
        this.mBookId = mBookId;
        this.mName = mName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mBookId);
        dest.writeString(this.mName);
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.mBookId = in.readInt();
        this.mName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public int getmBookId() {
        return mBookId;
    }

    public void setmBookId(int mBookId) {
        this.mBookId = mBookId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
