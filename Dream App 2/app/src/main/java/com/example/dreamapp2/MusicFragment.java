package com.example.dreamapp2;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MusicFragment extends Fragment {
    MediaPlayer music[] = new MediaPlayer[4];
    int[] songs = {R.raw.can_you_hear_the_music, R.raw.im_just_ken, R.raw.hit_it, R.raw.annhilate};
    int[] images = {R.drawable.oppenheimer, R.drawable.barbie, R.drawable.mi7, R.drawable.spiderverse};

    String[] song_names = {"Can you hear the music", "I'm just Ken!", "Hit it", "Annhilate"};
    TextView musicTitle;
    ImageView albumArt;
    Boolean playPause = false;
    FloatingActionButton play, pause, previous, next;
    int i = 0;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_music, container, false);

        play = view.findViewById(R.id.play);
        pause = view.findViewById(R.id.pause);
        previous = view.findViewById(R.id.previous);
        next = view.findViewById(R.id.next);

        for (int j=0; j<songs.length; j++) {
            music[j] = MediaPlayer.create(requireContext(), songs[j]);
        }

        albumArt = view.findViewById(R.id.album_art);
        musicTitle = view.findViewById(R.id.song_title);
        musicTitle.setText(song_names[i]);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSong();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseSong();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousSong();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextSong();
            }
        });

        return view;
    }

    private void nextSong() {
        music[i].stop();
        music[i] = MediaPlayer.create(getContext(), songs[i]);
        if (i == songs.length - 1) {
            i=0;
        }
        else
            i++;
        albumArt.setImageResource(images[i]);
        musicTitle.setText(song_names[i]);
        play.setImageResource(R.drawable.pause_icon);
        playPause = true;
        music[i].start();
    }

    private void pauseSong() {
        music[i].stop();
        music[i] = MediaPlayer.create(getContext(), songs[i]);
        play.setImageResource(R.drawable.play_icon);
        playPause = false;
    }

    private void previousSong() {
        music[i].stop();
        music[i] = MediaPlayer.create(getContext(), songs[i]);

        if (i==0) {
            i = songs.length - 1;
        }
        else
            i -= 1;
        albumArt.setImageResource(images[i]);
        musicTitle.setText(song_names[i]);
        play.setImageResource(R.drawable.pause_icon);
        playPause = true;
        music[i].start();
    }

    private void playSong() {
        if (!playPause) {
            music[i].start();
            play.setImageResource(R.drawable.pause_icon);
            playPause = true;
        }
        else {
            music[i].pause();
            play.setImageResource(R.drawable.play_icon);
            playPause = false;
        }
    }
}