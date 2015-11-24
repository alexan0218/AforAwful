public class power_method_result {
    public double eigenvalue;
    public vector eigenvector;
    public int iterations;
    public double trace;
    
    public power_method_result(double eigenvalueIn, vector eigenvectorIn, int iterationsIn, double t) {
        eigenvalue = eigenvalueIn;
        eigenvector = eigenvectorIn;
        iterations = iterationsIn;
        trace = t;
    }
    
    public power_method_result(int iterationsIn, double t) {
        eigenvalue = 0;
        eigenvector = null;
        iterations = iterationsIn;
        trace = t;
    }
    
    public String toString() {
        if (eigenvector == null) {
            return ("Eigenvalue: null; iterations: " + iterations + "; trace: " + trace + ";\neigenvector = null\n");
        }
        return ("Eigenvalue: " + eigenvalue + "; iterations: " + iterations + "; trace: " + trace + ";\neigenvector = \n" + eigenvector.toString() + "\n");
    }
}