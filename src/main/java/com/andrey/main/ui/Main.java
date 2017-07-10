package com.andrey.main.ui;

import com.andrey.main.dl.models.Flight;
import com.andrey.main.dl.models.Passenger;
import com.andrey.main.dl.models.Schedule;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.andrey.main.dl.data.Gender.*;
import static com.andrey.main.dl.data.ClassType.*;
import static java.time.DayOfWeek.*;


public class Main {
    public static void main(String[] args) {
        List<Passenger> passengersList = new ArrayList<>();
//        passengersList.add(new Passenger("01", "Andrey", "Shurda", "ua", "SM1233AM", LocalDate.of(1989, 8, 22), MAN, ECONOM));
//        passengersList.add(new Passenger("02", "Andrey", "Shurda", "ua", "SM1233AM", LocalDate.of(1989, 8, 22), MAN, ECONOM));
//        passengersList.add(new Passenger("03", "Andrey", "Shurda", "ua", "SM1233AM", LocalDate.of(1989, 8, 22), MAN, ECONOM));
//        passengersList.add(new Passenger("03", "Ivan", "Ivanov", "ru", "SM123478", LocalDate.of(1988, 6, 12), MAN, BUSINESS));
//        passengersList.add(new Passenger("03", "Rita", "Ivanova", "ru", "SM123445", LocalDate.of(2000, 4, 11), WOMAN, BUSINESS));

        printList(passengersList);

        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(new Schedule("01", Arrays.asList(MONDAY, WEDNESDAY, SUNDAY), LocalTime.of(12, 15)));
        scheduleList.add(new Schedule("02", Arrays.asList(MONDAY, TUESDAY), LocalTime.of(22, 00)));
        scheduleList.add(new Schedule("03", Arrays.asList(TUESDAY, THURSDAY, SUNDAY), LocalTime.of(15, 25)));

        printList(scheduleList);

        List<Flight> arrivalList = new ArrayList<>();

        System.out.println(scheduleList.get(1).getDays().get(1));

        LocalDateTime dateTime = LocalDateTime.of(2017, 05, 03, 13, 15);
//        arrivalList.add(new Flight("01", dateTime, "Kiev", "London", 'F', CHECK_IN, "A02"));

        printList(arrivalList);


        ArrayList<String> zoneIds = new ArrayList<>(ZoneId.getAvailableZoneIds());
//        printList(zoneIds);
        String zone = zoneIds.get(50);
        System.out.println(zone);
        System.out.println("\n" + dateTime.atZone(ZoneId.of(zone)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("\n" + dateTime.format(formatter));


        LocalDateTime currentTimeInLosAngeles = LocalDateTime.now(ZoneId.of("America/Los_Angeles"));

// current time in UTC time zone
        LocalTime nowInUtc = LocalTime.now(Clock.systemUTC());

        System.out.println(currentTimeInLosAngeles);
        System.out.println(nowInUtc);
        System.out.println();
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.ofHours(16)));
        System.out.println(LocalDateTime.now());
        System.out.println(dateTime.atZone(ZoneId.of("America/Los_Angeles")));
    }

    static <T> void printList(Collection<T> list) {
        list.forEach(System.out::println);
        System.out.println();
    }
}
