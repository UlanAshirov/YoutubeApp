
package com.joma.youtubeapp.data.model.playlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnails {

    @SerializedName("medium")
    @Expose
    private Medium medium;

    @SerializedName("high")

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }
}
