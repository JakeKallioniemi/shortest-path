package shortestpath.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import shortestpath.datastructures.List;

/**
 * Reads a file.
 * 
 * @author Jake
 */
public class FileReader {

    /**
     * Reads a file line by line into a list.
     * 
     * @param file file to be read
     * @return files rows in a list
     * @throws IOException thrown when file can not be read
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
