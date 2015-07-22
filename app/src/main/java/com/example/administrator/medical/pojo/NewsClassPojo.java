package com.example.administrator.medical.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2015/7/17.
 */
@DatabaseTable(tableName = "tb_class")
public class NewsClassPojo implements Parcelable {

    @DatabaseField(generatedId = true)
    private int _id;
    /**
     * 资讯分类ID
     */
    @DatabaseField
    private String id;
    /**
     * 资讯分类名称
     */
    @DatabaseField
    private String name;

    public NewsClassPojo() {

    }

    protected NewsClassPojo(Parcel in) {
        id = in.readString();
        _id = in.readInt();
        name = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int get_Id() {
        return _id;
    }

    public void set_Id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(_id);
        dest.writeString(name);
    }

    public static final Creator<NewsClassPojo> CREATOR = new Creator<NewsClassPojo>() {
        @Override
        public NewsClassPojo createFromParcel(Parcel in) {
            return new NewsClassPojo(in);
        }

        @Override
        public NewsClassPojo[] newArray(int size) {
            return new NewsClassPojo[size];
        }
    };
}
