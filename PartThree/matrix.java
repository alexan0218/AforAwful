import java.util.Random;

public class matrix{
    public float[][] contents;
    public int height;
    public int width;
    public float trace;
    public float[] eigenvalues;
    public float determinant;
    
    public matrix(float[][] matrix) {
        contents = matrix;
        height = contents.length;
        width = contents[0].length;
        determinant = calculateDeterminant();
    }
    //generate random 2x2 matrix with floats in [-2,2]
    public matrix() {
        Random rando = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                contents[j][i] = rando.nextFloat() * 4;
            }
        }
        height = contents.length;
        width = contents[0].length;
        determinant = calculateDeterminant();
    }
    
    public float calculateDeterminant() {
        if (width == 2 && height == 2) {
            determinant = (contents[1][1] * contents[2][2]) - (contents[1][2] * contents[2][1]);
        }
        return determinant;
    }
    
    public vector vectorMultiply(vector x) {
        if (x.length != width) {
            System.out.println("size mismatch between vector and matrix being multiplied");
            return null;
        }
        float[] answer = new float[height];
        for (int i = 0; i < height; i++) {
            answer[i] = 0;
            for (int j = 0; j < width; j++) {
                answer[i] += (contents[j][i] * x[j]);
            }
        }
        return new vector(answer);
    }
    
    public float estimateEigenvalue(vector x) {
        return (x.dot(vectorMultiply(x))) / (x.dot(x));
    }
}