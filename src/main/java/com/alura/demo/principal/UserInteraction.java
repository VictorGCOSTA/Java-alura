package com.alura.demo.principal;

import com.alura.demo.models.Episode;
import com.alura.demo.models.EpisodeDTO;
import com.alura.demo.models.Season;
import com.alura.demo.models.Serie;
import com.alura.demo.service.ApiHendler;
import com.alura.demo.service.ConvertData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserInteraction {

    private Scanner scanner = new Scanner(System.in);
    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=ce012dce";
    private ApiHendler api = new ApiHendler();
    private ConvertData convertData = new ConvertData();

    public void showMenu(){
        System.out.println("Welcome to Omdb API");
        System.out.println("Please enter your movie title: ");
        String movieTitle = scanner.nextLine();
        String address = ADDRESS + movieTitle.replace(" ", "+") + APIKEY;
        String serieJson = api.getData(address);

        Serie serie = convertData.convert(serieJson, Serie.class);
        List<Season> seasons = new ArrayList<>();
        for (int i = 1 ; i <= serie.totalSeasons(); i++){
        	String seasonJson = api.getData(address + "&season="+i);
        	Season season = convertData.convert(seasonJson, Season.class);
        	seasons.add(season);
        }

        List<EpisodeDTO> episodes = seasons.stream()
                .flatMap(s -> s.Episodes().stream())
                .toList();

        episodes.stream()
                .filter(e -> !e.imdbRating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodeDTO::imdbRating).reversed())
                .limit(10)
                .map(e -> e.Title().toUpperCase())
                .forEach(System.out::println);

        List<Episode> epsModel = seasons.stream()
                .flatMap(s -> s.Episodes().stream().map(e -> new Episode(e, Integer.parseInt(s.Season()))))
                .toList();
        System.out.println("Choose a title: ");
        String text = scanner.nextLine();
        Optional<Episode> ep = epsModel.stream()
                .filter(e -> e.getTitle().toUpperCase().contains(text.toUpperCase()))
                .findFirst();

        if(ep.isPresent()){
            System.out.println(ep.get());
        }else {
            System.out.println("Title not found");
        }
//        epsModel.forEach(System.out::println);

//        System.out.println("Choose a year: ");
//        int year = scanner.nextInt();
//        LocalDate date = LocalDate.of(year, 1, 1);
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        epsModel.stream()
//                .filter(e -> e.getReleased() != null && e.getReleased().isAfter(date))
//                .forEach(e -> System.out.println(
//                        "Season: " + e.getSeasonNumber() +
//                                " Title: " + e.getTitle() +
//                                " Rating: " + e.getImdbRating() +
//                                " Released: " + e.getReleased().format(df)
//                ));
    }
}
