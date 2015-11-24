/**
 * This is a Solve_qr_b_givenrotation class
 * @author An Jihai
 * @version 1.0
 */
public class QRsolution {
    private Matrix qmtx, rmtx, bmtx, amtx, wmtx;
    public  Vector b;
    private int col, row;

    public void solve_qr_b(Matrix a) {
        wmtx = a.copy();
        col = a.getColumnDimension();
        row = a.getRowDimension();
        b = a.getColumn(col - 1);
        double[][] a1 = new double[row][col - 1];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - 1; j++) {
                a1[i][j] = a.getArray()[i][j];
            }
        }
        amtx = new Matrix(a1);
        QRdecomposition temp = new QRdecomposition();
        temp.qr_fact_givens(amtx);
        qmtx = temp.getQMatrix();
        rmtx = temp.getRMatrix();
    }
    
    public Vector solve() {
        bmtx = qmtx.transpose().matrixMultiply(wmtx);
        double[][] b1 = bmtx.getArray();
        for (int j = row - 1; j >= 0; j--) {
            for (int i = j + 1; i < col - 1; i++) {
                b1[j][col - 1] -= (b1[j][i] * b1[i][col - 1]);
            }
            b1[j][col - 1] /= b1[j][j];
        }
        Matrix b2 = new Matrix(b1);
        return b2.getColumn(col - 1);
    }
    public double getError() {
        Vector x = this.solve();
        Matrix xmtx = x.vectorToMatrix();
        Matrix m = amtx.matrixMultiply(xmtx).subtract(b.vectorToMatrix());
        return (m.maxNormi());
    }
}
