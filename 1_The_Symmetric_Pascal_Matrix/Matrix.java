/**
* This is a Matrix class
* @author An Jihai
* @version 1.0
*/
public class Matrix extends Vector {
	private double[][] m;
	private int r, c;

	public Matrix(double[][] m) {
		for (int i = 0; i < r; i++) {
			if (m[i].length != c) {
				System.out.println("Invalid matrix.");
				System.exit(0);
			}
		}
		r = m.length;
		c = m[0].length;
		this.m = m;
	}

	public Matrix(int r, int c) {
		this.r = r;
		this.c = c;
		this.m = new double[r][c];
	}

	public void setM(int i, int j, double data) {
		m[i][j] = data;
	}

	public double[][] getM() {
		return m;
	}
	public int getRowDimension() {
		return r;
	}
	public int getColumnDimension() {
		return c;
	}
	public double[][] getArray() {
		return m;
	}
	public String toString() {
		String output = "";
        for (int j = 0; j < this.r; j++) {
            for (int i = 0; i < this.c; i++) {
                output += (String.format("%-25s",this.m[j][i]));
            }
            output += "\n";
        }
        return output;
	}

	public Matrix subtract(Matrix m1) {
		if (this.getColumnDimension() != m1.getColumnDimension()
			|| this.getRowDimension() != m1.getRowDimension()) {
			System.out.println("Invalid input");
			System.exit(0);
		}
		Matrix n = new Matrix(r, c);
		double[][] a = n.getArray();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				a[i][j] = m[i][j] - m1.getArray()[i][j];
			}
		}
		return n;
	}
	public Matrix multiple(double s) {
		Matrix n = new Matrix(r, c);
		double[][] a = n.getArray();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				a[i][j] = s * m[i][j];
			}
		}
		return n;
	}
	public Matrix copy() {
		Matrix n = new Matrix(r, c);
		double[][] a = n.getArray();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				a[i][j] = m[i][j];
			}
		}
		return n;
	}
	public Matrix transpose() {
		Matrix n = new Matrix(c, r);
		double[][] a = n.getArray();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				a[j][i] = m[i][j];
			}
		}
		return n;
	}
	public Vector getRow(int num) {
		if (num >= r) {
			System.out.println("Invalid input.");
			System.exit(0);
		}
		return new Vector(this.m[num]);
	}
	public Vector getColumn(int num) {
		if (num >= c) {
			System.out.println("Invalid input.");
			System.exit(0);
		}
		return new Vector(this.transpose().m[num]);
	}
	public Matrix matrixMultiply(Matrix other) {
		if (this.c != other.r) {
			System.out.println("Invalid input.");
			System.exit(0);
		}
		double result[][] = new double[this.r][other.c];
		for (int i = 0; i < this.r; i++) {
			for (int j = 0; j < other.c; j++) {
				result[i][j] = this.getRow(i).dotProduct(other.getColumn(j));
			}
		}
		return new Matrix(result);
	}

	public double maxNormi() {
		double sum = 0;
		double max = 0;
		for (int i = 0; i < r; i++) {
			sum = 0;
			for (int j = 0; j < c; j++) {
				sum += Math.abs(m[i][j]);
			}
			if (sum > max) {
				max = sum;
			}
		}
		return max;
	}

	public static Matrix identityMatrix(int num) {
		Matrix n = new Matrix(num, num);
		double[][] a = n.getArray();
		for (int i = 0; i < num; i++) {
			a[i][i] = 1.0;
		}
		return n;
	}
}
