package com.example.sotito.sotirmusicappproject;




public abstract class item {
    public int token;
    private int fPictureExtraction = NO_PICTURE;
    public static final int NO_PICTURE = -1;

    public int  fSongId;

    public item(int token, int picture, int sound) {
        this.token = token;
        this.fPictureExtraction = picture;
        this. fSongId = sound;
    }

    public item(int token, int fSongId) {
        this.token = token;
        this. fSongId = fSongId;
    }

    public boolean hasImage() {
        return fPictureExtraction != NO_PICTURE;
    }

    public int getNameInstrument() {
        return token;
    }

    public int getmImageResourceId() {
        return fPictureExtraction;
    }

    public int getfSong() {
        return fSongId;
    }

}
