package features.java16;

// The only change to Records from the JDK 15 implementation is to relax the longstanding restriction whereby an inner class
// cannot declare a member that is explicitly or implicitly static.  This is very useful for simplifying certain stream operations.
// Often it is desirable to have a stream pass more than one object for each element.
// Now, a local record can be defined reducing the type pollution that would otherwise result.
public class Records {
}
