package com.example.iss_mvvm.model;

import java.util.Observable;
import java.util.Observer;

public class ISS_Respository extends Observable implements Observer,DataSource {
    private final DataSource remoteDataSource;
    private  final DataSource localDataSource;

    public ISS_Respository(DataSource remoteDataSource, DataSource localDataSource){
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public void getLocation(String latitude, String longitude) {
        remoteDataSource.setObserver(this);
        remoteDataSource.getLocation(latitude,longitude);
    }

    @Override
    public void setObserver(Observer observer) {
        addObserver(observer);
    }

    @Override
    public void update(Observable o, Object result) {
        setChanged();
        notifyObservers(result);
    }
}
