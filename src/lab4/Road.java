package lab4;

import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Vehicle<?>> carsInRoad = new ArrayList<>();

    public int getCountOfHumans() {
        int total = 0;
        for (Vehicle<?> vehicle : carsInRoad) {
            total += vehicle.getOccupiedSeats();
        }
        return total;
    }

    public void addCarToRoad(Vehicle<?> vehicle) {
        carsInRoad.add(vehicle);
    }
}
