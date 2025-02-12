package com.example.Ninsho;
import java.util.Random;

public class RandomGenerator {
    public static final String NUM     = "0123456789";
    public static final String ALPHA_L = "abcdefghijklmnopqrstuvwxyz";
    public static final String ALPHA_U = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALL_PATTERN = NUM + ALPHA_L + ALPHA_U;
    public static String generateRandom(int count){
        String result = "";
        Random random = new Random();
        for(var ignored : new int[count]){
            int pos = random.nextInt(ALL_PATTERN.length());
            result += ALL_PATTERN.charAt(pos);
        }
        return result;
    }
}