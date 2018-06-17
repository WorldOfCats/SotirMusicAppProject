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

public class songsActivity extends AppCompatActivity {
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
    protected void onCreate(Bundle instanceSaved) {
        super.onCreate(instanceSaved);
        setContentView(R.layout.entity_list);

        manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final List<item> songsListItems = new ArrayList<>();
        songsListItems.add(new songs (R.string.song_wait_and_bleed, R.drawable.songs, R.raw.instruments_guitar));
        songsListItems.add(new songs (R.string.song_ev_sistr, R.drawable.songs, R.raw.instruments_piano));
        songsListItems.add(new songs (R.string.song_nannuflay, R.drawable.songs, R.raw.instruments_saxophon));
        songsListItems.add(new songs (R.string.song_syria, R.drawable.songs, R.raw.instruments_tambourine));
        songsListItems.add(new songs (R.string.song_chambermaid_swing, R.drawable.songs, R.raw.instruments_tambourine));


        itemAdapter adaptor = new itemAdapter(this, songsListItems, R.color.category_instrument);
        ListView listView = findViewById(R.id.list_entity);

        listView.setAdapter(adaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int x, long l) {
                playerExecution();
                item song = songsListItems.get(x);

                int dataR = manager.requestAudioFocus(audioTransition, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (dataR == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    player = MediaPlayer.create(songsActivity.this, song.getfSong());
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
