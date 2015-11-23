import java.util.ArrayList;
/**
 * This is a Vector class
 * @author An Jihai
 * @version 1.0
 */
public class Vector {

    protected int dimension;
    protected double norm;
    protected ArrayList<Double> vector = new ArrayList<>();

    public Vector(ArrayList<Double> vector) {
        this.vector = vector;
        double sum = 0;
        for(double value : vector) {
            sum += (value * value);
        }
        norm = Math.sqrt(sum);
        dimension = vector.size();
    }
    public Vector(double... vector) {
        double sum = 0;
        for(double value : vector) {
            this.vector.add(value);
        }
        dimension = vector.length;
        for(double value : vector) {
            sum += (value * value);
        }
        norm = Math.sqrt(sum);
    }

    public void set(int p, double d) {
        this.vector.set(p, d);
        double sum = 0;
        for (double value : vector) {
            sum += (value * value);
        }
        this.norm = Math.sqrt(sum);
    }

    public String toString() {
        String result = "[ ";
        for (Double d : vector) {
            String component =  String.format("%1$.5f", d);
            result += String.format("%-10s", component);
        }
        result += "]";
        return result;
    }

    public double[] toArray() {
        double n[] = new double[this.vector.size()];
        int i = 0;
        for (double value : vector) {
            n[i] = value;
            i += 1;
        }
        return n;
    }

    public Matrix vectorToMatrix() {
        double[][] o = new double[this.dimension][1];
        for (int i = 0; i < this.dimension; i++) {
            o[i][0] = this.vector.get(i);
        }
        return new Matrix(o);
    }

    public double dotProduct(Vector other) {
        if (this.dimension != other.dimension) {
            System.out.println("Invalid Input");
            System.exit(0);
        }
        double sum = 0;
        for (int i = 0; i < this.dimension; i++) {
            double a =  this.vector.get(i) * other.vector.get(i);
            sum += a;
        }
        return sum;
    }

    public Vector normalize() {
        if (this.norm == 0) {
            return this;
        }
        ArrayList<Double> newvector = new ArrayList<>();
        for (double value : this.vector) {
            newvector.add(value / this.norm);
        }
        return new Vector(newvector);
    }
}
