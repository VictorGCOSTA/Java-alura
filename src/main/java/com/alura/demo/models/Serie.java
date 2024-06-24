package com.alura.demo.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Serie(
        String Title,
        String Year,
        String Rated,
        String Released,
        Integer totalSeasons,
        String Genre,
        String Actors
) {
}
