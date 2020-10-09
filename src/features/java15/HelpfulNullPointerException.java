package features.java15;

// https://bugs.openjdk.java.net/browse/JDK-8233014
public class HelpfulNullPointerException {

    private String s;

    public void printStringLength() {
        System.out.println(s.length());
    }

    // this would result NPE with message:
    // Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "this.s" is null
    public static void main(String[] args) {
        HelpfulNullPointerException exception = new HelpfulNullPointerException();
        exception.printStringLength();
    }

}
