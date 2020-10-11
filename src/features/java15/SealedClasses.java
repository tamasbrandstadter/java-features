package features.java15;

// https://openjdk.java.net/jeps/360
public class SealedClasses {

    // Enhances the Java programming language with sealed classes and interfaces.
    // Sealed classes and interfaces restrict which other classes or interfaces may extend or implement them.
    public abstract sealed class Shape permits Circle, Rectangle, Triangle {
        double area;
    }

    public final class Circle extends Shape {
        double radius;
    }

    public final class Rectangle extends Shape {
        double a;
        double b;
    }

    // A permitted subclass may be declared non-sealed so that its part of the hierarchy reverts to being open for extension by unknown subclasses.
    public non-sealed class Triangle extends Shape {
        String name;
    }

    // Compile error
    // public class Triangle extends Shape {
    // }

    // No need for default case if all permitted types are covered
    // public double area(Shape shape) {
    //    return switch (shape) {
    //        case Circle c -> Math.pow(c.radius, 2) * Math.PI;
    //        case Rectangle r -> r.a * r.b;
    //    };
    //}

}
