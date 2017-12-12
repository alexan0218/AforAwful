import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MatrixScanner {

    private Scanner scanner;

    public double[][][] readMatrix(File file) {
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Sorry. File not found.");
        }
        int n;
        int c = 0;
        List<double[]> rowList = new ArrayList<double[]>();
        List<double[][]> matrixList = new ArrayList<double[][]>();
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            double[] row = parseRow(line);
            n = row.length;
            rowList.add(row);
            c++;
            if (c == n) {
                c = 0;
                matrixList.add(rowList.toArray(new double[rowList.size()][]));
                rowList = new ArrayList<double[]>();
            }
        }
        return matrixList.toArray(new double[matrixList.size()][][]);
    }

    private double[] parseRow(String row) {
        String[] asArray = row.split(",| ");
        double[] result = new double[asArray.length];
        for (int i = 0; i < asArray.length; i++) {
            result[i] = Double.parseDouble(asArray[i]);
        }
        return result;
    }
}

