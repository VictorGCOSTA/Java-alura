package com.alura.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData{
    private ObjectMapper mapper = new ObjectMapper();
    public ConvertData() {}

    @Override
    public <T> T convert(String data, Class<T> dataType) {
        try {
            return mapper.readValue(data, dataType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
