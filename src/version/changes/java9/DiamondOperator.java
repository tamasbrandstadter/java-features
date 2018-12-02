package version.changes.java9;

import version.changes.MyClass;

public class DiamondOperator {

    public static void main(String[] args) {
        // pre Java 6
        MyClass<String> c1 = new MyClass<String>("Frank") {
            @Override
            public void processData() {
                System.out.println("Processing " + getData());
            }
        };
        c1.processData();

        // Allow the diamond operator with anonymous classes
        // if the argument type of the inferred type is denotable
        MyClass<String> c2 = new MyClass<>("James") {
            @Override
            public void processData() {
                System.out.println("Processing " + getData());
            }
        };
        c2.processData();

        MyClass<Integer> c3 = new MyClass<>(1000) {
            @Override
            public void processData() {
                System.out.println("Processing " + getData());
            }
        };
        c3.processData();

        // Cannot infer the type of a non-denotable type (intersection type)
        // MyClass<T extends Comparable<T> & Serializable> c4 = new MyClass<> {};
    }

}
