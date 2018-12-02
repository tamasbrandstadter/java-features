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
    }

}
