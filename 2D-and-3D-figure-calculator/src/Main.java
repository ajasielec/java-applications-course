import javax.swing.*;

public class Main {
    public static void main(String[] args) {

//        Triangle t = new Triangle(2.0, 2.0, 3.0);
//        t.print();
//
//        Square s = new Square(5);
//        s.print();
//
//        Circle c = new Circle(12);
//        c.print();
//
//        TreeDim t3d = new TreeDim(t, 12);
//        t3d.print();
//
//        TreeDim s3d = new TreeDim(s, 12);
//        s3d.print();
//
//        TreeDim c3d = new TreeDim(c, 12);
//        c3d.print();

        Figure userFigure = null;
        TreeDim user3DFigure = null;

        while (true) {
            // choosing a figure to create
            if (userFigure == null) {
                userFigure = chooseFigure();
                if (userFigure == null) {
                    System.exit(0); // user closed a window
                }
            }else {
                // option panel for 2D or 3D figure
                int choice = showFigureOptions(userFigure, user3DFigure);

                if (choice == JOptionPane.CLOSED_OPTION) {
                    System.exit(0); // user closed a window
                }

                switch (choice) {
                    case 0:
                        if (user3DFigure == null) {
                            JOptionPane.showMessageDialog(null, userFigure.print()); // displaying 2D figure
                        } else {
                            JOptionPane.showMessageDialog(null, user3DFigure.print()); // displaying 3D figure
                        }
                        break;
                    case 1:
                        if (user3DFigure == null) {
                            double h = getValidDouble("Enter height");
                            user3DFigure = new TreeDim(userFigure, h);  // creating 3D figure
                        }else {
                            user3DFigure = null; // deletes 3D figure
                        }
                        break;
                    case 2:
                        userFigure = null;
                        user3DFigure = null;
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        }
    }

    // choosing figure method
    private static Figure chooseFigure() {
        String[] options = {"Triangle", "Square", "Circle"};
        int choice = JOptionPane.showOptionDialog(null,
                "Choose a figure you want to create:",
                "Choosing a figure",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == JOptionPane.CLOSED_OPTION) {
            return null;    // user closed a window
        }

        switch (choice) {
            case 0:
                // triangle validation
                while (true) {
                    double a = getValidDouble("Enter side a");
                    double b = getValidDouble("Enter side b");
                    double c = getValidDouble("Enter side c");

                    // triangle sides conditions
                    if (a > b + c || b > c + a || c > a + b){
                        JOptionPane.showMessageDialog(null,
                                "Invalid data! The sum of two triangle sides must be greater than third side.");
                    } else {
                        return new Triangle(a, b, c);
                    }
                }
            case 1:
                double side = getValidDouble("Enter side a");
                return new Square(side);
            case 2:
                double radius = getValidDouble("Enter radius");
                return new Circle(radius);
            default:
                return null;
        }
    }

    // method to show set of options
    private static int showFigureOptions(Figure figure, TreeDim figure3D) {
        String[] options = figure3D == null
                ? new String[]{"Display " + figure.getClass().getSimpleName(), "Make 3D", "Select different figure"}
                : new String[]{"Display 3D" + figure.getClass().getSimpleName(), "Make 2D", "Select different figure"};

        return JOptionPane.showOptionDialog(
                null,
                "What do you want to do?",
                figure.getClass().getSimpleName() + " option panel",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    // method to create 3D figure
    private static TreeDim create3DFigure(Figure figure) {
        try {
            double h = Double.parseDouble(JOptionPane.showInputDialog("Enter height"));
            return new TreeDim(figure, h);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a number.");
            return null;
        }
    }

    // function to get valid number
    private static double getValidDouble (String message) {
        double value = 0;
        boolean valid = false;

        // repeating message unlit valid number
        while (!valid) {
            String input = JOptionPane.showInputDialog(message);

            if (input == null) {
                System.exit(0); // user closed a window
            }

            try {
                value = Double.parseDouble(input);
                if (value <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter a positive number.");
                } else {
                    valid = true;   // positive number
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a number.");
            }
        }
        return value;
    }
}