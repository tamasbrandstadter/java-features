package features.java11;

import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FactoryMethods {

    public static void main(String[] args) {
        // They can be deemed to be the canonical choice as both Paths::get methods forward to them.
        Path tmp = Path.of("/home/nipa", "tmp");
        Path codefx = Path.of(URI.create("http://codefx.org"));

        // How do you turn a collection into an array?
        // before Java 11
        // check this for performance: https://shipilev.net/blog/2016/arrays-wisdom-ancients/
        List<String> list = new ArrayList<>();
        Object[] objects = list.toArray();
        String[] strings = list.toArray(new String[0]);
        String[] stringsWithSize = list.toArray(new String[list.size()]);

        // in Java 11 There’s a new overload of Collection::toArray that takes an IntFunction<T[]>,
        // i.e. a function that accepts the length of the array to produce as input and returns an array of that size.
        // That can be expressed succinctly as a constructor reference T[]::new (for concrete T).
        String[] stringsFun = list.toArray(String[]::new);
    }

}
