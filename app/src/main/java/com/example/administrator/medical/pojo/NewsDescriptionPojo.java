package com.example.administrator.medical.pojo;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Administrator on 2015/7/17.
 */
public class NewsDescriptionPojo implements Parcelable {

    /**
     * 资讯ID
     */
    private String id;
    /**
     * 资讯标题
     */
    private String title;
    /**
     * 资讯标签Tag
     */
    private String tag;
    /**
     * 资讯详细内容
     */
    private String message;
    /**
     * 图片
     */
    private String img;
    /**
     * 浏览次数
     */
    private int count;
    /**
     * 回复数
     */
    private int rcount;
    /**
     * 收藏数
     */
    private int fcount;
    /**
     * 作者
     */
    private String author;
    /**
     * 是否是焦点新闻，0：一般新闻，1：焦点新闻
     */
    private int focal;
    /**
     * 发布时间
     */
    private Date time;

    public NewsDescriptionPojo() {

    }


    protected NewsDescriptionPojo(Parcel in) {
        id = in.readString();
        title = in.readString();
        tag = in.readString();
        message = in.readString();
        img = in.readString();
        count = in.readInt();
        rcount = in.readInt();
        fcount = in.readInt();
        author = in.readString();
        focal = in.readInt();
    }

    public static final Creator<NewsDescriptionPojo> CREATOR = new Creator<NewsDescriptionPojo>() {
        @Override
        public NewsDescriptionPojo createFromParcel(Parcel in) {
            return new NewsDescriptionPojo(in);
        }

        @Override
        public NewsDescriptionPojo[] newArray(int size) {
            return new NewsDescriptionPojo[size];
        }
    };

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getFocal() {
        return focal;
    }

    public void setFocal(int focal) {
        this.focal = focal;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(tag);
        dest.writeString(message);
        dest.writeString(img);
        dest.writeInt(count);
        dest.writeInt(rcount);
        dest.writeInt(fcount);
        dest.writeString(author);
        dest.writeInt(focal);
    }
}
