package by.Bohdan.Dmitry.project.service.api.dto;

import java.util.Arrays;

public class Poll {
    private int artist;
    private int[] genres;
    private String about;

    public Poll(int artist, int[] genres, String about) {
        this.artist = artist;
        this.genres = genres;
        this.about = about;
    }

    public int getArtist() {
        return artist;
    }

    public int[] getGenres() {
        return genres;
    }

    public String getAbout() {
        return about;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "artist=" + artist +
                ", genres=" + Arrays.toString(genres) +
                ", about='" + about + '\'' +
                '}';
    }
}
