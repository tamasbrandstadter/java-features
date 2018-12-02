package version.changes.java9;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ProcessAPI {

    public static void main(String[] args) throws IOException {
        ProcessHandle current = ProcessHandle.current();
        long pid = current.pid();
        System.out.println(pid);

        // get snapshot of the process info
        ProcessHandle.Info info = current.info();

        // details of process
        Optional<String> user = info.user();
        user.ifPresent(System.out::println);

        Optional<String> command = info.command();
        command.ifPresent(System.out::println);

        Optional<String> commandLine = info.commandLine();
        // ifPresentOrElse() was also added in Java 9
        commandLine.ifPresentOrElse(System.out::println, () -> System.out.println("CommandLine was empty"));

        Optional<String[]> arguments = info.arguments();
        arguments.ifPresent(System.out::println);

        Optional<ProcessHandle> parent = current.parent();
        parent.ifPresentOrElse(System.out::println, () -> System.out.println("Parent was empty"));

        Stream<ProcessHandle> children = current.children();
        children.forEach(System.out::println);

        // create process
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", "process.jar");
        Process process = pb.start();

        // will be created after process ended
        CompletableFuture<Process> future = process.onExit();
        future.thenAccept(p -> System.out.println("Process terminated, pid: " + p.pid()));
        future.whenCompleteAsync((p, e) -> System.out.println("Completed"));

        // kill process
        process.destroy();

        // kill a running process which was created by someone else
        Optional<ProcessHandle> optPh = ProcessHandle.of(2323);
        optPh.ifPresent(ProcessHandle::destroy);

        // all running processes
        Stream<ProcessHandle> processes = ProcessHandle.allProcesses();
        processes.forEach(System.out::println);

    }

}
