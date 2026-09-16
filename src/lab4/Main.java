package lab4;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. Демонстрація посадки пасажирів ===");


        Taxi taxi = new Taxi(2);
        Human passenger = new Human("Олег");
        Firefighter firefighter = new Firefighter("Сергій");

        taxi.boardPassenger(passenger);
        taxi.boardPassenger(firefighter);
        System.out.printf("Таксі: зайнято %d з %d місць%n", taxi.getOccupiedSeats(), taxi.getMaxSeats());


        PoliceCar policeCar = new PoliceCar(2);
        PoliceOfficer cop = new PoliceOfficer("Андрій");
        policeCar.boardPassenger(cop);

        System.out.printf("Поліцейська машина: зайнято %d з %d місць%n", policeCar.getOccupiedSeats(), policeCar.getMaxSeats());

        FireTruck fireTruck = new FireTruck(4);
        fireTruck.boardPassenger(new Firefighter("Іван"));
        System.out.printf("Пожежна машина: зайнято %d з %d місць%n", fireTruck.getOccupiedSeats(), fireTruck.getMaxSeats());

        Bus bus = new Bus(20);
        bus.boardPassenger(new Human("Марія"));
        bus.boardPassenger(new PoliceOfficer("Богдан"));
        System.out.printf("Автобус: зайнято %d з %d місць%n", bus.getOccupiedSeats(), bus.getMaxSeats());

        System.out.println("\n=== 2. Демонстрація виняткових ситуацій ===");


        try {
            System.out.println("Спроба посадити зайвого пасажира в таксі...");
            taxi.boardPassenger(new Human("Зайвий пасажир"));
        } catch (IllegalStateException e) {
            System.out.println("Перехоплено виняток: " + e.getMessage());
        }


        try {
            System.out.println("Спроба висадити пасажира, якого немає...");
            taxi.dropPassenger(new Human("Невідомий"));
        } catch (IllegalArgumentException e) {
            System.out.println("Перехоплено виняток: " + e.getMessage());
        }

        System.out.println("\n=== 3. Підрахунок людей на дорозі (Road) ===");

        Road road = new Road();
        road.addCarToRoad(taxi);
        road.addCarToRoad(policeCar);
        road.addCarToRoad(fireTruck);
        road.addCarToRoad(bus);

        System.out.println("Загальна кількість людей на ділянці дороги: " + road.getCountOfHumans());
    }
}