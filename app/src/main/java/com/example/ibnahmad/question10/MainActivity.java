package com.example.ibnahmad.question10;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibnahmad.question10.model.IPClass;
import com.example.ibnahmad.question10.network.IPApi;
import com.example.ibnahmad.question10.network.IPService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private IPService ipService;
    private TextView resultTextView;
    private Button showIPButton;
    private ProgressBar searchProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showIPButton = findViewById(R.id.show_ip_button);
        showIPButton.setOnClickListener(this);

        searchProgressBar = findViewById(R.id.progress_bar);

        resultTextView = findViewById(R.id.ip_result);

        ipService = IPApi.getRetrofit(this).create(IPService.class);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.show_ip_button){
            showIPButton.setEnabled(false);
            searchProgressBar.setVisibility(View.VISIBLE);
            makeIPRequest();
        }
    }

    private void makeIPRequest() {
        ipService.getIPDetail().enqueue(new Callback<IPClass>() {
            @Override
            public void onResponse(Call<IPClass> call, Response<IPClass> response) {
                searchProgressBar.setVisibility(View.GONE);
                displayResult(response);
//                Toast.makeText(MainActivity.this, response.body().getCountry(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<IPClass> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "Error loading response");
            }
        });
    }

    private void displayResult(Response<IPClass> response) {
        IPClass ipClassResponse = response.body();
        resultTextView.setText(String.format("%s\tfrom\t%s", ipClassResponse.getQuery(), ipClassResponse.getCountry()));
    }
}
