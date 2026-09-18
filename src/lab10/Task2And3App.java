package lab10;

import lab5.ShapeFileManager;
import lab5.model.Rectangle;
import lab5.model.Shape;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.*;

public class Task2And3App {
    private static final Logger logger = Logger.getLogger(Task2And3App.class.getName());

    static {
        try {
            logger.setUseParentHandlers(false);
            logger.setLevel(Level.ALL);


            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            consoleHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(consoleHandler);


            FileHandler fileHandler = new FileHandler("app.log", false);
            fileHandler.setLevel(Level.FINE);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

        } catch (IOException e) {
            System.err.println("Не вдалося ініціалізувати логер: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        logger.fine("Ініціалізація програми (рівень FINE - тільки файл)");

        Scanner scanner = new Scanner(System.in);
        ShapeFileManager fileManager = new ShapeFileManager();
        I18nManager i18n = new I18nManager(Locale.forLanguageTag("uk"));

        Shape[] shapes = new Shape[] {
                new Rectangle("Червоний", 4.0, 5.0),
                new Rectangle("Синій", 2.0, 6.0)
        };

        logger.info("Програму запущено. Поточна мова: " + i18n.getCurrentLocale());

        while (true) {
            System.out.println("\n" + i18n.get("menu.title"));
            System.out.println(i18n.get("menu.view"));
            System.out.println(i18n.get("menu.save"));
            System.out.println(i18n.get("menu.load"));
            System.out.println(i18n.get("menu.lang"));
            System.out.println(i18n.get("menu.exit"));
            System.out.print(i18n.get("menu.choose"));

            String choice = scanner.nextLine().trim();
            logger.fine("Користувач обрав пункт меню: " + choice);

            if ("0".equals(choice)) {
                logger.info("Роботу завершено користувачем.");
                break;
            }

            switch (choice) {
                case "1" -> {
                    for (Shape shape : shapes) {
                        System.out.println(shape);
                    }
                }
                case "2" -> {
                    System.out.print(i18n.get("input.file.save"));
                    String path = scanner.nextLine().trim();
                    try {
                        fileManager.saveShapesToFile(shapes, path);
                        System.out.println(i18n.get("msg.saved"));
                        logger.info("Успішно збережено об'єкти у файл: " + path);
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                        logger.log(Level.SEVERE, "Критична помилка запису у файл: " + path, e);
                    }
                }
                case "3" -> {
                    System.out.print(i18n.get("input.file.load"));
                    String path = scanner.nextLine().trim();
                    try {
                        shapes = fileManager.loadShapesFromFile(path);
                        System.out.println(i18n.get("msg.loaded"));
                        for (Shape shape : shapes) {
                            System.out.println(shape);
                        }
                        logger.info("Успішно прочитано об'єкти з файлу: " + path);
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                        logger.log(Level.WARNING, "Помилка читання файлу: " + path, e);
                    }
                }
                case "4" -> {
                    System.out.println("1. Українська (uk)");
                    System.out.println("2. English (en)");
                    System.out.print("Оберіть / Select: ");
                    String langChoice = scanner.nextLine().trim();
                    if ("1".equals(langChoice)) {
                        i18n.setLocale(Locale.forLanguageTag("uk"));
                    } else if ("2".equals(langChoice)) {
                        i18n.setLocale(Locale.forLanguageTag("en"));
                    }
                    System.out.println(i18n.get("msg.lang.changed"));
                    logger.info("Змінено локаль на: " + i18n.getCurrentLocale());
                }
                default -> {
                    System.out.println(i18n.get("msg.invalid"));
                    logger.warning("Некоректний вибір у меню: " + choice);
                }
            }
        }
    }
}