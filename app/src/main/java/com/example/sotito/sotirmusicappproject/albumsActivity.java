package com.example.sotito.sotirmusicappproject;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class albumsActivity extends AppCompatActivity {
    private MediaPlayer player;
    private AudioManager manager;

    private MediaPlayer.OnCompletionListener listenerItem = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer player) {
            playerExecution();
        }
    };
    private AudioManager.OnAudioFocusChangeListener audioTransition = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int transition) {
            if (transition == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || transition == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                player.pause();
                player.seekTo(0);
            } else if (transition == AudioManager.AUDIOFOCUS_GAIN) {
                player.start();
            } else if (transition == AudioManager.AUDIOFOCUS_LOSS) {
                playerExecution();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entity_list);

        manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final List<item> albumListItem = new ArrayList<>();
        albumListItem.add(new albums(R.string.album_load, R.drawable.albums));
        albumListItem.add(new albums(R.string.album_reload, R.drawable.albums));
        albumListItem.add(new albums(R.string.album_st_anger, R.drawable.albums));
        albumListItem.add(new albums(R.string.album_death_death_magnetic, R.drawable.albums));
        albumListItem.add(new albums(R.album_hardwired_to_self_destruct, R.drawable.albums));

        itemAdapter adaptor = new itemAdapter(this, albumListItem, R.color.category_song);
        ListView listView = findViewById(R.id.list_entity);

        listView.setAdapter(adaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int x, long l) {
                playerExecution();
                item song = albumListItem.get(x);

                int dataR = manager.requestAudioFocus(audioTransition, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (dataR == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    player = MediaPlayer.create(albumsActivity.this, song.getfSong());
                    player.start();

                    player.setOnCompletionListener(listenerItem);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        playerExecution();
    }

    private void playerExecution() {
        if (player != null) {
            player.release();
            player = null;

            manager.abandonAudioFocus(audioTransition);
        }
    }
}
