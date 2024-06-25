package com.alura.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vehicle(
        int TipoVeiculo,
        String Valor,
        String Marca,
        String Modelo,
        int AnoModelo,
        String Combustivel,
        String CodigoFipe,
        String MesReferencia,
        String SiglaCombustivel
) {
}