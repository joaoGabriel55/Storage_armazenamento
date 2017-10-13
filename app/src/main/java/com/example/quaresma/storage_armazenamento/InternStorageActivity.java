package com.example.quaresma.storage_armazenamento;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class InternStorageActivity extends AppCompatActivity {

    private static final String FILENAME = "photo";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern_storage);

        imageView = (ImageView) findViewById(R.id.imageViewIntern);

    }


    public void takePhotoIntern(View v) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 0 && resultCode == RESULT_OK) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {

                    FileOutputStream fos = null;
                    Bitmap bitmap = (Bitmap) bundle.get("data");

                    try {
                        //abre o arquivo chamado FILENAME para ESCRITA
                        fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

                        Toast.makeText(this, "Salvo!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos);
                    imageView.setImageBitmap(bitmap);

                    try {
                        //metodo que garante o envio do último lote de bytes enviados para gravação
                        fos.flush();
                        //metodo que fecha a stram de leitura ou gravação
                        fos.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
