package com.example.quaresma.storage_armazenamento;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.orm.SugarContext;

import java.io.ByteArrayOutputStream;

public class BDActivity extends AppCompatActivity {

    private static final int CODE = 1;
    private Button button;
    private Button buttonSave;
    private ImageView imageView;
    private Bitmap bitmapImg;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd);

        SugarContext.init(this);

        imageView = (ImageView) findViewById(R.id.imageViewBD);
        button = (Button) findViewById(R.id.takePhotoBD);
        buttonSave = (Button) findViewById(R.id.buttonSaveBD);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(takePhoto.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePhoto, CODE);
                }

            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bitmapImg == null)
                    Toast.makeText(context, "Tire primeiro a foto!", Toast.LENGTH_SHORT).show();
                else {

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmapImg.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte imagemByte[] = stream.toByteArray();

                    Imagem imagem = new Imagem();
                    imagem.setPixels(imagemByte);
                    imagem.save(imagem);

                    Log.i("EAE", "MASSA");

                    Toast.makeText(getBaseContext(), "Foto Salva", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        BDActivity.super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            bitmapImg = (Bitmap) extras.get("data");
            imageView.setImageBitmap(bitmapImg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }
}
