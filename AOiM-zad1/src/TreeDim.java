public class TreeDim implements Printing{
    Figure root;
    double height;

    public TreeDim(Figure root, double height) {
        this.root = root;
        this.height = height;
    }

    double calculateVolume() {
        return height * root.calculateArea();
    }

    double calculateTotalArea() {
        return 2 * root.calculateArea() + height * root.calculatePerimeter();
    }

    @Override
    public String print() {
        System.out.println("\nRoot: " + root.getClass().getSimpleName());
        System.out.println("Height: " + height);
        System.out.println("Volume: " + calculateVolume());
        System.out.println("Total Area: " + calculateTotalArea());
        return "Root: " + root.getClass().getSimpleName() +
                "\nHeight = " + height +
                "\nVolume = " + calculateVolume() +
                "\nTotal Area = " + calculateTotalArea();
    }
}
