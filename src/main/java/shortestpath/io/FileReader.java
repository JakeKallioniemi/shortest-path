package shortestpath.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import shortestpath.util.List;

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
        List<String> lines = new List<>();
        BufferedReader reader = Files.newBufferedReader(file.toPath());
        while (reader.ready()) {
            lines.add(reader.readLine());
        }
        return lines;
    }
}
