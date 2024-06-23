package com.alura.demo;

import com.alura.demo.models.Serie;
import com.alura.demo.service.ApiHendler;
import com.alura.demo.service.ConvertData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ApiHendler api = new ApiHendler();
		String json = api.getData("https://www.omdbapi.com/?t=suits&apikey=ce012dce");
		ConvertData convertData = new ConvertData();
		Serie serie = convertData.convert(json, Serie.class);
		System.out.println(serie);

	}
}
