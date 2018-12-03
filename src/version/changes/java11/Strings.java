package version.changes.java11;

public class Strings {

    public static void main(String[] args) {
        var fooBar = "foo bar";
        // repeat allows concatenating a String with itself a given number of times
        var result = fooBar.repeat(2);
        System.out.println(result);

        var foo = "foo";
        // if you try to repeat a String 0 times, you will always get an empty String
        var result2 = foo.repeat(0);
        assert result2.equals("");

        var emptyString = "";
        var result3 = emptyString.repeat(Integer.MAX_VALUE);

        assert (result3).equals("");

        // we can check if a String instance is empty or contains whitespace
        var blank = " ".isBlank();
        System.out.println(blank);

        // we can easily get rid of all leading and trailing whitespace from each String now by strip() which is a Unicode-aware alternative to trim()
        // It uses Java 5’s Character::isWhitespace to determine what to strip.
        // more info: https://bugs.openjdk.java.net/browse/JDK-8200378
        assert("  f oo  ".strip()).equals("f oo");

        // we can narrow the operation only to trailing/leading whitespace
        assert("  f oo  ".stripLeading()).equals("f oo  ");
        assert("  f oo  ".stripTrailing()).equals("  f oo");

        // split a String instance into a Stream<String> of separate lines:
        // instead of splitting a String and converting it into a Stream,
        // specialized Spliterators were implemented(one for Latin and one for UTF-16 Strings) that make it possible to stay lazy.
        // The streamed lines never contain the line terminator itself.
        // They can be empty ( "like\n\nin this\n\ncase", which has 5 lines),
        // but the line at the end of the string will be ignored if its empty ( "like\nhere\n"; 2 lines).
        String s = "This\r\nis a\r\nmultiline\r\nstring";
        s.lines().map(line -> "// " + line).forEach(System.out::println);
    }

}
