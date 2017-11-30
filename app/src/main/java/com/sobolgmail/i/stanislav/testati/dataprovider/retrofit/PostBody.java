package com.sobolgmail.i.stanislav.testati.dataprovider.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VZ on 30.11.2017.
 */

class PostBody {
    @SerializedName("filter")
    Object filter = new Object();
    @SerializedName("page")
    int page = 1;
}
