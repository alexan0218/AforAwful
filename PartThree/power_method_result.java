public class power_method_result {
    public float eigenvalue;
    public float[] eigenvector;
    public int iterations;
    
    public power_method_result(float eigenvalueIn, float[] eigenvectorIn, int iterationsIn) {
        eigenvalue = eigenvalueIn;
        eigenvector = eigenvectorIn;
        iterations = iterationsIn;
    }
}