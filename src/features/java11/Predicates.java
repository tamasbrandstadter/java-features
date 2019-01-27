package features.java11;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class Predicates {

    public static void main(String[] args) {
        Stream.of("a", "b", "", "c")
            // ugh, lambda ~> want to use method reference and negate it
            // .filter(string -> !string.isBlank())
            // compiler has no target for method reference ~> error
            // .filter((String::isBlank).negate())
            // ugh, cast ~> this is way worse than lambda
            //.filter(((Predicate<String>) String::isBlank).negate())
            // since JDK 11 you can do this:
            .filter(Predicate.not(String::isBlank))
            .forEach(System.out::println);
    }

}
