import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.lang.System.currentTimeMillis;

// Source code file watcher
/*
####################################
### Add an alias into your ~/.bashrc
####################################
cat << EOF >> ~/.bashrc
alias watch='(
cd C:/Users/User/IdeaProjects/java-certification || exit && \
javac -cp target -d target src/SourceCodeFileWatcher.java && \
java -Dfile.encoding=UTF8 -Xms8m -Xmx16m -XX:MetaspaceSize=32m -XX:MaxMetaspaceSize=64m -cp target SourceCodeFileWatcher src
)'
EOF

####################################
### Reload shell environment
####################################
source ~/.bashrc
*/
public final class SourceCodeFileWatcher {
  private static final int THREAD_DELAY = 1000;
  private static final int TRAVERSE_MAX_DEPTH = 10;
  private static String[] sourceDirectories;

  // create a single scheduled thread
  public static void main(String... args) {
    if (args.length == 0) throw new IllegalArgumentException("No source code directories provided");

    Arrays.stream(args).map(Path::of).forEach(SourceCodeFileWatcher::createDirectories);

    sourceDirectories = args;

    System.out.format("Instantiation \t\t pid \t %s%n", ProcessHandle.current().pid());
    System.out.format("Directory \t\t path \t %s%n", System.getProperty("user.dir"));

    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    service.scheduleWithFixedDelay(
        () -> traversePath(args), 0, THREAD_DELAY, TimeUnit.MILLISECONDS);
  }

  // create missing directories
  private static void createDirectories(Path path) {
    if (!Files.exists(path))
      try {
        System.out.println("Created " + Files.createDirectories(path));
      } catch (IOException e) {
        e.printStackTrace();
      }
  }

  // find any files that have been modified x seconds ago
  private static void traversePath(String... paths) {
    Stream.of(paths)
        .distinct()
        .map(Path::of)
        .filter(Files::exists)
        .forEach(SourceCodeFileWatcher::filterCompileExecute);
  }

  // filter path then compile source and execute target files
  private static void filterCompileExecute(Path path) {
    BiPredicate<Path, BasicFileAttributes> predicate =
        (file, attributes) -> {
          String fileName = file.getFileName().toString();
          String selfName = SourceCodeFileWatcher.class.getSimpleName();

          if (fileName.contains(selfName)
              || !attributes.isRegularFile()
              || !fileName.endsWith(".java")) {
            return false;
          }

          if (attributes.lastModifiedTime().toMillis() > (currentTimeMillis() - THREAD_DELAY)) {
            System.out.format("Detection \t\t path \t %s%n", file.toString());

            return true;
          }

          return false;
        };

    try (Stream<Path> stream =
        Files.find(path, TRAVERSE_MAX_DEPTH, predicate, FileVisitOption.FOLLOW_LINKS)) {

      stream.sorted().map(Path::getFileName).forEach(SourceCodeFileWatcher::compileExecute);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // compile with javac and execute with java
  private static void compileExecute(Path path) {
    String fileName = path.toString();

    for (String sourceDirectory : sourceDirectories) {
      String sourcePath = String.format("%s/%s", sourceDirectory, fileName);
      String targetPath = fileName.replace(".java", "");

      if (!Files.exists(Path.of(sourcePath))) {
        continue;
      }

      long begin = currentTimeMillis();

      int code =
          createProcess(
              () -> "Compilation",
              "javac",
              "-Xlint:unchecked",
              "-Xdiags:verbose",
              "-cp",
              "target;resources",
              "-d",
              "target",
              sourcePath);

      if (code == 0)
        createProcess(
            () -> "Execution",
            "java",
            "-Dfile.encoding=UTF-8",
            "-cp",
            "target;resources",
            targetPath);
      else System.out.println("Execution ignored");

      System.out.format("Completion \t\t duration %s s%n", (currentTimeMillis() - begin) / 1000.00);
    }

    System.out.println("...".repeat(24));
  }

  // create a process and handle the output
  private static int createProcess(Supplier<String> label, String... parts) {
    ProcessBuilder processBuilder = new ProcessBuilder(parts);

    try {
      Process process = processBuilder.start();

      System.out.format("%s \t\t pid \t %s%n", label.get(), process.pid());

      Stream.of(process.getErrorStream(), process.getInputStream())
          .flatMap(o -> new BufferedReader(new InputStreamReader(o)).lines())
          .forEach(System.out::println);

      return process.onExit().thenApply(Process::exitValue).get();
    } catch (ExecutionException | InterruptedException | IOException e) {
      e.printStackTrace();
    }

    return 1;
  }
}