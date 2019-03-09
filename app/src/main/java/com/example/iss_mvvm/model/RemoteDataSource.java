package com.example.iss_mvvm.model;

import com.example.iss_mvvm.net.ISSResponse;
import com.example.iss_mvvm.net.ISS_Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.iss_mvvm.Constants.BASE_URL;

public class RemoteDataSource extends Observable implements DataSource {

   private final ISS_Service iss_service;
   public RemoteDataSource(){

       OkHttpClient okHttpClient = new OkHttpClient.Builder()
               .connectTimeout(20, TimeUnit.SECONDS)
               .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
               .build();

       Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
               .baseUrl(BASE_URL)
               .client(okHttpClient)
               .addConverterFactory(GsonConverterFactory.create());

       Retrofit retrofit = retrofitBuilder.build();

       iss_service = retrofit.create(ISS_Service.class);
   }
    @Override
    public void getLocation(String latitude, String longitude) {
        final List<com.example.iss_mvvm.net.Response> responses = new ArrayList<>();

        iss_service.getISSPasses(latitude,longitude).enqueue(new Callback<ISSResponse>() {
            @Override
            public void onResponse(Call<ISSResponse> call, Response<ISSResponse> response) {
                if (response.isSuccessful()&& response.body().getResponse() != null){
                    responses.clear();
                    responses.addAll(response.body().getResponse());
                    setChanged();
                    notifyObservers(responses);
                }
            }

            @Override
            public void onFailure(Call<ISSResponse> call, Throwable t) {
                t.fillInStackTrace();
            }
        });

    }

    @Override
    public void setObserver(Observer observer) {
        addObserver(observer);
    }
}
