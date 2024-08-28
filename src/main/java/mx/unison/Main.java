package mx.unison;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;


public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Usage: java Main <path> <keyword> ");
            System.exit(1);
        }

        String glob = "glob:**/*.java";

        String path = args[0];
        String keyword = args[2];

        try {
            match(glob, path, keyword);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void match(String glob, String location, String keyword) throws IOException {

        final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(glob);

        Files.walkFileTree(Paths.get(location), new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path path,
                                             BasicFileAttributes attrs) throws IOException {
                if (pathMatcher.matches(path)) {
                    System.out.println(path);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc)
                    throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void processFile(Path path, String keyword) throws IOException {


    }

}