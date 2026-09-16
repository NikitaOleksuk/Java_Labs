package lab4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void testBoardingAndDroppingPassengers() {
        Taxi taxi = new Taxi(2);
        Human person = new Human("Тарас");
        Firefighter firefighter = new Firefighter("Іван");

        taxi.boardPassenger(person);
        taxi.boardPassenger(firefighter); // Таксі приймає і пожежника, бо він є Human
        assertEquals(2, taxi.getOccupiedSeats());


        assertThrows(IllegalStateException.class, () -> taxi.boardPassenger(new Human("Олег")));


        taxi.dropPassenger(person);
        assertEquals(1, taxi.getOccupiedSeats());


        assertThrows(IllegalArgumentException.class, () -> taxi.dropPassenger(person));
    }

    @Test
    void testPoliceCarAcceptsOnlyPolice() {
        PoliceCar policeCar = new PoliceCar(2);
        PoliceOfficer cop = new PoliceOfficer("Петро");

        policeCar.boardPassenger(cop);
        assertEquals(1, policeCar.getOccupiedSeats());

    }

    @Test
    void testRoadCountingHumans() {
        Road road = new Road();

        Bus bus = new Bus(30);
        bus.boardPassenger(new Human("Пасажир 1"));
        bus.boardPassenger(new PoliceOfficer("Поліцейський 1"));

        FireTruck fireTruck = new FireTruck(4);
        fireTruck.boardPassenger(new Firefighter("Пожежник 1"));

        road.addCarToRoad(bus);
        road.addCarToRoad(fireTruck);


        assertEquals(3, road.getCountOfHumans());
    }
}