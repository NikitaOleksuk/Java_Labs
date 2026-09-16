package lab5;

import lab5.model.Rectangle;
import lab5.model.Shape;

import java.util.Scanner;

public class Task2App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShapeFileManager fileManager = new ShapeFileManager();

        Shape[] shapes = new Shape[] {
                new Rectangle("Червоний", 4.0, 5.0),
                new Rectangle("Синій", 2.0, 6.0)
        };

        while (true) {
            System.out.println("\n--- Меню роботи з файлами ---");
            System.out.println("1. Переглянути поточний набір об'єктів");
            System.out.println("2. Зберегти набір об'єктів у файл");
            System.out.println("3. Зчитати набір об'єктів із файлу");
            System.out.println("0. Вихід");
            System.out.print("Виберіть опцію: ");

            String choice = scanner.nextLine().trim();

            if ("0".equals(choice)) break;

            switch (choice) {
                case "1" -> {
                    for (Shape shape : shapes) {
                        System.out.println(shape);
                    }
                }
                case "2" -> {
                    System.out.print("Введіть шлях та назву файлу для збереження (напр., shapes.dat): ");
                    String path = scanner.nextLine().trim();
                    try {
                        fileManager.saveShapesToFile(shapes, path);
                        System.out.println("Набір об'єктів успішно серіалізовано у файл!");
                    } catch (Exception e) {
                        System.err.println("Помилка під час збереження: " + e.getMessage());
                    }
                }
                case "3" -> {
                    System.out.print("Введіть шлях та назву файлу для читання: ");
                    String path = scanner.nextLine().trim();
                    try {
                        shapes = fileManager.loadShapesFromFile(path);
                        System.out.println("Об'єкти успішно десеріалізовано з файлу!");
                        for (Shape shape : shapes) {
                            System.out.println(shape);
                        }
                    } catch (Exception e) {
                        System.err.println("Помилка під час завантаження: " + e.getMessage());
                    }
                }
                default -> System.out.println("Невірна команда!");
            }
        }
    }
}
