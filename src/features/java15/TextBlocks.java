package features.java15;

// https://openjdk.java.net/jeps/378
// https://openjdk.java.net/jeps/368
// https://openjdk.java.net/jeps/355
public class TextBlocks {

    // Add text blocks to the Java language. A text block is a multi-line string literal that avoids the need for most
    // escape sequences, automatically formats the string in a predictable way,
    // and gives the developer control over the format when desired.
    String html = """
            <html>
                <body>
                    <p>Hello, world</p>
                </body>
            </html>
            """;
}
