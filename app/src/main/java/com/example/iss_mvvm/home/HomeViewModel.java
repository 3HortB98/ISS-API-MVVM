package com.example.iss_mvvm.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.iss_mvvm.model.DataSource;
import com.example.iss_mvvm.model.ISS_Respository;
import com.example.iss_mvvm.model.LocalDataSource;
import com.example.iss_mvvm.model.RemoteDataSource;
import com.example.iss_mvvm.net.ISSResponse;
import com.example.iss_mvvm.net.Response;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class HomeViewModel implements Observer {
    private final DataSource iss_Respository;

    private final MutableLiveData<List<Response>> responseLiveData = new MutableLiveData<>();
    public HomeViewModel(){
        iss_Respository = new ISS_Respository(new RemoteDataSource(), new LocalDataSource());
    }
    public LiveData<List<Response>> getLocationLiveData(){
        return responseLiveData;
    }

    public void getLocation(String latitude, String longitude){
        iss_Respository.setObserver(this);
        iss_Respository.getLocation(latitude,longitude);
    }
    @Override
    public void update(Observable o, Object result) {
        List<Response> responses = (List<Response>) result;
        responseLiveData.setValue(responses);
    }
}
