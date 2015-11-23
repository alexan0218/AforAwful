public class power_method{
    public static power_method_result PowerIterate(matrix A, vector v, float tolerance, int iterationCap) {
        float newEigen = 0;
        float oldEigen = 0;
        int currentIteration = 0;
        vector temp = v;
        do {
            temp = A.vectorMultiply(temp);
            temp = temp.scale();
            newEigen = A.estimateEigenvalue(v);
            currentIteration++;
            if (Math.abs(newEigen - oldEigen) < tolerance) {
                A.addEigenvalue(newEigen);
                A.iterations = currentIteration;
                A.addEigenvector(temp);
                A.calculateTrace();
                return new power_method_result(newEigen, temp, currentIteration, A.trace);
            }
            oldEigen = newEigen;
        } while (currentIteration < iterationCap);
        return null;
    }
    
    public static void main(String[]args) {
        float[] defaultVectorData = {1, 1};
        vector defaultVector = new vector(defaultVectorData);
        float tolerance = .00005f;
        int iterationCap = 100;
        matrix[] matrixContainer = new matrix[10];
        power_method_result[] original = new power_method_result[matrixContainer.length];
        power_method_result[] inverse = new power_method_result[matrixContainer.length];
        for (int i = 0; i < matrixContainer.length; i++) {
            do{
                matrixContainer[i] = new matrix();
                matrixContainer[i].calculateDeterminant();
            } while (!matrixContainer[i].hasInverse());
           //matrixContainer[i].calculateTrace();
           original[i] = PowerIterate(matrixContainer[i], defaultVector, tolerance, iterationCap);
           matrixContainer[i].calculateInverse();
           inverse[i] = PowerIterate(matrixContainer[i].inverse, defaultVector, tolerance, iterationCap);
        }
    }
}