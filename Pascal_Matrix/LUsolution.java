/**
 * This is a LUsolution class
 * @author An Jihai
 * @version 1.0
 */
public class LUsolution {

	private Matrix wmtx, lmtx, umtx, amtx;
    private int col, row;
    private Vector b;
    private double[] b1;
    private double[][] h, l, u;

    public void solve_lu_b(Matrix a) {
        wmtx = a.copy();
        col = (wmtx.getColumnDimension() - 1);
        row = wmtx.getRowDimension();
        b = wmtx.getColumn(col);
        b1 = b.toArray();
        h = new double[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                h[i][j] = wmtx.getArray()[i][j];
            }
        }
        amtx = new Matrix(h);
        LUDecomposition temp = new LUDecomposition();
        temp.lu_fact(amtx);
        lmtx = temp.getLMatrix();
        l = lmtx.getArray();
        umtx = temp.getUMatrix();
        u = umtx.getArray();
    }

    public Vector solve() {
    	double[] y = new double[row];
    	y[0] = b1[0] / l[0][0];
    	for (int i = 1; i < row; i++) {
    		double sum = 0;
    		for (int j = 0; j < col; j++) {
    			sum += l[i][j] * y[j];
    		}
    		y[i] = (b1[i] - sum) / l[i][i];
    	}
    	double[] x = new double[row];
    	x[row-1] = y[row-1] / u[row-1][row-1];
    	for (int i = row - 2; i >= 0; i--) {
    		double sum1 = 0;
    		for (int j = 0; j < col; j++) {
    			sum1 += u[i][j] * x[j];
    		}
    		x[i] = (y[i] - sum1) / u[i][i];
    	}
    	return new Vector(x);
    }

    public double getError() {
        Vector x = this.solve();
        Matrix temp = x.vectorToMatrix();
        Matrix m = amtx.matrixMultiply(temp).subtract(b.vectorToMatrix());
        return (m.maxNormi());
    }
}
