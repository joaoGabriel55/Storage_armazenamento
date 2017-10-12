package com.example.quaresma.storage_armazenamento;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class SdCardActivity extends AppCompatActivity {

    private String pictureImagePath = "";
    private static final String FILENAME = "photo";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sd_card);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        imageView = (ImageView) findViewById(R.id.imageViewSD);

    }

    public void openCamera(View v) throws IOException {

        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
        pictureImagePath = storageDir.getAbsolutePath() + "/" + FILENAME;
        File image = new File(pictureImagePath);

        File file = image;

        Uri outputFileUri = FileProvider.getUriForFile(SdCardActivity.this, BuildConfig.APPLICATION_ID + ".provider", file);
        Log.i("Salvando", file.getAbsolutePath());
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(cameraIntent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Imagem salva com sucesso!", Toast.LENGTH_SHORT).show();
            }
        }

    }


}
