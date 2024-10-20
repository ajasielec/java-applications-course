import java.awt.print.Printable;

public class Circle extends Figure implements Printing {
    double radius;
    double area;
    double perimeter;

    public Circle(double radius) {
        if (radius < 0){
            System.out.println("Radius cannot be negative");
        }
        else{
            this.radius = radius;
            this.area = calculateArea();
            this.perimeter = calculatePerimeter();
        }
    }

    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }
    @Override
    double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
    @Override
    public String print() {
        System.out.println("\nCircle:\n r = " + radius);
        System.out.println("Area = " + area);
        System.out.println("Perimeter = " + perimeter);
        return "Circle:\n r = " + radius + "\nArea = " + area + "\nPerimeter = " + perimeter;
    }
}
