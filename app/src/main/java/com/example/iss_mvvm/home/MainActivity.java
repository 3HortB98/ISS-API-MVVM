package com.example.iss_mvvm.home;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.iss_mvvm.R;
import com.example.iss_mvvm.net.ISSResponse;
import com.example.iss_mvvm.net.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etLatitude;
    EditText etLongitude;
    Button btnSearch;
    ISS_Adapter iss_adapter = new ISS_Adapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etLatitude = findViewById(R.id.etLatitude);
        etLongitude = findViewById(R.id.etLongitude);
        btnSearch = findViewById(R.id.btnGetLocation);

        RecyclerView recyclerView = findViewById(R.id.rvData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(iss_adapter);

        final HomeViewModel homeViewModel = new HomeViewModel();
        homeViewModel.getLocationLiveData().observe(this, new Observer<List<Response>>() {
            @Override
            public void onChanged(@Nullable List<Response> responses) {
                iss_adapter.setData(responses);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeViewModel.getLocation(etLatitude.getText().toString(),etLongitude.getText().toString());
            }
        });
    }
}
