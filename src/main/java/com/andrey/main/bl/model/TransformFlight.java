package com.andrey.main.bl.model;

import com.andrey.main.bl.services.ServiceOperation;
import com.andrey.main.dl.dao.ArrivalsDAO;
import com.andrey.main.dl.dao.DeparturesDAO;
import com.andrey.main.dl.dao.FlightDAO;
import com.andrey.main.dl.models.Arrivals;
import com.andrey.main.dl.models.Departures;
import com.andrey.main.dl.models.Destination;
import com.andrey.main.dl.models.Flight;

import java.util.ArrayList;
import java.util.List;

public class TransformFlight implements ServiceOperation<FlightTO> {

    private FlightDAO flightDAO = FlightDAO.getInstance();
    private ArrivalsDAO arrivalsDAO = ArrivalsDAO.getInstance();
    private DeparturesDAO departuresDAO = DeparturesDAO.getInstance();

    public static void main(String[] args) {
        TransformFlight transformFlight = new TransformFlight();
        List<FlightTO> flightTOList = transformFlight.getAll();
        System.out.println(flightTOList);
    }

    @Override
    public void add(FlightTO record) {

    }

    @Override
    public void delete(FlightTO record) {

    }

    @Override
    public void update(FlightTO record) {

    }

    @Override
    public List<FlightTO> getAll() {
        return null;
    }

//    public List<FlightTO> getAll(List<Departures> departures) {
//        List<FlightTO> flightTOList = new ArrayList<>();
//
////        FlightTO flightTO = new FlightTO();
////        for (Arrivals arrivals : arrivalsDAO.getAll()) {
////        for (Departures arrivals : departuresDAO.getAll()) {
//////            flightTO.setId((arrivals.getDestination().getId()));
//////            flightTO.setNumber(arrivals.getDestination().getNumber());
//////            flightTO.setDate(arrivals.getDate());
//////            flightTO.setCity(arrivals.getCity());
//////            flightTO.setTerminal(arrivals.getTerminal());
//////            flightTO.setStatus(arrivals.getStatus());
//////            flightTO.setGate(arrivals.getGate());
////            flightTOList.add(convertFlight(arrivals));
////        }
//        for (Departures departure : departures) {
//            flightTOList.add(convertFlight(departure));
////        }
//
//        return flightTOList;
//    }


    public List<FlightTO> getAll(List<Arrivals> arrivalses) {
        List<FlightTO> flightTOList = new ArrayList<>();

        for (Arrivals arrivals : arrivalses) {
            flightTOList.add(convertFlight(arrivals));
        }

        return flightTOList;
    }


    private FlightTO convertFlight(Arrivals arrivals){
        FlightTO flightTO = new FlightTO();
        flightTO.setId((arrivals.getFlight().getId()));
        flightTO.setNumber(arrivals.getFlight().getNumber());
        flightTO.setDate(arrivals.getDate());
        flightTO.setCity(arrivals.getCity());
        flightTO.setTerminal(arrivals.getTerminal());
        flightTO.setStatus(arrivals.getStatus());
        flightTO.setGate(arrivals.getGate());

        return flightTO;
    }

    private FlightTO convertFlight(Departures departures){
        FlightTO flightTO = new FlightTO();

        flightTO.setId((departures.getFlight().getId()));
        flightTO.setNumber(departures.getFlight().getNumber());
        flightTO.setDate(departures.getDate());
        flightTO.setCity(departures.getCity());
        flightTO.setTerminal(departures.getTerminal());
        flightTO.setStatus(departures.getStatus());
        flightTO.setGate(departures.getGate());

        return flightTO;
    }
}
