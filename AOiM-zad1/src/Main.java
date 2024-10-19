public class Main {
    public static void main(String[] args) {

        Triangle t = new Triangle(2.0, 2.0, 3.0);
        t.print();

        Square s = new Square(5);
        s.print();

        Circle c = new Circle(12);
        c.print();

        TreeDim t3d = new TreeDim(t, 12);
        t3d.print();

        TreeDim s3d = new TreeDim(s, 12);
        s3d.print();

        TreeDim c3d = new TreeDim(c, 12);
        c3d.print();




    }
}