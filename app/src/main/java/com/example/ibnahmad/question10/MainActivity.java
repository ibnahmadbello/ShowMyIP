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
    private TextView resultTextView;
    private Button showIPButton;
    private ProgressBar searchProgressBar;
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showIPButton = findViewById(R.id.show_ip_button);
        showIPButton.setOnClickListener(this);

        searchProgressBar = findViewById(R.id.progress_bar);

        resultTextView = findViewById(R.id.ip_result);

        presenter = new Presenter(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.show_ip_button){
            disableButton();
            showProgressBar();
            presenter.createInstance();
        }
    }

    public void showProgressBar(){
        searchProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar(){
        searchProgressBar.setVisibility(View.GONE);
    }

    public void disableButton(){
        showIPButton.setEnabled(false);
    }

    public void enableButton(){
        showIPButton.setEnabled(true);
    }

    public void displayResult(Response<IPClass> response) {
        IPClass ipClassResponse = response.body();
        resultTextView.setText(String.format("%s\tfrom\t%s", ipClassResponse.getQuery(), ipClassResponse.getCountry()));
    }
}
