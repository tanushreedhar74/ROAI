package com.tanushree.vectordb;

public class DistanceUtils {
    //there are 3 methods to ca distances
    //ecludian,manhattan,cosine
    public static float cosine(float[]a,float[]b){
        //formula=A.B/|A||B|;
        float dot=0;
        float normA=0;
        float normB=0;
        for(int i=0;i<a.length;i++){
            dot +=a[i]*b[i];
            normA +=a[i]*a[i];
            normB +=b[i]*b[i];
        }
        return 1-(float)(dot/(Math.sqrt(normA)*(Math.sqrt(normB))));
    }
    public  static float  euclidean(float[]a,float[]b){
        float sum=0;
        for(int i=0;i<a.length;i++){
            float diff=a[i]-b[i];
            sum += diff * diff;   // (xi - yi)^2
        }
        return (float) (Math.sqrt(sum));
    }
    public static float manhattan(float[]a,float[]b){
        float sum=0;
        for(int i=0;i<a.length;i++){
            float diff=a[i]-b[i];
            sum+=Math.abs(diff);
        }
        return sum;
    }
}
