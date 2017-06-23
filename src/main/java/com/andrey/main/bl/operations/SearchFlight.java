package com.andrey.main.bl.operations;

import com.andrey.main.dl.models.Flight;

import java.util.List;

public interface SearchFlight {
    List<Flight> byNumber(String number);

    List<Flight> byPort(String port);
}
