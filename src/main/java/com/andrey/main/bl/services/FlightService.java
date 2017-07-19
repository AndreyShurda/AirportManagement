package com.andrey.main.bl.services;

import com.andrey.main.dl.dao.FlightDAO;
import com.andrey.main.dl.models.Flight;

import java.util.List;

public class FlightService implements ServiceOperation<Flight> {

    private FlightDAO flightDAO = FlightDAO.getInstance();

    @Override
    public void add(Flight record) {
        flightDAO.add(record);
    }

    @Override
    public void delete(Flight record) {
        flightDAO.delete(record.getId());
    }

    @Override
    public void update(Flight record) {
        flightDAO.update(record);
    }

    @Override
    public List<Flight> getAll() {
        return flightDAO.getAll();
    }

    public Flight getById(long id){
        return flightDAO.getById(id);
    }
}
