package features.java9;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Scanners {

    public static void main(String[] args) {

        // tokens() -Returns a stream of delimiter-separated tokens from this scanner.
        // The stream contains the same tokens that would be returned, starting from
        // this scanner's current state, by calling the next method
        // repeatedly until the hasNext method returns false.
        // The resulting stream is sequential and ordered. All stream elements are non-null.
        // The following code will create a list of comma-delimited tokens from a string.
        // The resulting list would contain "abc", "def", the empty string, and "ghi".
        List<String> result = new Scanner("abc,def,,ghi")
                .useDelimiter(",")
                .tokens()
                .collect(Collectors.toList());

        // findAll() - Returns a stream of match results from this scanner
        // There is an overloaded method where you can pass the argument as String.
        // The resulting stream is sequential and ordered. All stream elements are non-null.
        // The following code will read a file and return a list of all sequences of characters consisting of seven 
        // or more Latin capital letters.
        try (var scanner = new Scanner(Path.of("input.txt"))) {
            Pattern pattern = Pattern.compile("[A-Z]{7,}");
            List<String> words = scanner.findAll(pattern)
                    .map(MatchResult::group)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
