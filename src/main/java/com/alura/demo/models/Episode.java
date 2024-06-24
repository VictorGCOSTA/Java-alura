package com.alura.demo.models;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {
    private String Title;
    private LocalDate Released;
    private Integer EpisodeNumber;
    private Integer SeasonNumber;
    private Double imdbRating;
    private String imdbID;

    @Override
    public String toString() {
        return "Episode{" +
                "Title='" + Title + '\'' +
                ", Released=" + Released +
                ", EpisodeNumber=" + EpisodeNumber +
                ", SeasonNumber=" + SeasonNumber +
                ", imdbRating=" + imdbRating +
                ", imdbID='" + imdbID + '\'' +
                '}';
    }

    public Episode(EpisodeDTO episodeDTO, Integer seasonNumber) {
        this.Title = episodeDTO.Title();

        try{
            this.Released = LocalDate.parse(episodeDTO.Released());
        }catch (DateTimeParseException e) {
            this.Released = null;
        }
        this.EpisodeNumber = episodeDTO.Episode();
        this.SeasonNumber = seasonNumber;

        try{
            this.imdbRating = Double.valueOf(episodeDTO.imdbRating());
        }catch (NumberFormatException e) {
            this.imdbRating = 0.0;
        }
        this.imdbID = episodeDTO.imdbID();
    }

    public Integer getEpisodeNumber() {
        return EpisodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        EpisodeNumber = episodeNumber;
    }

    public Integer getSeasonNumber() {
        return SeasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        SeasonNumber = seasonNumber;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public LocalDate getReleased() {
        return Released;
    }

    public void setReleased(LocalDate released) {
        Released = released;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }
}
