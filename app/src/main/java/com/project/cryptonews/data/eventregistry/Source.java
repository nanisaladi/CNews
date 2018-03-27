package com.project.cryptonews.data.eventregistry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Venkat on 24/03/18.
 */

public class Source {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("title")
    @Expose
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
