package com.alura.demo.models;

import java.util.List;

public record Season(
        String Title,
        String Season,
        int totalSeasons,
        List<EpisodeDTO> Episodes,
        String Response
) {
}
