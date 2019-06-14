package com.example.ibnahmad.question10;

import android.util.Log;
import android.view.View;

import com.example.ibnahmad.question10.model.IPClass;
import com.example.ibnahmad.question10.network.IPApi;
import com.example.ibnahmad.question10.network.IPService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Presenter {

    private MainActivity mainActivity;
    private static final String TAG = Presenter.class.getSimpleName();



    public Presenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void createInstance(){
        Retrofit retrofit = IPApi.getRetrofit();
        IPService ipService = retrofit.create(IPService.class);
        ipService.getIPDetail().enqueue(new Callback<IPClass>() {
            @Override
            public void onResponse(Call<IPClass> call, Response<IPClass> response) {
                mainActivity.hideProgressBar();
                mainActivity.enableButton();
                mainActivity.displayResult(response);
            }

            @Override
            public void onFailure(Call<IPClass> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "Error loading response");
            }
        });
    }
}
