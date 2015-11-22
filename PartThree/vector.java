public class vector{
    public float[] contents;
    public int size;
    public float largest;
    
    public vector(float[] vector) {
        contents = vector;
        size = vector.length;
    }
    
    public vector() {
        
    }
    
    public vector scale() {
        float[] answer = new float[input.length];
        float max = 0;
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
    
    public float dot(vector v) {
        float[] answer = 0
        for (int i = 0; i < v.length; i++) {
            float += v.contents[i] * contents[i];
        }
        return answer;
    }
}