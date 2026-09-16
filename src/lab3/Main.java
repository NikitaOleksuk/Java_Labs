package lab3;

import lab3.controller.ShapeController;
import lab3.view.ShapeView;

public class Main {
    public static void main(String[] args) {
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(view);
        controller.execute();
    }
}
