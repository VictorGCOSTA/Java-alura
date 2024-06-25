package com.alura.demo.principal;

import com.alura.demo.models.Brand;
import com.alura.demo.models.Vehicle;
import com.alura.demo.models.VehicleList;
import com.alura.demo.service.ApiHendler;
import com.alura.demo.service.ConvertData;

import java.util.*;
import java.util.stream.Collectors;

public class UserInteraction {

    private Scanner scanner = new Scanner(System.in);
    private final String ADDRESS = "https://parallelum.com.br/fipe/api/v1";
    private ApiHendler api = new ApiHendler();
    private ConvertData convertData = new ConvertData();

    public void showMenu(){
        System.out.println("Selecione uma opção");
        System.out.println("Carro");
        System.out.println("Moto");
        System.out.println("Caminhão");
        String newAddress = switch (scanner.nextLine().toLowerCase(Locale.ROOT)) {
            case "carro" -> ADDRESS + "/carros/marcas";
            case "moto" -> ADDRESS + "/motos/marcas";
            case "caminhão" -> ADDRESS + "/caminhoes/marcas";
            default -> throw new IllegalArgumentException("Opção inválida");
        };
        String data = api.getData(newAddress);

        List<Brand> brands = convertData.convertList(data, Brand.class);
        System.out.println("Marcas: ");
        brands.stream().sorted(Comparator.comparing(e -> Integer.parseInt(e.codigo()))).forEach(e ->
                System.out.println("Nome: " + e.nome() + " Código: " + e.codigo()));

        System.out.println("Digite o código da marca: ");
        String code = scanner.nextLine();
        String modelsAddress = newAddress + "/" + code + "/modelos";
        String modelsData = api.getData(modelsAddress);
        VehicleList models = convertData.convert(modelsData, VehicleList.class);
        System.out.println("Modelos: ");
        models.modelos().stream().sorted(Comparator.comparing(e -> Integer.parseInt(e.codigo()))).forEach(e ->
                System.out.println("Nome: " + e.nome() + " Código: " + e.codigo()));

        System.out.println("Digite o código do modelo: ");
        String modelCode = scanner.nextLine();
        String yearAddress = modelsAddress + "/" + modelCode + "/anos";
        String yearData = api.getData(yearAddress);
        List<String> years = convertData.convertList(yearData, Brand.class).stream().map(Brand::codigo).collect(Collectors.toList());
        long startTime = System.nanoTime();
        years.forEach(y -> {
            var temp_address = yearAddress + "/" + y;
            String json = api.getData(temp_address);
            Vehicle v = convertData.convert(json, Vehicle.class);
            System.out.println(v);
        });
        long endTime = System.nanoTime();

        // Calcula o tempo de execução em segundos
        double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("Tempo de execução: " + durationInSeconds + " segundos");

        years = convertData.convertList(yearData, Brand.class).stream().map(Brand::codigo).collect(Collectors.toList());
         startTime = System.nanoTime();
        years.parallelStream().forEach(y -> {
            var temp_address = yearAddress + "/" + y;
            String json = api.getData(temp_address);
            Vehicle v = convertData.convert(json, Vehicle.class);
            System.out.println(v);
        });
        endTime = System.nanoTime();

        // Calcula o tempo de execução em segundos
        durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("Tempo de execução: " + durationInSeconds + " segundos");

    }
}
