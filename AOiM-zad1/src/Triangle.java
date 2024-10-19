public class Triangle extends Figure implements Printing {
    double a, b, c;
    double area;
    double perimeter;

    public Triangle(double a, double b, double c) {
        if (a > b + c || b > c + a || c > a + b){
            System.out.println("Wrong data! Sum of two of the triangle sides cannot be lower then third side!");
        }
        else if (a <= 0 || b <= 0 || c <= 0){
            System.out.println("Enter a positive number!");
        }
        else {
            this.a = a;
            this.b = b;
            this.c = c;
            perimeter = calculatePerimeter();
            area = calculateArea();
        }
    }

    @Override
    double calculateArea() {
        double p = perimeter / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
    @Override
    double calculatePerimeter() {
        return a + b + c;
    }
    @Override
    public void print(){
        System.out.println("\nTriangle: \na = " + a + ", b = " + b + ", c = " + c);
        System.out.println("area = " + area);
        System.out.println("perimeter = " + perimeter);
    }
}
