import java.io.File;
import java.util.ArrayList;

/**
 * Created by Jihai on 11/24/2015.
 */
public class TestIterations {

    public static void main(String[] args) throws NullPointerException{

        if (args.length != 3) {
            System.out.println("Arguments: fileName, tolerance, maxIterations");
            return;
        }

        VectorScanner VS = new VectorScanner();
        Vector v = VS.readVector(new File(args[0]));
        IterativeMethods test = new IterativeMethods();

        System.out.println("Current initial vector: " + v.asString());
        Pair jcbanswer = test.jacobi_iter(v, Double.parseDouble(args[1]), Integer.parseInt(args[2]));
        System.out.println("Solution and #Iterations for jacobi: " + jcbanswer.toString() + "\n");

        Pair granswer = test.gs_iter(v, Double.parseDouble(args[1]), Integer.parseInt(args[2]));
        System.out.println("Solution and #Iterations for Gauss-Seidel: " + granswer.toString() + "\n");


    }
}
