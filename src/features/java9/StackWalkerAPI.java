package features.java9;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// In Java 8, the Throwable::getStackTrace and Thread::getStackTrace returns an array of StackTraceElements.
// Without a lot of manual code, there was no way to discard the unwanted frames and keep only the ones we are interested in.
// In addition to this, the Thread::getStackTrace may return a partial stack trace.
// This is because the specification allows the VM implementation to omit some stack frames for the sake of performance.
// In Java 9, using the walk() method of the StackWalker, we can traverse a few frames that we are interested in or the complete stack trace.
// Of course, the new functionality is thread-safe; this allows multiple threads to share a single StackWalker instance
// for accessing their respective stacks.
// As described in the JEP-259, the JVM will be enhanced to allow efficient lazy access to additional stack frames when required.
public class StackWalkerAPI {

    public static void main(String[] args) {
        List<StackWalker.StackFrame> stackTrace = StackWalker.getInstance(StackWalker.Option.SHOW_HIDDEN_FRAMES)
                .walk(StackWalkerAPI::walkExample);
    }

    public static List<StackWalker.StackFrame> walkExample(Stream<StackWalker.StackFrame> stackFrameStream) {
        return stackFrameStream
                .filter(frame -> frame.getClassName().contains("features"))
                .collect(Collectors.toList());
    }

}
