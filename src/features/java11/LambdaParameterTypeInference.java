package features.java11;

public class LambdaParameterTypeInference {

    public static void main(String[] args) {
        // more info: https://openjdk.java.net/jeps/323

        // In Java 10, using the var for type inference was forbidden when declaring the parameter list of implicitly typed lambda expressions.
        // In Java 11 we can do things like this:
        Math multiply = (var x, var y) -> x * y;

        // which is equivalent to:
        // Math multiply = (double x, double y) -> x * y;
        // Math multiply = (x, y) -> x * y;

        // vars also could be final in Java 11
        final Math finalMultiply = (final var x, final var y) -> x * y;

        // illegal cases:
        // semi-explicit and semi-implicit types
        // Math divide = (var x, double y) -> x / y;

        // mixing implicit types
        // Math divide = (var x, y) -> x / y;

        // modifier on implicit type
        // Math divide = (final x, final y) -> x / y;
    }

}
