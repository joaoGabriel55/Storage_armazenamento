package com.example.quaresma.storage_armazenamento;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternStorageActivity extends AppCompatActivity {

    String FILENAME = "photo_master";
    ImageView imageView;
    Bitmap img;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern_storage);

        imageView = (ImageView) findViewById(R.id.imageViewIntern);
/*        editText = (EditText) findViewById(R.id.editText);

        readFile();*/

    }



    public void takePhotoIntern(View v){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        //Por motivos obvios
        //Esse zero é nosso result code para saber se vai dar bom ou não
        //Intent contem a msg
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null){
            Bundle bundle = data.getExtras();
            if(bundle != null){
                //Esse "data" é nossa chave ou seja podia ser bananinha
                img = (Bitmap) bundle.get("data");

                imageView = (ImageView) findViewById(R.id.imageViewIntern);
                imageView.setImageBitmap(img);

            }
        }

    }

    public void readFile() {
        // Read File and Content
        FileInputStream fin ;
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



    public String salvarInterno(View v){
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

    public void readFileClick(View v) {
        // Read File and Content
        try {
            String path = null;
            File f = new File(path, "image.jpg");
            Bitmap img2 = BitmapFactory.decodeStream(new FileInputStream(f));
            imageView.setImageBitmap(img2);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       /* FileInputStream fin ;
        try {
            //abre o arquivo chamado FILENAME para LEITURA
            fin = openFileInput(FILENAME);
            int size;
            String newText = "";

            // le enquanto é possível
            while ((size = fin.read()) != -1) {
                Log.i( "Leu", ""+(char)size);
                // add & append content
                newText += Character.toString((char) size);
            }

            Toast.makeText(this, newText, Toast.LENGTH_SHORT).show();
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
