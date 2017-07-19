package com.andrey.main.ui;


import com.andrey.main.dl.dao.*;
import com.andrey.main.dl.models.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class Test {
    public static void main(String[] args) {
//        HibernateDBUtil.getFactory();

//        SessionFactory factory = new Configuration().configure().buildSessionFactory();
//        HibernateDBUtil.setFactory(factory);
        TicketDAO ticketDAO = TicketDAO.getInstance();

        FlightDAO flightDAO = FlightDAO.getInstance();
        PassengerDAO passengerDAO = PassengerDAO.getInstance();
//        Flight flight = new Flight("PS1000", LocalDateTime.now(), "Kiev", 'A', FlightStatus.CHECK_IN, "4A");
//        flightDAO.add(flight);
//        Ticket ticket = new Ticket();
//        ticket.setIdTicket(1);
//        ticket.setClassType(ClassType.BUSINESS);
//        ticket.setPrice(1560);
//        Flight flightDAOById = flightDAO.getById(2);
//        ticket.setDestination(flightDAOById);
//        ticketDAO.update(ticket);
//
//        final Flight[] flight = new Flight[1];
//        HibernateDBUtil.operationCRUD(session -> {
//            flight[0] = session.get(Flight.class, 2);
//        });
//        System.out.println(flight[0]);
//        ticket.setDestination(flight[0]);
//        ticketDAO.update(ticket);


//        Passenger passenger = new Passenger();
//        passenger.setFirstName("Andrey");
//        passenger.setLastName("Shurda");
//        passenger.setBirthday(LocalDate.of(1989, 07, 22));
//        passenger.setNationality("Ukraine");
//        passenger.setPassport("PS1231AA");
//        passenger.setGender(Gender.MAN);
//        passenger.setClassType(ClassType.BUSINESS);
//
//        passengerDAO.add(passenger);

//        Passenger passengerDAOById = passengerDAO.getById(1);
//        Flight flightDAOById = flightDAO.getById(2);
//        Ticket ticketDAOById = ticketDAO.getById(1);
//        ticketDAOById.setDestination(flightDAOById);
//        ticketDAO.update(ticketDAOById);
//        System.out.println(passengerDAOById);
//        System.out.println(flightDAOById);
//        passengerDAOById.setDestination(flightDAOById);
//        passengerDAO.update(passengerDAOById);

//        List<Ticket> ticketList = ticketDAO.getAll();
//        System.out.println(ticketList);
//        List<Flight> flights = flightDAO.getAll();
//        System.out.println(flights);
//        List<Passenger> passengers = passengerDAO.getAll();
//        System.out.println(passengers);
//
//        HibernateDBUtil.shutdownConnection();

//        String pattern = ".*[_\\s-]$";
//        String pattern = "^[a-zA-Z_\\s]+";
////        String pattern = "(^[a-zA-Z])([a-zA-Z_\\s-]+)";
////        String pattern = "([_\\s-])$";
//        System.out.println(pattern);
//        Pattern p = Pattern.compile(pattern);
//        String sd = "as  s";
//        Matcher m = p.matcher(sd);
//        System.out.println(m.matches());

//        System.out.println(ValidationData.isFlightNumber("PS123"));
//        System.out.println(ValidationData.isFlightNumber("P123"));
//        System.out.println(ValidationData.isFlightNumber("Ps123255"));
//        ApplicationProperties.GetPath getPath = new ApplicationProperties.GetPath();
//        getPath.getAbsolutePathFile("/fxml/main.fxml");
//        getPath.getAbsolutePathFile("/configuration/db.properties");
//
//
        UserEntityDAO userEntityDAO = UserEntityDAO.getInstance();
//        UserEntity user = new UserEntity();
//        user.setName("Administrator");
//        String str = "onairda32167";
//        user.setPassword(Password.encode(str));
//        user.setPermissionAction(PermissionAction.ADMIN);
//        userEntityDAO.add(user);
//        UserEntity userEntity1 = userEntityDAO.getById(11);
//        System.out.println(userEntity1);
//
//        System.out.println(CryptoUtils.decode(userEntity1.getPassword()));

//        UserEntity userEntity2 = userEntityDAO.getById(2);
//        System.out.println(userEntity1.equals(userEntity2));

//        System.out.println(userEntityDAO.getPermissions(user));

        ArrivalsDAO arrivalsDAO = ArrivalsDAO.getInstance();
        DeparturesDAO departuresDAO = DeparturesDAO.getInstance();

        DestinationDAO destinationDAO = DestinationDAO.getInstance();

        Flight flight = new Flight();
        flight.setNumber("DD555AA");
//        flightDAO.add(flight);

//        Arrivals arrivals = new Arrivals();
//        Destination arrivals = new Departures();
//        Destination arrivals = new Destination();
//        arrivals.setDate(LocalDateTime.now());
//        arrivals.setCity("Piter");
//        arrivals.setTerminal('A');
//        arrivals.setStatus(FlightStatus.DELAYED);
//        arrivals.setGate("11");
//        arrivals.setFlight(flightDAO.getById(1));

//        destinationDAO.add(arrivals);


//        Arrivals byId = (Arrivals) destinationDAO.getById(1);
//        Departures byIdD = (Departures) destinationDAO.getById(1);
//        System.out.println(byId);
//        System.out.println(byIdD);

//        Destination byId = destinationDAO.getById(Departures.class, 1);
//        byId.setCity("Vena");
//        destinationDAO.update(byId);
//        destinationDAO.delete(byId);
        List<Destination> all = destinationDAO.getAll();
//        System.out.println(all);

//        Departures arrivals = departuresDAO.getById(1);

//        arrivals.setCity("London");

//        System.out.println(arrivals);

//        arrivalsDAO.add(arrivals);
//        departuresDAO.update(arrivals);
        List<Arrivals> arrivalsList = arrivalsDAO.getAll();

        List<Flight> flightList = flightDAO.getAll();


//        final List<Ticket>[] tickets = new List<Ticket>[0];
        HibernateDBUtil.operationCRUD(session -> {
//            Double minWeight = 100.0;
//            Double maxWeight = 600.0;
//            List<Ticket> tickets = session.createCriteria(Ticket.class)
//                    .add(Restrictions.between("price", minWeight, maxWeight))
//                    .list();
//
//            System.out.println(tickets);
            List<Destination> list = session.createCriteria(Arrivals.class)
                    .createAlias("flight", "flight")
                    .add(Restrictions.like("flight.number", "a%"))
                    .list();

            for (Destination destination : list) {
                System.out.println(destination);

            }

//            session.createCriteria(Flight.class)
//                    .createAlias("flight", )
        });

        System.out.println("--------------");
//        destinationDAO.setTABLE(InitialData.TABLE_ARRIVALS);
//        System.out.println(destinationDAO.searchByCity(Arrivals.class, "k"));
//        System.out.println("+++++++++++++");
//        destinationDAO.setTABLE(InitialData.TABLE_DEPARTURES);
//        System.out.println(destinationDAO.searchByCity(Departures.class, "k"));
//
//        System.out.println("11111111111111");
//        System.out.println(destinationDAO.searchByNumberArrivals("w"));

//        System.out.println(destinationDAO.searchByNumberTest("a"));
        HibernateDBUtil.shutdownConnection();

//        System.out.println("as".matches("(^[a-zA-Z]{1})([a-zA-Z_]*)([a-zA-Z]$)"));


    }
}

