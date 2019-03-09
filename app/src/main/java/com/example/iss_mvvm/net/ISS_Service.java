package com.example.iss_mvvm.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.iss_mvvm.Constants.ISS_ENDPOINT;

public interface ISS_Service {
    @GET(ISS_ENDPOINT)
    Call<ISSResponse> getISSPasses(@Query("lat")String latitude,@Query("lon")String longitude);

}
