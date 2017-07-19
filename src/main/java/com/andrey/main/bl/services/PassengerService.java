package com.andrey.main.bl.services;

import com.andrey.main.bl.operations.ServiceOperation;
import com.andrey.main.dl.dao.PassengerDAO;
import com.andrey.main.dl.models.Passenger;

import java.util.List;

public class PassengerService implements ServiceOperation<Passenger> {
    private PassengerDAO passengerDAO = PassengerDAO.getInstance();

    @Override
    public void add(Passenger record) {
        passengerDAO.add(record);
    }

    @Override
    public void delete(Passenger record) {
        passengerDAO.delete(record.getId());
    }

    @Override
    public void update(Passenger record) {
        passengerDAO.update(record);
    }

    @Override
    public List<Passenger> getAll() {
        return passengerDAO.getAll();
    }

    public List<Passenger> searchByFirstName(String text) {
        return passengerDAO.searchByFirstName(text);
    }

    public List<Passenger> searchByLastName(String text) {
        return passengerDAO.searchByLastName(text);
    }

    public List<Passenger> searchByPassport(String text) {
        return passengerDAO.searchByPassport(text);
    }
}
