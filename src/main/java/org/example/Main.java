package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.example.sample.Car;
import org.example.sample.Man;

public class Main {

    public static void main(String[] args) {
        List<String> originalBooks = new ArrayList<>();
        Man originalMan = new Man("Jane Doe", 25, originalBooks);
        Car originalCar = new Car(originalMan);
        Set<Car> carSet = new HashSet<>();
        carSet.add(originalCar);
        originalCar.setCarSet(carSet);

        Car copiedCar = CopyUtils.deepCopy(originalCar);

        System.out.println(!copiedCar.equals(originalCar));
        System.out.println(copiedCar.getMan().getName()
                .equals(originalCar.getMan().getName()));
        System.out.println(copiedCar.getMan()
                .equals(copiedCar.getCarSet().stream().findFirst().get().getMan()));
    }
}
