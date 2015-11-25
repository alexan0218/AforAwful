import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class VectorScanner {

    private Scanner scanner;

    public Vector readVector(File file) {
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Sorry. File not found.");
        }
        int n = 0;
        double[] vectorEntries = new double[3];
        while (scanner.hasNextLine()) {
            if (n > 2) {
                System.out.println("\nBased on the project outline, this function would only take a vector with three element as the initial Xo.");
                System.out.println("Your input has more than three elements, the first three element would be taken as initial Xo.\n");
                break;
            }
            String line = scanner.nextLine();
            double thisElement = parseElement(line);
            vectorEntries[n] = thisElement;
            n++;
            }
        return new Vector(vectorEntries);
        }


    private double parseElement(String row) {
        String[] asArray = row.split(",| ");
        double[] result = new double[asArray.length];
        for (int i = 0; i < asArray.length; i++) {
            result[i] = Double.parseDouble(asArray[i]);
        }
        if (result.length > 1) {
            throw new IllegalArgumentException("The input must be a vector, not matrix !");
        }
        return result[0];
    }
}

