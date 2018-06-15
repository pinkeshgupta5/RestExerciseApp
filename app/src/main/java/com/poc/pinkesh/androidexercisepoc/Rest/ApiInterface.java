package com.poc.pinkesh.androidexercisepoc.Rest;

import com.poc.pinkesh.androidexercisepoc.Usermodels.UserResponses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("2iodh4vg0eortkl/facts.json")
    Call<UserResponses> getUsersContent();


}
