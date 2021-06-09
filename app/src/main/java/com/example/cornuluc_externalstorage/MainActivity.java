package com.example.cornuluc_externalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    // nom de fichier
    private static String fileName = null;
    // chaine de caractère à enregistrer
    private static String contentString = null;
    // Output & Input des fichiers
    private static FileOutputStream outStream = null;
    private static FileInputStream inputStream = null;

    private static StringBuilder content = null;

    // Référence des vues
    private static EditText nameInput = null;
    private static Button saveButton = null;
    private static Button showButton = null;



    // Cache
    private static File cache = null;
    private static FileOutputStream fos = null;
    private static FileInputStream fis = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des variables utilisées
        init();


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentString = nameInput.getText().toString(); // On récupère ce qui est écrit dans l'editText

                // Version stockage interne classique
                // Ouverture d'un fichier en écriture
                try {
                    outStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                // On écrit dans le fichier puis on le ferme
                try {
                    outStream.write(contentString.getBytes());
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                // Version cache
                /*// Ouverture d'un fichier en écriture
                try {
                    cache = new File(getCacheDir(), fileName);
                    fos = new FileOutputStream(cache);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                // On écrit dans le fichier puis on le ferme
                try {
                    fos.write(contentString.getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });


        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Version  interne classique
                // Ouverture d'un fichier en lecture
                try {
                    inputStream = openFileInput(fileName);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                // On lit dans le fichier puis on le ferme
                try {
                    byte[] buffer = new byte[1024];
                    content = new StringBuilder();
                    while ((inputStream.read(buffer)) != -1) {
                        content.append(new String(buffer));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();


                // Version cache
                /*// Ouverture d'un fichier en lecture
                try {
                    cache = new File(getCacheDir(), fileName);
                    fis = new FileInputStream(cache);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                // On lit dans le fichier puis on le ferme
                try {
                    byte[] buffer = new byte[1024];
                    content = new StringBuilder();
                    while ((fis.read(buffer)) != -1) {
                        content.append(new String(buffer));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();*/
            }
        });
    }


    protected void init() {
        nameInput = (EditText) findViewById(R.id.editTextTextPersonName);
        saveButton = (Button) findViewById(R.id.buttonSave);
        showButton = (Button) findViewById(R.id.buttonShow);
        fileName = (String) "Storage";
    }

}