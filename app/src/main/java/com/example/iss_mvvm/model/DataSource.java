package com.example.iss_mvvm.model;

import java.util.Observer;

public interface DataSource {
     void getLocation(String latitude, String longitude);
     void setObserver(Observer observer);

}
