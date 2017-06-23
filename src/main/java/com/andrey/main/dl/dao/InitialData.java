package com.andrey.main.dl.dao;

import com.andrey.main.bl.access.PermissionAction;
import com.andrey.main.bl.access.User;

import java.util.Locale;

import static com.andrey.main.dl.dao.ApplicationProperties.readProperty;

public class InitialData {
    public static final String DATABASE = readProperty("database");
    public static final String URL = readProperty("url");
    public static final String DB_USER = readProperty("db_user");
    public static final String DB_PASSWORDS = readProperty("db_passwords");
    public static final String TABLE_FLIGHTS = readProperty("table_flights");
    public static final String TABLE_ARRIVALS = readProperty("table_arrivals");
    public static final String TABLE_DEPARTURES = readProperty("table_departures");
    public static final String TABLE_PASSENGERS = readProperty("table_passengers");
    public static final String TABLE_TICKETS = readProperty("table_tickets");
    public static final String LANGUAGE = readProperty("language");

    public static final String PATH_BUNDLES_LOCALE = "bundles.Locale";
    public static Locale LOCALE_VALUE = new Locale(InitialData.LANGUAGE);

    public static User CURRENT_USER;
    public static User USER_ADMIN;
    public static User USER_STAFF;
    public static User USER_GUEST;

    static {
        User admin = new User("Admin");
        admin.addPermission(PermissionAction.ADMIN);
        admin.addPermission(PermissionAction.STAFF);
        USER_ADMIN = admin;

        User staff = new User("STAFF");
        staff.addPermission(PermissionAction.STAFF);
        USER_STAFF = staff;

        User guest = new User("Guest");
        guest.addPermission(PermissionAction.GUEST);
        USER_GUEST = guest;
    }
}
