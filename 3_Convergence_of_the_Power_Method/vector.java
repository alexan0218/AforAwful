public class vector{
    public double[] contents;
    public int size;
    //public double largest;
    
    public vector(double[] vector) {
        contents = vector;
        size = vector.length;
    }
    
    public vector() {
        
    }
    
    public vector scale() {
        double[] answer = new double[contents.length];
        double max = contents[0];
        for (int i = 0; i < contents.length; i++) {
            if (contents[i] > max) {
                max = contents[i];
            }
        }
        for (int i = 0; i < contents.length; i++) {
            answer[i] = contents[i] / max;
        }
        return new vector(answer);
    }
    
    public double dot(vector v) {
        double answer = 0;
        for (int i = 0; i < v.size; i++) {
            answer += v.contents[i] * contents[i];
        }
        return answer;
    }
    
    public String toString() {
        String ans = "";
        for (int i = 0; i < contents.length; i++) {
            ans += "\t" + contents[i] + "\n";
        }
        return ans;
    }
}