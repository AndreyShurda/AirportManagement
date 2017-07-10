package com.andrey.main.ui;


import com.andrey.main.dl.data.FlightStatus;
import com.andrey.main.dl.models.Flight;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Excel {
    public static void main(String[] args) {
        writeToExcel();
        Class<Flight> flightClass = Flight.class;
        for (Field field : flightClass.getDeclaredFields()) {
            System.out.println(field.getName());
        }
        for (Method method : flightClass.getDeclaredMethods()) {
            if (method.getName().startsWith("get")) {
                System.out.println(method.getName());
            }
        }
    }

    private static void writeToExcel() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");

        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("PS123", LocalDateTime.now(), "Kiev", 'A', FlightStatus.CHECK_IN, "12a"));
        flights.add(new Flight("PS111", LocalDateTime.now(), "London", 'A', FlightStatus.CHECK_IN, "12a"));


        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(HSSFColor.RED.index);
//        font.setBold(true);
//        font.setFontHeight((short) 18);
        style.setFont(font);


        int rowCount = 0;
        Row rows = sheet.createRow(rowCount);
        Cell cell = rows.createCell(1);
        cell.setCellValue("Number");
        cell.setCellStyle(style);
        rows.createCell(2).setCellValue("Date");
        rows.createCell(3).setCellValue("City");
        rows.createCell(4).setCellValue("Terminal");
        rows.createCell(5).setCellValue("Status");
        rows.createCell(6).setCellValue("Gate");

        for (Flight flight : flights) {
            Row row = sheet.createRow(++rowCount);

            row.createCell(1).setCellValue(flight.getNumber());
            row.createCell(2).setCellValue(flight.getDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            row.createCell(3).setCellValue(String.valueOf(flight.getCity()));
            row.createCell(4).setCellValue(String.valueOf(flight.getTerminal()));
            row.createCell(5).setCellValue(String.valueOf(flight.getStatus()));
            row.createCell(6).setCellValue(String.valueOf(flight.getGate()));
        }


        try (FileOutputStream outputStream = new FileOutputStream("JavaBooks.xlsx")) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List readFromEcxel(String excelFilePath) throws IOException {
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
