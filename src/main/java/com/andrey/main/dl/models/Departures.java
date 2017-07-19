package com.andrey.main.dl.models;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Departures extends Destination implements Serializable {

    @Override
    public String toString() {
        return "Departures{ " + super.toString() + " }";
    }
}

