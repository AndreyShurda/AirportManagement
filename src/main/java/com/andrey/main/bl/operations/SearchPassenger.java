package com.andrey.main.bl.operations;

import com.andrey.main.dl.models.Passenger;

import java.util.List;

public interface SearchPassenger {
    List<Passenger> byFirstName(String firstName);

    List<Passenger> byLastName(String lastName);

    List<Passenger> byPassport(String passport);
}
