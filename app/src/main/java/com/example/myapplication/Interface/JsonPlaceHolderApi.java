package com.example.myapplication.Interface;

import com.example.myapplication.Model.Albums;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("albums")
    Call<List<Albums>> getAlbums();
}
