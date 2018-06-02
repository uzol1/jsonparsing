package com.ujjwal.jsonparsing;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroFitApiInterface {
    @GET("api.php")
    Call<List<MenuModel>>getMenu();
}
