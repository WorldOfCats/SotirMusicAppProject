package com.example.sotito.sotirmusicappproject;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView songPicture = findViewById(R.id.view_songs);
        songPicture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent songInt = new Intent(MainActivity.this, songsActivity.class);
                startActivity(songInt);
            }
        });
        ImageView albumPicture = findViewById(R.id.view_albums);
        albumPicture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent albumInt = new Intent(MainActivity.this, albumsActivity.class);
                startActivity(albumInt);
            }
        });


    }
}
