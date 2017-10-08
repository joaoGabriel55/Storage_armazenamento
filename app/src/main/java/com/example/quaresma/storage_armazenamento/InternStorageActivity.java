package com.example.quaresma.storage_armazenamento;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InternStorageActivity extends AppCompatActivity {

    String FILENAME = "photo_master";
    String picturePath = "";
    ImageView imageView;
    Bitmap img;
    EditText editText;
    Context context;
    static Uri capturedImageUri = null;
    private String pictureImagePath = "";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern_storage);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        builder.detectFileUriExposure();

        imageView = (ImageView) findViewById(R.id.imageViewIntern);
/*        editText = (EditText) findViewById(R.id.editText);
        readFile();*/
        Button button = (Button) findViewById(R.id.takePhotoInter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = timeStamp + ".jpg";
                File storageDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
                File file = new File(pictureImagePath);
                Uri outputFileUri = Uri.fromFile(file);
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                //Por motivos obvios
                //Esse zero é nosso result code para saber se vai dar bom ou não
                //Intent contem a msg
                Log.i("TO usando agr", "EAE MLK");
                startActivityForResult(cameraIntent, 1);
                Log.i("TO usando agr", "EAE MLK");
            }

            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (requestCode == 1) {
                    File imgFile = new File(pictureImagePath);
                    if (imgFile.exists()) {
                        img = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        imageView.setImageBitmap(img);

                    }
                }

                Log.i("TO usando agr", "Sera que deu bom ????");
            }
        });

    }


    public void takePhotoIntern(View v) {
        ContextWrapper contextWrapper = new ContextWrapper(this);

        String timestamp = new SimpleDateFormat("ddMMyyyy").format(new Date());
        String imageFileName = timestamp + ".jpg";
        File storagediretorio = contextWrapper.getDir("photo", Context.MODE_PRIVATE);

        picturePath = storagediretorio.getAbsolutePath() + "/" + imageFileName;

        File file = new File(picturePath);
        Uri outputFileUri = Uri.fromFile(file);
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        //Por motivos obvios
        //Esse zero é nosso result code para saber se vai dar bom ou não
        //Intent contem a msg
        startActivityForResult(cameraIntent, 1);
        Log.i("TO usando agr", "EAE MLK");
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            File imgFile = new File(picturePath);
            if (imgFile.exists()){
                img = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView.setImageBitmap(img);
            }
        }

        Log.i("TO usando agr","Sera que deu bom ????");
    }*/

    public void readFile() {
        // Read File and Content
        FileInputStream fin;
        try {
            //abre o arquivo chamado FILENAME para LEITURA
            fin = openFileInput(FILENAME);
            int size;
            String newText = "";

            // read inside if it is not null (-1 means empty)
            while ((size = fin.read()) != -1) {
                // add & append content
                newText += Character.toString((char) size);
            }

            editText.setText(newText);
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String salvarInterno(View v) {
        ContextWrapper contextWrapper = new ContextWrapper(this);

        File dir = contextWrapper.getDir("photo", Context.MODE_PRIVATE);
        File path = new File(dir, "image.jpg");

        //Write file and content
        FileOutputStream fos;

        try {
            //abre o arquivo chamado FILENAME para ESCRITA
            fos = new FileOutputStream(path);

            fos.close();
            Toast.makeText(this, "Salvo!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dir.getAbsolutePath();

        /*//Write file and content
        FileOutputStream fos;

        try {
            //abre o arquivo chamado FILENAME para ESCRITA
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(editText.getText().toString().getBytes());
            fos.close();
            Toast.makeText(this, "Salvo!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }


}
