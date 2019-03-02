package features.java9;

import java.util.Arrays;

public class ArrayUtilities {

    public static void main(String[] args) {
        String[] a = new String[]{"apple", "ap", "app", "a"};
        String[] b = new String[]{"apple", "ap", "ba", "a"};

        // Finds and returns the index of the first mismatch between two Object arrays, otherwise return -1 if no mismatch is found.
        // The index will be in the range of 0 (inclusive) up to the length (inclusive) of the smaller array.
        System.out.println(Arrays.mismatch(a, b));
    }
}
