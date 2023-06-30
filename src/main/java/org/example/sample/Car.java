package org.example.sample;

import java.util.Set;

public class Car {
    private Man man;

    private Set<Car> carSet;

    public Car(Man man) {
        this.man = man;
    }

    public Man getMan() {
        return man;
    }

    public void setMan(Man man) {
        this.man = man;
    }

    public Set<Car> getCarSet() {
        return carSet;
    }

    public void setCarSet(Set<Car> carSet) {
        this.carSet = carSet;
    }
}
