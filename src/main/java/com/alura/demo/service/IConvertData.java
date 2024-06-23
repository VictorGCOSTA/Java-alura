package com.alura.demo.service;

public interface IConvertData{
    <T> T convert(String data, Class<T> dataType);
}
