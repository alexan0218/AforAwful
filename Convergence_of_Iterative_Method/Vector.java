import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jihai on 11/21/2015.
 */

public class Vector {

    public double[] entry = new double[3];

    public Vector(double[] entry) {
        this.entry = entry;
    }

    public double getEntry(int index) {
        if (index > 2) {
            System.out.println("invalid index for vector");
            return 0;
        }
        return entry[index];
    }

    public String toString() {
        double entry1 = (entry[0] - 9.0/190) * (entry[0] - 9.0/190);
        double entry2 = (entry[1] - 28.0/475) * (entry[1] - 28.0/475);
        double entry3 = (entry[2] - 33.0/475) * (entry[2] - 33.0/475);
        double error = Math.sqrt(entry1 + entry2 + entry3);
        String result = error + "   ";
        return result;
    }

    public String asString() {
        return ("Vector Xn: " + entry[0] + " " + entry[1] + " " + entry[2] + "\n");
    }





































}
