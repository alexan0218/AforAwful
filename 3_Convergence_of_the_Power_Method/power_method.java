import java.io.*;
public class power_method{
    public static power_method_result PowerIterate(matrix A, vector v, double tolerance, int iterationCap) {
        double oldEigen;
        double newEigen = 0;
        int i = 0;
        
        vector eigenVector = v.scale();
        
        do {
            oldEigen = newEigen;
            
            vector temp = A.vectorMultiply(eigenVector); //Ax
            
            eigenVector = temp.scale(); //normalize
            
            newEigen = A.estimateEigenvalue(eigenVector);
            //estimateEigenvalue is a function that uses the Rayleigh quotient to get an eigenvalue using a matrix and an eigenVector.
            
            i++;
        } while(i == 1 || (Math.abs(newEigen - oldEigen) > tolerance && i < iterationCap));
        A.iterations = i;
        A.calculateTrace();
        if (i == 100) {
            return new power_method_result(i, A.trace);
        }
        A.addEigenvalue(newEigen);
        A.addEigenvector(eigenVector);
        return new power_method_result(newEigen, eigenVector, i, A.trace);
    }
    
    public static vector generateDefaultVector(int size) {
        double[] answer = new double[size];
        for (int i = 0; i < size; i++) {
            answer[i] = 1;
        }
        return new vector(answer);
    }
    
    public static void main(String[]args) throws IOException {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Takes two filenames: first as the file to read matrices from, second as the one to read vectors from.  Second is optional.");
            return;
        }
        MatrixScanner MS = new MatrixScanner();
        double[][][] matrixArray = MS.readMatrix(new File(args[0]));
        double[] vectorArray;
        vector defaultVector;

        double tolerance = .00005f;
        int iterationCap = 100;
        matrix[] matrixContainer = new matrix[matrixArray.length];
        power_method_result[] original = new power_method_result[matrixContainer.length];
        power_method_result[] inverse = new power_method_result[matrixContainer.length];
        
        for (int i = 0; i < matrixContainer.length; i++ ) {
            matrixContainer[i] = new matrix(matrixArray[i]);
            matrixContainer[i].transpose();//because I was dumb and designed this with column major backing.
            matrixContainer[i].calculateDeterminant();
            if (args.length != 2) {
                defaultVector = generateDefaultVector(matrixContainer[i].contents.length);
            } else {
                VectorScanner VS = new VectorScanner();
                vectorArray = VS.readVector(new File(args[1]));
                defaultVector = new vector(vectorArray);
            }
            original[i] = PowerIterate(matrixContainer[i], defaultVector, tolerance, iterationCap);
            matrixContainer[i].calculateInverse();
            //inverse[i] = PowerIterate(matrixContainer[i].inverse, defaultVector, tolerance, iterationCap);
        }
        
    
        /*double[] defaultVectorData = {1, 1};
        vector defaultVector = new vector(defaultVectorData);
        double tolerance = .00005f;
        int iterationCap = 100;
        matrix[] matrixContainer = new matrix[1000];
        power_method_result[] original = new power_method_result[matrixContainer.length];
        power_method_result[] inverse = new power_method_result[matrixContainer.length];
        for (int i = 0; i < matrixContainer.length; i++) {
            do{
                matrixContainer[i] = new matrix();
                matrixContainer[i].calculateDeterminant();
            } while (!matrixContainer[i].hasInverse());
            original[i] = PowerIterate(matrixContainer[i], defaultVector, tolerance, iterationCap);
            matrixContainer[i].calculateInverse();
            inverse[i] = PowerIterate(matrixContainer[i].inverse, defaultVector, tolerance, iterationCap);
        }*/
        /*System.out.println("Matrices:\n");
        for (int i = 0; i < matrixContainer.length; i++) {
            System.out.println(i + ":\n" + matrixContainer[i].toString() + "\n\n");
        }
        System.out.println("results for originals:\n");
        for (int i = 0; i < matrixContainer.length; i++) {
            System.out.println((i+1) + ":\n" + original[i].toString() + "\n");
        }
        /*System.out.println("results for inverses:\n");
        for (int i = 0; i < matrixContainer.length; i++) {
            System.out.println(i + ":\n" + inverse[i].toString() + "\n");
        }*/
        
        
        File outputFile = new File("Power Method Results.txt");
        OutputStream stream = new FileOutputStream(outputFile);
        stream.write(("results for originals:\n").getBytes());
        for (int i = 0; i < matrixContainer.length; i++) {
            if (matrixContainer[i].iterations != 100) stream.write(((i+1) + ":\n" + original[i].toString() + "\n").getBytes());
        }
        stream.flush();
        stream.close();
        /*outputFile = new File("Inverse Power Method Results.txt");
        stream = new FileOutputStream(outputFile);
        for (int i = 0; i < matrixContainer.length; i++) {
            if (matrixContainer[i].iterations != 100 && matrixContainer[i].inverse.determinant < 25 && matrixContainer[i].inverse.determinant > -25 ) stream.write(("Determinant: " + matrixContainer[i].inverse.determinant + "\t" + matrixContainer[i].inverse.trace + "\t" + (matrixContainer[i].inverse.iterations/10 + 1) + "\n").getBytes());
        }
        stream.flush();
        stream.close();*/
    }
}























