package com.example.activityelementos;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonPlay = findViewById(R.id.buttonPlay);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.artorias);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    // Si el audio está reproduciéndose, pausamos
                    mediaPlayer.pause();
                    isPlaying = false; // Actualizamos el estado
                } else {
                    // Si el audio está en pausa o no se ha reproducido, empezamos a reproducir
                    mediaPlayer.start();
                    isPlaying = true; // Actualizamos el estado
                }
            }
        });

        Button buttonVideo = findViewById(R.id.buttonVideo);
        buttonVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar VideoActivity usando un Intent
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intent); // Lanza la nueva actividad
            }
        });

        Button buttonAnimation = findViewById(R.id.buttonAnimation);
        final ImageView imageView = findViewById(R.id.torias);
        buttonAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation fadeInAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade);
                imageView.startAnimation(fadeInAnimation);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Liberamos los recursos del MediaPlayer cuando se destruye la actividad
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}