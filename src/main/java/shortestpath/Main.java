package shortestpath;

import java.io.File;
import java.io.IOException;
import java.util.List;
import shortestpath.domain.Dijkstra;
import shortestpath.domain.Graph;
import shortestpath.domain.Node;
import shortestpath.io.FileReader;
import shortestpath.util.Parser;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> contents = FileReader.read(new File("maps/arena.map"));
        Graph graph = Parser.buildGraph(contents);
        Dijkstra dijkstra = new Dijkstra();
        double length = dijkstra.search(graph, new Node(1, 13), new Node(4, 12));
        System.out.println("Length of shortest path is: " + length);
    }
}
