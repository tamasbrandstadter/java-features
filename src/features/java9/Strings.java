package features.java9;

public class Strings {

    public static void main(String[] args) {
        // chars() - This method returns a stream of int zero-extending the char values from this sequence.
        // Any char which maps to a surrogate code point is passed through uninterpreted.
        // If the sequence is mutated while the stream is being read, the result is undefined.
        String s = "Programming With Java";
        s.chars().mapToObj(i -> (char) i).forEach(System.out::println);

        // codePoints() - Returns a stream of code point values from this sequence.
        String str = "Greek Alphabets α-Ω";
        str.codePoints().forEach(x -> System.out.print(new StringBuilder().appendCodePoint(Character.toChars(x)[0]).toString()));
    }

}
