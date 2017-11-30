package com.example.dominik.evfinders.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dominik on 21.11.2017.
 */

public class DateConverterImpl implements DateConverter {


    @Override
    public long convertDateStringToLong(String stringDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date = format.parse(stringDate);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String convertLongToJson(long date) {
        return null;
    }

    @Override
    public String convertLongToString(long date) {
        return null;
    }
}
