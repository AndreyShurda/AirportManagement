package com.andrey.main.dl.dao;

import com.andrey.main.dl.data.ClassType;
import com.andrey.main.dl.data.Gender;
import com.andrey.main.dl.models.Passenger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.andrey.main.dl.dao.DBUtil.*;
import static com.andrey.main.dl.dao.InitialData.TABLE_PASSENGERS;

public class PassengerDAO implements OperationDAO<Passenger> {
    private static PassengerDAO instance = new PassengerDAO();
//    private static final String TABLE_PASSENGERS = "passengers";

    private PassengerDAO() {
    }

    public static PassengerDAO getInstance() {
        return instance;
    }


    @Override
    public void add(Passenger record) {
        String query = "insert into " + TABLE_PASSENGERS + " (idFlight, firstName, lastName, nationality," +
                " passport, birthday, gender, classType) values(?,?,?,?,?,?,?,?)";
        doQuery(record, query);
    }

    @Override
    public void delete(Passenger record) {
        createConnection();
        String query = "delete from " + TABLE_PASSENGERS + " WHERE idPassenger =?";
        try {
            executeQuery(query, String.valueOf(record.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void update(Passenger record) {
        String query = "UPDATE " + TABLE_PASSENGERS + " SET idFlight=?, firstName=?, lastName=?, nationality=?," +
                "passport=?, birthday=?, gender=?, classType=? WHERE  idPassenger =" + record.getId();
        doQuery(record, query);
    }

    @Override
    public List<Passenger> getAll() {
        String query = "select idPassenger, idFlight, firstName, lastName, nationality, passport, birthday, gender, classType " +
                "from " + TABLE_PASSENGERS;
        return getPassengers(query);
    }

    public List<Passenger> searchByFirstName(String text) {
        return searchByColumn("firstName", text);
    }


    public List<Passenger> searchByLastName(String text) {
        return searchByColumn("lastName", text);
    }

    public List<Passenger> searchByPassport(String text) {
        return searchByColumn("passport", text);
    }

    private List<Passenger> searchByColumn(String columnName, String value) {
        String query = "select idPassenger, idFlight, firstName, lastName, nationality, passport, birthday, gender, classType " +
                "from " + TABLE_PASSENGERS +
                " where " + columnName + " like '" + value + "%'";
        return getPassengers(query);
    }

    private List<Passenger> getPassengers(String query) {
        createConnection();
        List<Passenger> passengers = new ArrayList<>();
//        String query = "select idPassenger, idFlight, firstName, lastName, nationality, passport, birthday, gender, classType " +
//                "from " + TABLE_PASSENGERS;

        try {
            ResultSet resultSet = executeQuery(query);

//            ResultSetMetaData metaData = resultSet.getMetaData();
//            for (int i = 1; i <= metaData.getColumnCount(); i++) {
//                System.out.print(metaData.getColumnName(i) + "\t");
//            }
//            System.out.println();

            while (resultSet.next()) {
                Integer idPassenger = Integer.valueOf(resultSet.getString("idPassenger"));
                String numberFlight = resultSet.getString("idFlight");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String nationality = resultSet.getString("nationality");
                String passport = resultSet.getString("passport");
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
                Gender gender = Gender.values()[resultSet.getInt("gender")];
                ClassType classType = ClassType.values()[resultSet.getInt("classType")];

//                for (int i = 0; i < metaData.getColumnCount(); i++) {
//                    System.out.print(resultSet.getString(i + 1) + "\t");
//                }
//                System.out.println();
                passengers.add(new Passenger(idPassenger,
                        numberFlight,
                        firstName,
                        lastName,
                        nationality,
                        passport,
                        birthday,
                        gender,
                        classType)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return passengers;
    }

    private void doQuery(Passenger record, String query) {
        createConnection();
        try {
            executeQuery(query,
                    record.getFlightNumber(),
                    record.getFirstName(),
                    record.getLastName(),
                    record.getNationality(),
                    record.getPassport(),
                    record.getBirthday().toString(),
                    String.valueOf(record.getGender().ordinal()),
                    String.valueOf(record.getClassType().ordinal()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

}
