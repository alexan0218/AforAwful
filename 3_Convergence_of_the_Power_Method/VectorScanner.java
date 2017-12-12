import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class VectorScanner {

    private Scanner scanner;

    public double[] readVector(File file) {
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Sorry. File not found.");
        }
        List<Double> contents = new ArrayList<Double>();
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            double newNumber = Double.parseDouble(line);
            contents.add(newNumber);
        }
        double[] answer = new double[contents.size()];
        for (int i = 0; i < contents.size(); i++) {
            answer[i] = contents.get(i);
        }
        return answer;
    }

    /*private double[] parseRow(String row) {
        String[] asArray = row.split(",| ");
        double[] result = new double[asArray.length];
        for (int i = 0; i < asArray.length; i++) {
            result[i] = Double.parseDouble(asArray[i]);
        }
        return result;
    }*/
}

