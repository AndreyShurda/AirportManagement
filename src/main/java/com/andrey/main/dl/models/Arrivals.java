package com.andrey.main.dl.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Arrivals extends Destination implements Serializable {
    @Override
    public String toString() {
        return "Arrivals{ " + super.toString() + " }";
    }
}
