/**
 * This is a LU Decomposition class
 * @author An Jihai
 * @version 1.0
 */
public class LUDecomposition {
    private Matrix lm,um,im,xm;
    private int row, col;

    public void lu_fact(Matrix m) {
        xm = m.copy();
        um = m.copy();
        lm = Matrix.identityMatrix(m.getRowDimension());
        im = Matrix.identityMatrix(m.getRowDimension());
        row = m.getRowDimension();
        col = m.getColumnDimension();
        for (int i = 0; i < col; i++) {
            for (int j = i + 1; j < row; j++) {
                double[][] temp = Matrix.identityMatrix(row).getArray();
                double a1 = um.getArray()[i][i];
                double a2 = um.getArray()[j][i];
                temp[j][i] = -a2 / a1;
                Matrix l1 = new Matrix(temp);
                um = l1.matrixMultiply(um);
                temp[j][i] = a2 / a1;
                Matrix l2 = new Matrix(temp);
                lm = lm.matrixMultiply(l2);
            }
        }
    }

    public Matrix getLMatrix() {
        return lm;
    }
    public Matrix getUMatrix() {
        return um;
    }
    public double getError() {
        Matrix m = (this.getLMatrix().matrixMultiply(this.getUMatrix())).subtract(xm);
        return m.maxNormi();
    }
}
