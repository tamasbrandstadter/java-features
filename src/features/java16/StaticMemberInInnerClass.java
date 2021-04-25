package features.java16;

// Now you can declare static members in inner classes
public class StaticMemberInInnerClass {

    class InnerClass {
        private static final String STATIC_MESSAGE = "foo";

        static String staticMethod() {
            return STATIC_MESSAGE;
        }
    }

}
