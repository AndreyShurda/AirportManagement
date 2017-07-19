package com.andrey.main.bl.services;


import com.andrey.main.dl.dao.DestinationDAO;
import com.andrey.main.dl.models.Arrivals;
import com.andrey.main.dl.models.Departures;
import com.andrey.main.dl.models.Destination;

import java.util.List;

public class DestinationService implements ServiceOperation<Destination> {

    private DestinationDAO destinationDAO = DestinationDAO.getInstance();


    @Override
    public void add(Destination record) {
        destinationDAO.add(record);
    }

    @Override
    public void delete(Destination record) {
        destinationDAO.delete(record);
    }

    @Override
    public void update(Destination record) {
        destinationDAO.update(record);
    }

//    @Override
//    public Flight getById(Flight record){
//        return destinationDAO.getById(record);
//    }

    @Override
    public List<Destination> getAll() {
        return destinationDAO.getAll();
    }

    public List<Destination> searchByNumber(String text) {
        return destinationDAO.searchByNumber(text);
//        return null;
    }

    public List<Destination> searchByCity(String text) {
        return destinationDAO.searchByCity(text);
    }
}
