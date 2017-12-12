import java.io.File;

/**
 * This is a Test class
 * @author An Jihai
 * @version 1.0
 */
public class TestSolutions {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Takes one filename as the file to read matrices from");
            return;
        }
        MatrixScanner MS = new MatrixScanner();
        double[][][] matrixArray = MS.readAugmentedMatrix(new File(args[0]));
        Matrix[] matrixContainer = new Matrix[matrixArray.length];

        for (int index = 0; index < matrixContainer.length; index++) {
            Matrix currentTesting = new Matrix(matrixArray[index]);
            matrixContainer[index] = currentTesting;
        }
        //System.out.println("hello : " + matrixContainer.length);
        for (Matrix m : matrixContainer) {
            //System.out.println("current m: " + m);
            LUsolution m5 = new LUsolution();
            m5.solve_lu_b(m);
            System.out.println("Solve_lu_b: ");
            System.out.println("Solution : ");
            System.out.println(m5.solve());
            System.out.println("Error: ");
            System.out.println(m5.getError());
            System.out.println();
            //Solve_qr_b_householder
            QRsolution m6 = new QRsolution();
            m6.solve_qr_b(m);
            System.out.println("Solve_qr_b_householder: ");
            System.out.println("Solution : ");
            System.out.println(m6.solve());
            System.out.println("Error: ");
            System.out.println(m6.getError());
            System.out.println();
            //Solve_qr_b_givenrotation
            QRsolution m7 = new QRsolution();
            m7.solve_qr_b(m);
            System.out.println("Solve_qr_b_givenrotation: ");
            System.out.println("Solution : ");
            System.out.println(m7.solve());
            System.out.println("Error: ");
            System.out.println(m7.getError());
            System.out.println();
        }
























    }

}