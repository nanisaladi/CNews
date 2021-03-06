package com.project.cryptonews.data.eventregistry;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Result class stores the content of each article in JSON response.
 */
@Entity(tableName = "articles")
public class Result {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("isDuplicate")
    @Expose
    private Boolean isDuplicate;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("dataType")
    @Expose
    private String dataType;
    @SerializedName("sim")
    @Expose
    private Double sim;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @Embedded
    @SerializedName("source")
    @Expose
    private Source source;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("wgt")
    @Expose
    private Integer wgt;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Boolean getIsDuplicate() {
        return isDuplicate;
    }

    public void setIsDuplicate(Boolean isDuplicate) {
        this.isDuplicate = isDuplicate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Double getSim() {
        return sim;
    }

    public void setSim(Double sim) {
        this.sim = sim;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getWgt() {
        return wgt;
    }

    public void setWgt(Integer wgt) {
        this.wgt = wgt;
    }
}
