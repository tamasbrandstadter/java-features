package features.java15;

public class Strings {

    public static void main(String[] args) {
        // The stripIndent method removes incidental white space from a multi-line string,
        // using the same algorithm used by the Java compiler. This is useful if you have a program that reads text as input data
        // and you want to strip indentation in the same manner as is done for text blocks.
        String html = """
 ..............<html>
 ..............    <body>
 ..............        <p>Hello, world</p>
 ..............    </body>
 ..............</html>
 ..............""";
        System.out.println(html);
        System.out.println(html.stripIndent());

        // The translateEscapes method performs the translation of escape sequences (\b, \f, \n, \t, \r, \", \', \\ and octal escapes)
        // and is used by the Java compiler to process text blocks and string literals.
        // This is useful if you have a program that reads text as input data and you want to perform escape sequence processing.
        // Note that Unicode escapes are not processed.
        System.out.println(html.translateEscapes());

        // The formatted method is equivalent to String.format(this, args).
        // The advantage is that, as an instance method, it can be chained off the end of a text block:
        String name = "test";
        String phone = "111";
        String address = "222";
        double salary = 454654.54536;
        String output = """
            Name: %s
            Phone: %s
            Address: %s
            Salary: $%.2f
            """.formatted(name, phone, address, salary);
        System.out.println(output);
    }

}
