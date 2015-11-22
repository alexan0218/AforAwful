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
        String result = "Vector Xn: " + entry[0] + ", " + entry[1] + ", " + entry[2] + "   ";
        return result;
    }






































}
