package com.alura.demo.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Serie(
        String Title,
        String Year,
        String Rated,
        String Released,
        String Runtime,
        String Genre,
        String Director,
        String Writer,
        String Actors,
        String Plot,
        String Language,
        String Country,
        String Awards,
        String Poster
) {
}
