/**
 * Created by Jihai on 11/21/2015.
 */
public class Matrix {

    double[][] entries = new double[3][3];

    public Matrix(Vector v1, Vector v2, Vector v3) {
        this.entries[0][0] = v1.getEntry(0);
        this.entries[1][0] = v1.getEntry(1);
        this.entries[2][0] = v1.getEntry(2);

        this.entries[0][1] = v2.getEntry(0);
        this.entries[1][1] = v2.getEntry(1);
        this.entries[2][1] = v2.getEntry(2);

        this.entries[0][2] = v3.getEntry(0);
        this.entries[1][2] = v3.getEntry(1);
        this.entries[2][2] = v3.getEntry(2);

    }

    public double getEntry(int r, int c) {
        if (r > 2 || c > 2) {
            System.out.println("invalid index for matrix");
            return 0;
        }
        return this.entries[r][c];
    }

    public String toString() {
        return (entries[0][0] + "\t" + entries[0][1] + "\t" + entries[0][2] + "\n"
                + entries[1][0] + "\t" + entries[1][1] + "\t" + entries[1][2] + "\n"
                + entries[2][0] + "\t" + entries[2][1] + "\t" + entries[2][1] + "\n");
    }

    public void printString() {
        System.out.printf("%-25s%-25s%-25s\n", entries[0][0], entries[0][1], entries[0][2]);
        System.out.printf("%-25s%-25s%-25s\n", entries[1][0], entries[1][1], entries[1][2]);
        System.out.printf("%-25s%-25s%-25s\n", entries[2][0], entries[2][1], entries[2][2]);
    }

}
