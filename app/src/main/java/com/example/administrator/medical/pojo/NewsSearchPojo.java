package com.example.administrator.medical.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/7/17.
 */
public class NewsSearchPojo implements Parcelable{

    private String id;
    private String title;

    public NewsSearchPojo(){

    }

    protected NewsSearchPojo(Parcel in) {
        id = in.readString();
        title = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static final Creator<NewsSearchPojo> CREATOR = new Creator<NewsSearchPojo>() {
        @Override
        public NewsSearchPojo createFromParcel(Parcel in) {
            return new NewsSearchPojo(in);
        }

        @Override
        public NewsSearchPojo[] newArray(int size) {
            return new NewsSearchPojo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
    }
}
