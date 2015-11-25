import java.io.PrintStream;
import java.io.File;

/**
 * This is a test for Symmetric Pascal Matrix
 * @author An Jihai
 * @version 1.0
 */

public class PascalMatrix {

    public static Matrix PascalMatrix(int i) {
        double[][] result = new double[i][i];

        for (int r = 0; r < i; r++) {
            double[] thisRow = new double[i];
            for (int c = 0; c < i; c++) {
                thisRow[c] = entryAtHere(r,c);
            }
            result[r] = thisRow;
        }
        return new Matrix(result);
    }

    public static Matrix PascalAugementMatrix(int i) {
        double[][] result = new double[i][i + 1];
        for (int r = 0; r < i; r++) {
            double[] thisRow = new double[i + 1];
            for (int c = 0; c < i + 1; c++) {
                if (c != i) {
                    thisRow[c] = entryAtHere(r, c);
                } else {
                    thisRow[c] = (1.0/(r+1));
                }
            }
            result[r] = thisRow;
        }
        return new Matrix(result);
    }

    public static long factorial(int d) {
        long result = d;
        if (d <= 1 ) { return 1;}
        for (int i = d - 1; i > 0; i--) {
            result *= i;
        }
        return result;
    }

    public static long entryAtHere(int r, int c) {
        //for n = 12, the entry is too big to fit in a long.
        if (r == 11  && c == 10) {
            return 352716;
        } else if (r == 11 && c == 11){
            return 705432;
        } else if (r == 10 && c == 11) {
            return 352716;
        }
        long upper = factorial((r) + (c));
        long lower = (factorial(r) * factorial(c));
        return upper/lower;
    }

    public static void main(String[] args) throws Exception {
        PrintStream output = new PrintStream(new File("PascalMatrix.txt"));
        System.out.println("Starting correctly...");
        for (int i = 2; i <=12; i++) {
            output.println("n = " + i);
            output.println();
            Matrix pmtx = PascalMatrix(i);
            output.println("Pascal Matrix:");
            output.println(pmtx);
            output.println();
            //LU Decomposition
            LUDecomposition m1 = new LUDecomposition();
            m1.lu_fact(pmtx);
            output.println("LU decomposition Error: ");
            output.println(m1.getError());
            output.println();
            //QR Householder
            QRdecomposition m2 = new QRdecomposition();
            m2.qr_fact_househ(pmtx);
            output.println("Householder Error: ");
            output.println(m2.getError());
            output.println();
            // QR GivenRotation
            QRdecomposition m3 = new QRdecomposition();
            m3.qr_fact_givens(pmtx);
            output.println("Given Rotation Error: ");
            output.println(m3.getError());
            output.println();
            //Augmented Pascal Matrix
            Matrix pamtx = PascalAugementMatrix(i);
            output.println("Pascal Augmented Matrix:");
            output.println(pamtx);
            output.println();
            //Solve_lu_b
            LUsolution m5 = new LUsolution();
            m5.solve_lu_b(pamtx);
            output.println("Solve_lu_b: ");
            output.println("Solution : ");
            output.println(m5.solve());
            output.println("Error: ");
            output.println(m5.getError());
            output.println();
            //Solve_qr_b_householder
            QRsolution m6 = new QRsolution();
            m6.solve_qr_b(pamtx);
            output.println("Solve_qr_b_householder: ");
            output.println("Solution : ");
            output.println(m6.solve());
            output.println("Error: ");
            output.println(m6.getError());
            output.println();
            //Solve_qr_b_givenrotation
            QRsolution m7 = new QRsolution();
            m7.solve_qr_b(pamtx);
            output.println("Solve_qr_b_givenrotation: ");
            output.println("Solution : ");
            output.println(m7.solve());
            output.println("Error: ");
            output.println(m7.getError());
            output.println();
            output.println("======================="
                    + "============================"
                    + "============================"
                    + "============================"
                    + "============================"
                    + "============================"
                    + "============================"
                    + "============================"
                    + "============================"
                    + "============================"
                    + "============================"
                    + "====================");
        }
        System.out.println("Done !");
    }
}
