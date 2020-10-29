package features.java12;

import java.util.Optional;

// https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/lang/String.html
public class Strings {

    // The new indent adjusts the indentation of each line of this string.
    // If n > 0, this method inserts 'n' number of space characters (U+00200).
    //Before inserting spaces, the input string is separated into lines. Each line is inserted with 'n' spaces,
    // then suffixed with a line feed "\n" (U+000A). The resulting lines are then concatenated and returned.
    // If 'n' is negative then n number of strings are removed.
    // more info: https://www.logicbig.com/tutorials/core-java-tutorial/java-12-changes/string-changes.html
    public static String indent(String s, int n) {
        System.out.println("s before indent " + s);
        String indented = s.indent(n);
        System.out.println("s after indent " + indented);
        return indented;
    }

    // The new transform method allows us to apply a lambda function to a String.
    public static void parse(String s) {
        Integer i = s.transform(Integer::parseInt);
        System.out.println(i);
    }

    // Java 12 introduced the new Constable which is implemented by String class.
    // It represents a type which is constable. A constable type is one whose values are constants that can be represented
    // in the constant pool of a Java.
    // Classfile as described in JVMS 4.4, and whose instances can describe themselves nominally as a ConstantDesc
    // via resolveConstantDesc() method.
    // describeConstable() returns an Optional containing the nominal descriptor for the instance, which is the instance itself.
    public static Optional<String> describeConstable(String s) {
        return s.describeConstable();
    }

}
