public class power_method{
    public power_method_result PowerIterate(matrix A, vector v, float tolerance, int iterationCap) {
        float newEigen = 0;
        float oldEigen = 0;
        int currentIteration = 0;
        vector temp = v;
        do {
            temp = A.vectorMultiply(temp);
            temp.scale();
            float newEigen = A.estimateEigenvalue(v);
            currentIteration++;
            if (Math.abs((newEigen - oldEigen)/newEigen) {
                return new power_method_result(newEigen, temp, currentIteration);
            }
            oldEigen = newEigen;
        } while (currentIteration < iterationCap);
        return null;
    }
    
    
}