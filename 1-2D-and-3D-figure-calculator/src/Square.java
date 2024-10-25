import java.awt.*;
import java.awt.print.Printable;

public class Square extends Figure implements Printing {
    double a;
    double area;
    double perimeter;

    public Square(double a) {
        if (a < 0){
            System.out.println("Wrong data! Enter a positive number.");
        }
        else{
            this.a = a;
            this.area = calculateArea();
            this.perimeter = calculatePerimeter();
        }
    }

    @Override
    double calculateArea() {
        return a * a;
    }
    @Override
    double calculatePerimeter() {
        return 4 * a;
    }
    @Override
    public String print(){
        System.out.println("\nSquare:\n a = " + a);
        System.out.println("Area = " + area);
        System.out.println("Perimeter = " + perimeter);
        return "Square:\n a = " + a + "\nArea = " + area + "\nPerimeter = " + perimeter;
    }
}
