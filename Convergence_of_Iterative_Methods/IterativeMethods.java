import java.util.ArrayList;
import java.io.*;
import java.util.Random;

/**
 * Created by Jihai on 11/21/2015.
 */

public class IterativeMethods {

    public static Matrix initializeMatrix() {
        Vector v1 = new Vector(generateEntries(1, 0.5, 1.0 / 3.0));
        Vector v2 = new Vector(generateEntries(0.5, 1, 0.25));
        Vector v3 = new Vector(generateEntries(1.0 / 3.0, 0.25, 1));
        Matrix m = new Matrix(v1, v2, v3);
        return m;
    }

    public static Vector initializeVector() {
        Vector b = new Vector(generateEntries(0.1, 0.1, 0.1));
        return b;
    }

    public static double[] generateEntries(double a, double b, double c) {
        double[] result = {a ,b ,c};
        return result;
    }

    public static ArrayList<Vector> generateVectors() {
        ArrayList<Vector> vectors = new ArrayList<>();
        Random randomEngine = new Random();
        for (int i = 0; i < 100; i++) {
            double entry1 = (-1) + (2 * randomEngine.nextDouble());
            double entry2 = (-1) + (2 * randomEngine.nextDouble());
            double entry3 = (-1) + (2 * randomEngine.nextDouble());
            double[] generatedEntry = {entry1, entry2, entry3};
            Vector newVector = new Vector(generatedEntry);
            vectors.add(newVector);
        }
        return vectors;
    }

    public static Pair jacobi_iter(Vector x, double tolerance, int maxIteration) {
        Matrix m = initializeMatrix();
       // System.out.println("Matrix A is : \n");
        //m.printString();
       //System.out.println("Vector b is : \n");
        Vector b = initializeVector();
       //System.out.println("===================");
        double x0 = x.getEntry(0);
        double x1 = x.getEntry(1);
        double x2 = x.getEntry(2);
        int iterationCounter = 0;
        double error = 9999;
        double new0, new1, new2;
        while (iterationCounter < maxIteration && error > tolerance) {
            new0 = (b.getEntry(0) - (x1 * m.getEntry(0,1)) - (x2 * m.getEntry(0,2)))/m.getEntry(0,0);
            new1 = (b.getEntry(1) - (x0 * m.getEntry(1,0)) - (x2 * m.getEntry(1,2)))/m.getEntry(1,1);
            new2 = (b.getEntry(2) - (x0 * m.getEntry(2,0)) - (x1 * m.getEntry(2,1)))/m.getEntry(2,2);
            error = Math.sqrt((Math.abs(new0 - x0) * Math.abs(new0 - x0)) + (Math.abs(new1 - x1) * Math.abs(new1 - x1)) + (Math.abs(new2 - x2) * Math.abs(new2 - x2)));
          //System.out.println("Error: " + error);
            iterationCounter++;
            x0 = new0;
            x1 = new1;
            x2 = new2;
        }
        if (error > tolerance) {
            System.out.println("After " + maxIteration + "iterations, the error is still larger than the tolerance");
            return new Pair(null,0);
        }
        Pair result = new Pair(new Vector(generateEntries(x0, x1, x2)),iterationCounter);
        return result;
    }

    public static Pair gs_iter(Vector x, double tolerance, int maxIteration) {
        Matrix m = initializeMatrix();
        // System.out.println("Matrix A is : \n");
        //m.printString();
        //System.out.println("Vector b is : \n");
        Vector b = initializeVector();
        //System.out.println("===================");
        double x0 = x.getEntry(0);
        double x1 = x.getEntry(1);
        double x2 = x.getEntry(2);
        int iterationCounter = 0;
        double error = 9999;
        double record0 = x0;
        double record1 = x1;
        double record2 = x2;
        while (iterationCounter < maxIteration && error > tolerance) {
            x0 = (b.getEntry(0) - (x1 * m.getEntry(0,1)) - (x2 * m.getEntry(0,2)))/m.getEntry(0,0);
            x1 = (b.getEntry(1) - (x0 * m.getEntry(1,0)) - (x2 * m.getEntry(1,2)))/m.getEntry(1,1);
            x2 = (b.getEntry(2) - (x0 * m.getEntry(2,0)) - (x1 * m.getEntry(2,1)))/m.getEntry(2,2);
            error = Math.sqrt((Math.abs(record0 - x0) * Math.abs(record0 - x0)) + (Math.abs(record1 - x1) * Math.abs(record1 - x1)) + (Math.abs(record2 - x2) * Math.abs(record2 - x2)));
            record0 = x0;
            record1 = x1;
            record2 = x2;
            iterationCounter++;
        }
        if (error > tolerance) {
            System.out.println("After " + maxIteration + "iterations, the error is still larger than the tolerance");
            return new Pair(null,0);
        }
        Pair result = new Pair(new Vector(generateEntries(x0, x1, x2)),iterationCounter);
        return result;
    }


    public static void main(String[] args) throws IOException {
        //============================== Jacobi's method part ============================================

        File jcbResultFile = new File("Convergence_of_iterative_methods.txt");
        OutputStream outStream1 = new FileOutputStream(jcbResultFile);
        outStream1.write(("=====================================Jacobi's method begins =====================================\n").getBytes());
        ArrayList<Vector> vectors1 = generateVectors();
        double jcbsum0 = 0;
        double jcbsum1 = 0;
        double jcbsum2 = 0;
        ArrayList<Integer> jcbiterCount = new ArrayList<>();
        for (Vector v : vectors1) {
            System.out.print(v.asString());
            Pair jcbanswer = jacobi_iter(v, 0.00005, 100);
            outStream1.write((jcbanswer.toString() + "\n").getBytes());
            jcbsum0 += jcbanswer.getEntry(0);
            jcbsum1 += jcbanswer.getEntry(1);
            jcbsum2 += jcbanswer.getEntry(2);
            jcbiterCount.add(jcbanswer.getIteration());
        }
        jcbsum0 = jcbsum0/100.0;
        jcbsum1 = jcbsum1/100.0;
        jcbsum2 = jcbsum2/100.0;
        //double avgIter = jcbiterCount/100.0;
        outStream1.write(("Solution: " + jcbsum0 + " " + jcbsum1 + " " + jcbsum2 + " \n").getBytes());
        //outStream1.flush();
        //outStream1.close();


        //============================== GS method part ============================================
        //File gsResultFile = new File("gs_iter_result_File.txt");
        //OutputStream outStream2 = new FileOutputStream(gsResultFile);
        outStream1.write(("=====================================Givens Rotation's method begins======================================\n").getBytes());
        //ArrayList<Vector> vectors2 = generateVectors();
        double gssum0 = 0;
        double gssum1 = 0;
        double gssum2 = 0;
        ArrayList<Integer> gsiterCount = new ArrayList<>();
        for (Vector v : vectors1) {
            Pair gsanswer = gs_iter(v, 0.00005, 100);
            outStream1.write(gsanswer.toString().getBytes());
            outStream1.write("\n".getBytes());
            gssum0 += gsanswer.getEntry(0);
            gssum1 += gsanswer.getEntry(1);
            gssum2 += gsanswer.getEntry(2);
            gsiterCount.add(gsanswer.getIteration());
        }
        gssum0 = gssum0/100.0;
        gssum1 = gssum1/100.0;
        gssum2 = gssum2/100.0;
        //double avgIter2 = gsiterCount/100.0;
        outStream1.write(("Solution: " + gssum0 + " " + gssum1 + " " + gssum2 + " \n").getBytes());

        //============================ Ratio of iteration ===========================================
        ArrayList<Double> ratios = new ArrayList<>(100);
        outStream1.write(("=========================Ratio of iterations starts here ( N jcb/N gs ) ===================\n").getBytes());
        for (int i = 0; i < 100; i++) {
            double ratio = ((double) jcbiterCount.get(i))/gsiterCount.get(i);
            ratios.add(ratio);
        }
        for (int e = 0; e < 100; e++) {
            outStream1.write((ratios.get(e) + "\n").getBytes());
        }

        outStream1.flush();
        outStream1.close();
    }





}
