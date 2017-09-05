package com.example.dominik.evfinders.model.repo;

/**
 * Created by Dominik on 05.09.2017.
 */

public interface IPrefs {

    void save(String key, String value);
    String get(String key);
}
