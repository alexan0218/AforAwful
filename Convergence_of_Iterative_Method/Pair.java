/**
 * Created by Jihai on 11/22/2015.
 */
public class Pair {
    private Vector v;
    private int iteration;

    public Pair(Vector v, int iteration) {
        this.v = v;
        this.iteration = iteration;
    }

    public String toString() {
        return (v.toString() + " " + iteration);
    }

   /* public Pair nullPair() {

    }*/

    public double getEntry(int i) {
        return v.getEntry(i);
    }

    public int getIteration() {
        return iteration;
    }
}
