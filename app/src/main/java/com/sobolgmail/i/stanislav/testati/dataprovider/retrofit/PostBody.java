package com.sobolgmail.i.stanislav.testati.dataprovider.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class PostBody {
    @SerializedName("filter")
    Object filter = new Object();
    @SerializedName("page")
    int page = 1;
}
