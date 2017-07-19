package com.andrey.main.dl.dao;


import org.apache.log4j.Logger;

public class FlightTODAO{
    private static FlightTODAO instance = new FlightTODAO();
    private static String TABLE = InitialData.TABLE_ARRIVALS;
    private static final Logger log = Logger.getLogger(FlightTODAO.class);

    private FlightTODAO() {
    }

    public static FlightTODAO getInstance() {
        return instance;
    }


}
