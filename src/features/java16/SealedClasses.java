package features.java16;

// This was introduced as a preview feature in JDK 15 and continues as one in JDK 16.
// There are three changes:
// Adding new language syntax often requires new keywords. Adding these as reserved words to the language
// (like assert in JDK 1.4) can seriously impact backwards compatibility since these words are no longer valid as identifiers.
// We have had the concept of restricted types and restricted keywords (like var and sealed) to work around this.
// This JEP introduces the concept of contextual keywords.  Specifically, for JDK 16, sealed, non-sealed and permits are contextual keywords.
// As with anonymous classes and lambda expressions, local classes may not be subclasses of sealed classes when determining
// the implicitly declared permitted subclasses of a sealed class or interface.
// The narrowing reference conversion definition in the JLS has been extended to navigate sealed hierarchies to
// determine which conversions are not possible at compile time.
public class SealedClasses {
}
