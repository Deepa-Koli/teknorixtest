package com.androideeps.teknorix.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.androideeps.teknorix.R;
import com.androideeps.teknorix.adapter.RetrofitAdapter;
import com.androideeps.teknorix.models.Datum;
import com.androideeps.teknorix.network.ApiInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private RetrofitAdapter retroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lv);
        getJSONResponse();
    }

    private void getJSONResponse(){

        //creating retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        //creating our api
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getString();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        String jsonresponse = response.body().toString();
                       writeListView(jsonresponse);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void writeListView(String response){

        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(response);
                ArrayList<Datum> modelListViewArrayList = new ArrayList<>();
                JSONArray dataArray  = obj.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {

                   Datum modelListView = new Datum();
                    JSONObject dataobj = dataArray.getJSONObject(i);

                    modelListView.setAvatar(dataobj.getString("avatar"));
                    modelListView.setEmail(dataobj.getString("email"));
                    modelListView.setFirstName(dataobj.getString("first_name"));
                    modelListView.setLastName(dataobj.getString("last_name"));

                    modelListViewArrayList.add(modelListView);
                }

                retroAdapter = new RetrofitAdapter(this, modelListViewArrayList);
                listView.setAdapter(retroAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}