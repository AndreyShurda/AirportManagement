package com.andrey.main.ui;


import com.andrey.main.bl.access.PermissionAction;
import com.andrey.main.dl.dao.*;
import com.andrey.main.dl.models.UserEntity;

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
//        ticket.setFlight(flightDAOById);
//        ticketDAO.update(ticket);
//
//        final Flight[] flight = new Flight[1];
//        HibernateDBUtil.operationCRUD(session -> {
//            flight[0] = session.get(Flight.class, 2);
//        });
//        System.out.println(flight[0]);
//        ticket.setFlight(flight[0]);
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
//        ticketDAOById.setFlight(flightDAOById);
//        ticketDAO.update(ticketDAOById);
//        System.out.println(passengerDAOById);
//        System.out.println(flightDAOById);
//        passengerDAOById.setFlight(flightDAOById);
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
//        UserEntityDAO userEntityDAO = UserEntityDAO.getInstance();
//        UserEntity user = new UserEntity();
//        user.setName("adriano");
//        try {
//            user.setPassword(EncryptionDecryptionAES.encryptRSA("32167"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        user.setPermissionAction(PermissionAction.STAFF);
//        userEntityDAO.add(user);
//        UserEntity userEntity1 = userEntityDAO.getById(10);
//        System.out.println(userEntity1);
//        try {
//            System.out.println(EncryptionDecryptionAES.decryptRSA(userEntity1.getPassword()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        UserEntity userEntity2 = userEntityDAO.getById(2);
//        System.out.println(userEntity1.equals(userEntity2));

//        System.out.println(userEntityDAO.getPermissions(user));

        HibernateDBUtil.shutdownConnection();

        String key = "private_key";
        String private_key = ApplicationProperties.readProperty(key);
        System.out.println(private_key);
        if (private_key == null){
            ApplicationProperties.writeProperty(key, EncryptionDecryptionAES.privateKey.toString());
        }

//        System.out.println("as".matches("(^[a-zA-Z]{1})([a-zA-Z_]*)([a-zA-Z]$)"));
//        System.out.println("AES");
//        try {
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//            keyGenerator.init(128);
//            SecretKey secretKey = keyGenerator.generateKey();
//            String password = "A32167A";
//            byte[] data = password.getBytes();
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//            byte[] encryptData = cipher.doFinal(data);
//            String encryptString = new String(encryptData);
//            System.out.println(encryptString);
//
//            cipher.init(Cipher.DECRYPT_MODE, secretKey);
//            byte[] decryptData = cipher.doFinal(encryptData);
//            System.out.println(new String(decryptData));
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("RSA");
//        try {
//            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
//            kpg.initialize(1024);
//            KeyPair kp = kpg.generateKeyPair();
//            PublicKey publicKey = kp.getPublic();
//            PrivateKey privateKey = kp.getPrivate();
//
//            String password = "A32167A";
//            byte[] data = password.getBytes();
//            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//            byte[] encryptData = cipher.doFinal(data);
//            System.out.println(new String(encryptData));
//
//            cipher.init(Cipher.DECRYPT_MODE, privateKey);
//            byte[] decryptData = cipher.doFinal(encryptData);
//            System.out.println(new String(decryptData));
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        }

    }
}

