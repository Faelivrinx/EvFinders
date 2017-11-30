package com.example.dominik.evfinders.converters;

/**
 * Created by Dominik on 22.11.2017.
 */

public interface Converter<T, C> {
    C convert(T object);
}
