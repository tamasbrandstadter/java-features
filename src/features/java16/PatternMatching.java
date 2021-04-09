package features.java16;

// There are two minor changes to how the feature worked in JDK 15.
// The first is that pattern variables are no longer implicitly final.
// This is a very logical change since local variables are not treated as implicitly final.
// The second change is that is now a compile-time error for a pattern instanceof expression to compare an expression of
// type S against a pattern of type T, where S is a subtype of T. Here’s an example:
public class PatternMatching {

    public static void main(String[] args) {
//        var a = new ArrayList<String>();
//        compile time error: Error: pattern type java.util.List is a subtype of expression type java.util.ArrayList<java.lang.String>
//        if (a instanceof List l) {
//            System.out.println(l.size());
//        }
    }

}
