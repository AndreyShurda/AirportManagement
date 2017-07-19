package com.andrey.main.dl.dao.utils;

import com.andrey.main.dl.models.User;

import java.util.Locale;

import static com.andrey.main.dl.dao.utils.ApplicationProperties.readProperty;

public class InitialData {
    public static final String TABLE_ARRIVALS = readProperty("table_arrivals");
    public static final String TABLE_DEPARTURES = readProperty("table_departures");
    public static final String LANGUAGE = readProperty("language");

    public static final String PATH_BUNDLES_LOCALE = "bundles.Locale";
    public static Locale LOCALE_VALUE = new Locale(InitialData.LANGUAGE);

    public static User CURRENT_USER;
}
