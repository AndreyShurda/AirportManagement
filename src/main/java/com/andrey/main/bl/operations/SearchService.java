package com.andrey.main.bl.operations;


import com.andrey.main.dl.dao.FlightDAO;
import com.andrey.main.dl.dao.PassengerDAO;
import com.andrey.main.dl.dao.TicketDAO;
import com.andrey.main.dl.models.Flight;
import com.andrey.main.dl.models.Passenger;
import com.andrey.main.dl.models.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SearchService implements SearchFlight, SearchPassenger, SearchTicket {
    private FlightDAO flights = FlightDAO.getInstance();
    private PassengerDAO passengers = PassengerDAO.getInstance();
    private TicketDAO tickets = TicketDAO.getInstance();

//    private static SearchService instance = new SearchService();
//
//    private SearchService() {
//    }

//    public static SearchService getInstance() {
//        return instance;
//    }

    @Override
    public List<Flight> byNumber(String number) {
        return search(flights.getAll(), c -> searchInWord(c.getNumber(), number));
    }

    @Override
    public List<Flight> byPort(String port) {
        return search(flights.getAll(), c -> searchInWord(c.getCity(), port));
    }


    @Override
    public List<Ticket> byPrice(Predicate<Ticket> price) {
        return search(tickets.getAll(), price);
    }

    @Override
    public List<Passenger> byFirstName(String firstName) {
        return search(passengers.getAll(), c -> searchInWord(c.getFirstName(), firstName));
    }

    @Override
    public List<Passenger> byLastName(String lastName) {
        return search(passengers.getAll(), c -> searchInWord(c.getLastName(), lastName));
    }

    @Override
    public List<Passenger> byPassport(String passport) {
        return search(passengers.getAll(), c -> searchInWord(c.getPassport(), passport));
    }

    private <T> List<T> search(List<T> list, Predicate<T> predicate) {
        List<T> result = list.stream()
                .filter(predicate::test)
                .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }

    private boolean searchInWord(String first, String second) {
        return first.toLowerCase().startsWith(second.toLowerCase());
    }
}

