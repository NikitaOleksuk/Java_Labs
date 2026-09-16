package lab3.controller;

import lab3.model.Circle;
import lab3.model.Rectangle;
import lab3.model.Shape;
import lab3.model.Triangle;
import lab3.view.ShapeView;

import java.util.Arrays;
import java.util.Comparator;

public class ShapeController {
    private Shape[] shapes;
    private final ShapeView view;

    public ShapeController(ShapeView view) {
        this.view = view;
        initData();
    }

    // Підготовлений набір даних (10 фігур)
    private void initData() {
        shapes = new Shape[] {
                new Rectangle("Червоний", 4.0, 5.0),
                new Circle("Синій", 3.0),
                new Triangle("Зелений", 6.0, 2.5),
                new Rectangle("Жовтий", 2.0, 3.0),
                new Circle("Червоний", 1.5),
                new Triangle("Синій", 4.0, 4.0),
                new Rectangle("Білий", 7.0, 2.0),
                new Circle("Зелений", 5.0),
                new Triangle("Жовтий", 3.0, 8.0),
                new Rectangle("Чорний", 3.5, 4.5)
        };
    }

    public void execute() {
        // 1. Відображення початкового набору даних
        view.printShapes(shapes, "Початковий набір фігур");

        // 2. Сумарна площа всіх фігур
        double totalArea = calculateTotalArea();
        view.printTotalArea("\nСумарна площа всіх фігур", totalArea);

        // 3. Сумарна площа фігур заданого виду (наприклад, Circle і Rectangle)
        double circlesArea = calculateAreaByType(Circle.class);
        view.printTotalArea("Сумарна площа всіх кіл (Circle)", circlesArea);

        double rectanglesArea = calculateAreaByType(Rectangle.class);
        view.printTotalArea("Сумарна площа всіх прямокутників (Rectangle)", rectanglesArea);

        // 4. Сортування за площею (Comparator)
        Arrays.sort(shapes, Comparator.comparingDouble(Shape::calcArea));
        view.printShapes(shapes, "Фігури, відсортовані за збільшенням площі");

        // 5. Сортування за кольором (Comparator)
        Arrays.sort(shapes, Comparator.comparing(Shape::getShapeColor));
        view.printShapes(shapes, "Фігури, відсортовані за кольором");
    }

    private double calculateTotalArea() {
        double total = 0.0;
        for (Shape shape : shapes) {
            total += shape.calcArea();
        }
        return total;
    }

    private double calculateAreaByType(Class<? extends Shape> shapeType) {
        double sum = 0.0;
        for (Shape shape : shapes) {
            if (shapeType.isInstance(shape)) {
                sum += shape.calcArea();
            }
        }
        return sum;
    }
}