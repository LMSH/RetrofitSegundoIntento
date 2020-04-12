package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.Interface.JsonPlaceHolderApi;
import com.example.myapplication.Model.Albums;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView myJsonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myJsonTextView = findViewById(R.id.text_view);
        getAlbums();
    }

    private void getAlbums (){

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Albums>> call = jsonPlaceHolderApi.getAlbums();

        call.enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
                if(!response.isSuccessful()){
                    myJsonTextView.setText("Codigo: " + response.code());
                    return;

                }

                List<Albums> listAlbums = response.body();
                for (Albums albums: listAlbums){
                    String content = "";
                    content += "userID: " + albums.getUserID() + "\n";
                    content += "id: " + albums.getId() + "\n";
                    content += "title: " + albums.getTitle() + "\n" + "\n" + "\n";
                    myJsonTextView.append(content);


                }
            }

            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {
                myJsonTextView.setText(t.getMessage());
            }
        });

    }

}
