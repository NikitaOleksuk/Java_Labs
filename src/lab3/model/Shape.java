package lab3.model;

public abstract class Shape implements Drawable {
    private final String shapeColor;

    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public String getShapeColor() {
        return shapeColor;
    }

    public abstract double calcArea();

    @Override
    public void draw() {
        System.out.println("Малюємо фігуру: " + toString());
    }

    @Override
    public String toString() {
        return String.format("Колір='%s', Площа=%.2f", shapeColor, calcArea());
    }
}