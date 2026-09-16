package lab5.view;

import lab3.model.Shape;

public class ShapeView {

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printShapes(Shape[] shapes, String title) {
        System.out.println("\n--- " + title + " ---");
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }

    public void printTotalArea(String description, double area) {
        System.out.printf("%s: %.2f%n", description, area);
    }
}