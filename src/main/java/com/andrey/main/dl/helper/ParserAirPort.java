package com.andrey.main.dl.helper;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.DateTimeException;
import java.time.ZoneOffset;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserAirPort {
    public static void main(String[] args) {

        String FILE_NAME = "D:\\airport\\test.csv";

        List<Airport> airportList = new ArrayList<>();

//        airportList = convertFileToAirport(FILE_NAME);

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        for (Airport airport : airportList) {
//            System.out.println(airport);
//        }
        System.out.println("airportList.size() = " + airportList.size());

        String pattern = ".*[_\\s-]$";
//        String pattern = "^[a-zA-Z_\\s]+";
//        String pattern = "(^[a-zA-Z])([a-zA-Z_\\s-]+)";
//        String pattern = "([_\\s-])$";
        System.out.println(pattern);
        Pattern p = Pattern.compile(pattern);
        String sd = "as  s";
        Matcher m = p.matcher(sd);
        System.out.println(m.matches());

//        System.out.println(sd.matches(pattern));

    }

    public static List<Airport> convertFileToAirport(String FILE_NAME) {
        List<Airport> airportList = new ArrayList<>();
        try {
            BufferedReader inputStream;

            inputStream = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            int count = 0;
            while ((line = inputStream.readLine()) != null) {
                Airport airport = parseAirport(line);
                if (airport != null) {
                    airportList.add(airport);
                }
                count++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return airportList;
    }

    public static Airport parseAirport(String string) {
        String[] arrayParam = string.split(";");
        Airport airport = null;

        if (arrayParam.length == Airport.class.getDeclaredFields().length) {

            try {
                String iataCode = arrayParam[0];
                String city = arrayParam[1];
                String country = arrayParam[2];
                ZoneOffset gtmOffset = parseGTMOffset(arrayParam[3]);
                Double latitude = parseCoordinates(arrayParam[4]);
                Double longitude = parseCoordinates(arrayParam[5]);

                airport = new Airport(iataCode, city, country, gtmOffset, latitude, longitude);
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            try {
                throw new InvalidAirportStringException("This line: '" + string + "' has incorrect number of parameters");
            } catch (InvalidAirportStringException e) {
                e.printStackTrace();
            }
        }

        return airport;
    }

    public static ZoneOffset parseGTMOffset(String string) {

        ZoneOffset GTMOffset = null;
        try {
            double value = Double.parseDouble(string);
            int hours = (int) value;
            int minute = (int) ((value - hours) * 60);
            GTMOffset = ZoneOffset.ofHoursMinutes(hours, minute);
        } catch (DateTimeException e) {
            e.printStackTrace();
        }

        return GTMOffset;
    }

    public static double parseCoordinates(String string) {
        Double value = null;

        try {
            value = Double.parseDouble(string);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
            System.out.println("Invalid Coordinates");
        }

        return value;
    }

    public static boolean isWord(String string){
        String pattern = "(^[a-zA-Z])([a-zA-Z_\\s-]+)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(string);

        return m.matches();
    }

    public static List readFromEcxel(String excelFilePath) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        List listBook = new ArrayList();
        while (iterator.hasNext()) {

            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            List listRow = new ArrayList();
            listRow.add(excelFilePath);
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
//                for (int i = 1; i < 3; i++) {
//                if (cell.getColumnIndex() > 0 && cell.getColumnIndex() < 3){
//                    System.out.print(cell);
                listRow.add(cell);
//                    System.out.print(" - ");
//                }
            }
            listBook.add(listRow);
//            System.out.println();
        }

        workbook.close();
        inputStream.close();
        return listBook;
    }
}
