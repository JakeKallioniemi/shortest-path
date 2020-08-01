package shortestpath.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Reads a file
 * 
 * @author Jake
 */
public class FileReader {

    /**
     * Reads a file.
     * 
     * @param file file to be read
     * @return files rows in a List
     * @throws IOException 
     */
    public static List<String> read(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }
}
