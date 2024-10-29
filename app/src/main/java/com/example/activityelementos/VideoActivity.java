package com.example.activityelementos;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // Manejo de insets para los bordes de pantalla
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Botón para regresar a la actividad anterior
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finaliza la actividad actual y regresa a la anterior
            }
        });

        // Inicializamos el VideoView
        VideoView videoView = findViewById(R.id.videoView);

        // Botón para reproducir el video
        Button buttonPlayV = findViewById(R.id.buttonPlayV);
        buttonPlayV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reproducimos un video desde los recursos raw
                Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.dark);
                videoView.setVideoURI(videoUri);
                videoView.start();
            }
        });
    }
}
