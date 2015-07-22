package com.example.administrator.medical.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Administrator on 2015/7/17.
 */
public class NewsListPolo implements Parcelable {

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
     * 图片
     */
    private String img;
    /**
     * 浏览次数
     */
    private String count;
    /**
     * 作者
     */
    private String author;
    /**
     * 是否是焦点新闻，0：一般新闻，1：焦点新闻
     */
    private String focal;
    /**
     * 发布时间
     */
    private Date time;


    public NewsListPolo() {

    }

    protected NewsListPolo(Parcel in) {
        id = in.readString();
        title = in.readString();
        tag = in.readString();
        img = in.readString();
        count = in.readString();
        author = in.readString();
        focal = in.readString();
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFocal() {
        return focal;
    }

    public void setFocal(String focal) {
        this.focal = focal;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public static final Creator<NewsListPolo> CREATOR = new Creator<NewsListPolo>() {
        @Override
        public NewsListPolo createFromParcel(Parcel in) {
            return new NewsListPolo(in);
        }

        @Override
        public NewsListPolo[] newArray(int size) {
            return new NewsListPolo[size];
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
        dest.writeString(tag);
        dest.writeString(img);
        dest.writeString(count);
        dest.writeString(author);
        dest.writeString(focal);
    }
}
