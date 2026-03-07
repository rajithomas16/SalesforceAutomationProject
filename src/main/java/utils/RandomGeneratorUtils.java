package utils;

import java.util.Random;
import java.util.UUID;

public class RandomGeneratorUtils {
//UUID.randomUUID(): A UUID (Universally Unique Identifier) is a 36-character string that is mathematically guaranteed to be unique.
    //.toString().substring(0, 5): Since a full UUID is too long (e.g., 550e8400-e29b-41d4-a716-446655440000), we just grab the first 5 characters.
    public static String getRandomString(String prefix) {
        return prefix + "_" + UUID.randomUUID().toString().substring(0, 5);
    }
    //new Random().nextInt(1000): This generates a random number between 0 and 999.
    public static String getRandomEmail(String domain) {
        return "testuser" + new Random().nextInt(1000) + "@" + domain;
    }
}
