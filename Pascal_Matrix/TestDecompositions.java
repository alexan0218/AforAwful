import java.io.File;

/**
* This is a Test class
* @author An Jihai
* @version 1.0
*/
public class TestDecompositions {


    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Takes one filename as the file to read matrices from");
            return;
        }
        MatrixScanner MS = new MatrixScanner();
        double[][][] matrixArray = MS.readMatrix(new File(args[0]));
        Matrix[] matrixContainer = new Matrix[matrixArray.length];

        for (int index = 0; index < matrixContainer.length; index++) {
            Matrix currentTesting = new Matrix(matrixArray[index]);
            matrixContainer[index] = currentTesting;
        }

        for (Matrix m : matrixContainer) {
            LUDecomposition lum = new LUDecomposition();
            lum.lu_fact(m);
            System.out.println("=======================================================================================");
            System.out.println("LU decomposition: ");
            System.out.println("L Matrix : ");
            System.out.println(lum.getLMatrix());
            System.out.println("U Matrix : ");
            System.out.println(lum.getUMatrix());
            System.out.println("LU decomposition Error: ");
            System.out.println(lum.getError());
            System.out.println();

            QRdecomposition qrmhh = new QRdecomposition();
            qrmhh.qr_fact_househ(m);
            System.out.println("Householder decomposition: ");
            System.out.println("Q Matrix : ");
            System.out.println(qrmhh.getQMatrix());
            System.out.println("R Matrix : ");
            System.out.println(qrmhh.getRMatrix());
            System.out.println("QR decomposition Error: ");
            System.out.println(qrmhh.getError());
            System.out.println();

            QRdecomposition qrmgr = new QRdecomposition();
            qrmgr.qr_fact_givens(m);
            System.out.println("Givens rotation decomposition: ");
            System.out.println("Q Matrix : ");
            System.out.println(qrmgr.getQMatrix());
            System.out.println("R Matrix : ");
            System.out.println(qrmgr.getRMatrix());
            System.out.println("QR decomposition Error: ");
            System.out.println(qrmgr.getError());
            System.out.println();

            //System.out.println(qrmgr.getRMatrix().getM()[0][1]);
        }
























    }

}