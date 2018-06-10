package com.project.cryptonews.data.eventregistry;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Venkat on 24/03/18.
 */

public class Source {

        @ColumnInfo(name="source_uri")
        @SerializedName("uri")
        @Expose
        private String uri;
        @ColumnInfo(name="source_dataType")
        @SerializedName("dataType")
        @Expose
        private String dataType;
        @ColumnInfo(name="source_title")
        @SerializedName("title")
        @Expose
        private String title;

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
}
