package com.alura.demo.service;

import com.alura.demo.models.Brand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConvertData implements IConvertData{
    private ObjectMapper mapper = new ObjectMapper();
    public ConvertData() {}

    @Override
    public <T> T convert(String data, Class<T> dataType) {
        try {
            if(dataType.isAssignableFrom(List.class)) {
                return (T) mapper.readValue(data, new TypeReference<List<T>>(){});
            } else {
                return mapper.readValue(data, dataType);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    };


    public <T> List<T> convertList(String data, Class<T> dataType) {
        try {
            return mapper.readValue(data, mapper.getTypeFactory().constructCollectionType(List.class, dataType));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
