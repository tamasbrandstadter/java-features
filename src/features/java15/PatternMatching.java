package features.java15;

// https://openjdk.java.net/jeps/375
// https://openjdk.java.net/jeps/305
public class PatternMatching {

    // Pattern Matching for instanceof gives as a cleaner way to do this check, the instanceof operator is now extended to
    // take a type test pattern instead of just a type.
    public static boolean isJava15PatternMatching(Object o) {
        if (o instanceof String s) {
            return s.contains("Java 15 with Pattern Matching");
        }
        return false;
    }

}
