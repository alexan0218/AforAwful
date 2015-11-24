import java.util.Random;

public class matrix{
    public double[][] contents;
    public int height;
    public int width;
    public double trace;
    public double[] eigenvalues;
    public double determinant;
    public matrix inverse;
    public int eigenvalueCount;
    public int iterations;
    public vector[] eigenvectors;
    public int eigenvectorCount;
    
    public matrix(double[][] matrix) {
        contents = matrix;
        height = contents.length;
        width = contents[0].length;
        determinant = calculateDeterminant();
        trace = 0;
        eigenvalues = new double[width];
        eigenvalueCount = 0;
        iterations = 0;
        eigenvectors = new vector[width];
        eigenvectorCount = 0;
    }
    //generate random 2x2 matrix with doubles in [-2,2]
    public matrix() {
        Random rando = new Random();
        contents = new double[2][2];
        height = contents.length;
        width = contents[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                contents[j][i] = (rando.nextDouble() * 4) - 2;
            }
        }
        determinant = calculateDeterminant();
        trace = 0;
        eigenvalues = new double[width];
        eigenvalueCount = 0;
        iterations = 0;
        eigenvectors = new vector[width];
        eigenvectorCount = 0;
    }
    
    public double calculateDeterminant() {
        if (width == 2 && height == 2) {
            determinant = (contents[0][0] * contents[1][1]) - (contents[0][1] * contents[1][0]);
        }
        return determinant;
    }
    
    public vector vectorMultiply(vector x) {
        if (x.contents.length != width) {
            System.out.println("size mismatch between vector and matrix being multiplied");
            return null;
        }
        double[] answer = new double[height];
        for (int i = 0; i < height; i++) {
            answer[i] = 0;
            for (int j = 0; j < width; j++) {
                answer[i] += (contents[j][i] * x.contents[j]);
            }
        }
        return new vector(answer);
    }
    
    public double estimateEigenvalue(vector x) {
        return (x.dot(vectorMultiply(x))) / (x.dot(x));
    }
    
    public boolean hasInverse() {
        return determinant != 0;
    }
    
    //designed to work on 2x2 matrices
    public void calculateInverse() {
        double[][] temp = new double[2][2];
        temp[0][0] = contents[1][1] * (1/determinant);
        temp[1][0] = contents[1][0] * (1/determinant) * -1;
        temp[0][1] = contents[0][1] * (1/determinant) * -1;
        temp[1][1] = contents[0][0] * (1/determinant);
        inverse = new matrix(temp);
    }
    
    public void calculateTrace() {
        trace = 0;
        
        for (int i = 0; i < height; i++) {
            trace += contents[i][i];
        }
    }
    
    public void addEigenvalue(double value) {
        eigenvalues[eigenvalueCount] = value;
        eigenvalueCount++;
    }
    
    public void addEigenvector(vector v) {
        eigenvectors[eigenvectorCount] = v;
        eigenvectorCount++;
    }
    
    public String toString() {
        return (contents[0][0] + "\t" + contents[1][0] + "\n"
                + contents[0][1] + "\t" + contents[1][1] + "\n");
    }
    
    //transpose a square matrix
    public void transpose(){
        double[][] temp = new double[contents.length][contents.length];
        for (int i = 0; i < contents.length; i++) {
            for (int j = 0; j < contents.length; j++) {
                temp[j][i] = contents[i][j];
            }
        }
        contents = temp;
    }
}