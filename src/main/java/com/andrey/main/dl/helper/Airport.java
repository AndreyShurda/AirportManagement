package com.andrey.main.dl.helper;

import java.time.ZoneOffset;

public class Airport {
    private String IATACode;
    private String city;
    private String country;
    private ZoneOffset GTMOffset;
    private Double latitude;
    private Double longitude;

    public Airport(String IATACode, String city, String country, ZoneOffset GTMOffset, Double latitude, Double longitude) {
        this.IATACode = IATACode;
        this.city = city;
        this.country = country;
        this.GTMOffset = GTMOffset;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getIATACode() {
        return IATACode;
    }

    public void setIATACode(String IATACode) {
        this.IATACode = IATACode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ZoneOffset getGTMOffset() {
        return GTMOffset;
    }

    public void setGTMOffset(ZoneOffset GTMOffset) {
        this.GTMOffset = GTMOffset;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "IATACode='" + IATACode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", GTMOffset=" + GTMOffset +
                ", latitude=" + latitude +
                ", longitude=" + longitude;
    }
}
