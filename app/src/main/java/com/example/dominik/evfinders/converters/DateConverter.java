package com.example.dominik.evfinders.converters;

/**
 * Created by Dominik on 21.11.2017.
 */

public interface DateConverter {

    long convertDateStringToLong(String date);

    String convertLongToJson(long date);

    String convertLongToString(long date);
}
