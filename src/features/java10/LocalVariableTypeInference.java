package features.java10;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocalVariableTypeInference {

    /* var can be used for:
            - variables within a method
            - resource variables for try-with-resources
            - loop variable in for-each loop
     */
    public static void main(String[] args) throws IOException {
        try (var input = new FileInputStream("test.txt")) {
            System.out.println(input.getChannel().size());
        }

        // the compiler infers the type from the elements
        var numbers = List.of(1, 2, 3, 4, 5);
        assert numbers instanceof ArrayList;

        // the compiler infers the type from collection passed
        var numbers2 = new ArrayList<>(numbers);

        // this will be an ArrayList<Object> because the compiler cannot infer a specific type
        var numbers3 = new ArrayList<>();

        for (var num : numbers) {
            System.out.println(num);
        }
    }

}
