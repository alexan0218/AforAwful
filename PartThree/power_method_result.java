public class power_method_result {
    public float eigenvalue;
    public vector eigenvector;
    public int iterations;
    public float trace;
    
    public power_method_result(float eigenvalueIn, vector eigenvectorIn, int iterationsIn, float t) {
        eigenvalue = eigenvalueIn;
        eigenvector = eigenvectorIn;
        iterations = iterationsIn;
        trace = t;
    }
}