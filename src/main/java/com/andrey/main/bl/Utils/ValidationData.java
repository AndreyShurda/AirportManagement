package com.andrey.main.bl.Utils;

public class ValidationData {

    public static boolean isFlightNumber(String s){
        return s.matches("(^[a-zA-Z]{2}).*\\d");
    }

}
