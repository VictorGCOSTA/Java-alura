package com.alura.demo.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeDTO(
        String Title,
        String Released,
        Integer Episode,
        String imdbRating,
        String imdbID
) {
}
