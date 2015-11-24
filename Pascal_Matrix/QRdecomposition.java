/**
 * This is a QR decomposition class
 * @author An Jihai
 * @version 1.0
 */

public class QRdecomposition {
    private Matrix xm, qm, rm, im;
    private int row, col;

    public void qr_fact_househ(Matrix m) {
        xm = m.copy();
        rm = m.copy();
        qm = Matrix.identityMatrix(m.getRowDimension());
        im = Matrix.identityMatrix(m.getRowDimension());
        row = m.getRowDimension();
        col = m.getColumnDimension();
        for (int i = 0; i < row; i++) {
            Vector v = rm.getColumn(i);
            double a = v.vector.get(i);
            for(int j = 0; j < i; j++) {
                v.set(j, 0);
            }
            v.set(i, v.norm + a);
            Matrix h1 = v.normalize().vectorToMatrix().matrixMultiply(v.normalize().vectorToMatrix().transpose());
            Matrix q1 = (h1.multiple(2)).subtract(im);
            rm = q1.matrixMultiply(rm);
            qm = qm.matrixMultiply(q1.transpose());
        }
    }

    public void qr_fact_givens(Matrix a) {
        xm = a.copy();
        rm = a.copy();
        qm = Matrix.identityMatrix(a.getRowDimension());
        im = Matrix.identityMatrix(a.getRowDimension());
        row = a.getRowDimension();
        col = a.getColumnDimension();
        for (int i = 0; i < col; i++) {
            for (int j = i + 1; j < row; j++) {
                double a1 = rm.getArray()[i][i];
                double a2 = rm.getArray()[j][i];
                double[][] temp = Matrix.identityMatrix(row).getArray();
                temp[i][i] = cos(a1, a2);
                temp[j][j] = cos(a1, a2);
                temp[i][j] = sin(a1, a2);
                temp[j][i] = sin(a1, -a2);
                Matrix q1 = new Matrix(temp);
                rm = q1.matrixMultiply(rm);
                qm = qm.matrixMultiply((q1).transpose());
            }
        }

    }

    private double sin(double a, double b) {
        return (b / Math.sqrt(a * a + b * b));
    }
    private double cos(double a, double b) {
        return (a / Math.sqrt(a * a + b * b));
    }
    public Matrix getQMatrix() {
        return this.qm;
    }
    public Matrix getRMatrix() {
        return this.rm;
    }
    public double getError() {
        Matrix m = (this.getQMatrix().matrixMultiply(this.getRMatrix())).subtract(xm);
        return m.maxNormi();
    }
}
