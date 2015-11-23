import java.util.Random;

public class matrix{
    public float[][] contents;
    public int height;
    public int width;
    public float trace;
    public float[] eigenvalues;
    public float determinant;
    public matrix inverse;
    public int eigenvalueCount;
    public int iterations;
    public vector[] eigenvectors;
    public int eigenvectorCount;
    
    /*public matrix(float[][] matrix) {
        contents = matrix;
        height = contents.length;
        width = contents[0].length;
        determinant = calculateDeterminant();
    }*/
    //generate random 2x2 matrix with floats in [-2,2]
    public matrix() {
        Random rando = new Random();
        contents = new float[2][2];
        height = contents.length;
        width = contents[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                contents[j][i] = rando.nextFloat() * 4;
            }
        }
        determinant = calculateDeterminant();
        trace = 0;
        eigenvalues = new float[width];
        float[][] inverse = new float[width][height];
        eigenvalueCount = 0;
        iterations = 0;
        eigenvectors = new vector[width];
        eigenvectorCount = 0;
    }
    
    public float calculateDeterminant() {
        if (width == 2 && height == 2) {
            determinant = (contents[1][1] * contents[2][2]) - (contents[1][2] * contents[2][1]);
        }
        return determinant;
    }
    
    public vector vectorMultiply(vector x) {
        if (x.contents.length != width) {
            System.out.println("size mismatch between vector and matrix being multiplied");
            return null;
        }
        float[] answer = new float[height];
        for (int i = 0; i < height; i++) {
            answer[i] = 0;
            for (int j = 0; j < width; j++) {
                answer[i] += (contents[j][i] * x.contents[j]);
            }
        }
        return new vector(answer);
    }
    
    public float estimateEigenvalue(vector x) {
        return (x.dot(vectorMultiply(x))) / (x.dot(x));
    }
    
    public boolean hasInverse() {
        return determinant != 0;
    }
    
    //designed to work on 2x2 matrices
    public void calculateInverse() {
        inverse.contents[1][1] = contents[2][2] * (1/determinant);
        inverse.contents[2][1] = contents[2][1] * (1/determinant) * -1;
        inverse.contents[1][2] = contents[1][2] * (1/determinant) * -1;
        inverse.contents[2][2] = contents[1][1] * (1/determinant);
    }
    
    public void calculateTrace() {
        for (int i = 0; i < height; i++) {
            trace += contents[i][i];
        }
    }
    
    public void addEigenvalue(float value) {
        eigenvalues[eigenvalueCount] = value;
        eigenvalueCount++;
    }
    
    public void addEigenvector(vector v) {
        eigenvectors[eigenvectorCount] = v;
        eigenvectorCount++;
    }
    
    public String toString() {
        return (
    }
}