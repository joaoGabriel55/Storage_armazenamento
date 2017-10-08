package com.example.quaresma.storage_armazenamento;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToSDCard(View v){
        startActivity(new Intent(MainActivity.this, SdCardActivity.class));
    }

    public void goToInternStorage(View v){
        startActivity(new Intent(MainActivity.this, InternStorageActivity.class));
    }

    public void goToBD(View v){
        startActivity(new Intent(MainActivity.this, BDActivity.class));
    }
}
