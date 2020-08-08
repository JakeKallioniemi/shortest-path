package shortestpath;

import java.io.File;
import java.io.IOException;
import java.util.List;
import shortestpath.benchmark.Scenario;
import shortestpath.domain.AStar;
import shortestpath.domain.Dijkstra;
import shortestpath.io.FileReader;
import shortestpath.util.Parser;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> mapData = FileReader.read(new File("maps/arena.map"));
        List<String> scenarioData = FileReader.read(new File("maps/arena.map.scen"));

        Scenario dijkstraBenchmark = Parser.buildScenario(scenarioData, mapData, new Dijkstra());
        dijkstraBenchmark.runScenario(100);

        Scenario aStarBenchMark = Parser.buildScenario(scenarioData, mapData, new AStar());
        aStarBenchMark.runScenario(100);

        System.out.println("Dijkstra's algorithm:");
        System.out.println("Successful: " + dijkstraBenchmark.allPathsFound());
        System.out.println("Setup time: " + dijkstraBenchmark.getAvgSetupTime());
        System.out.println("Run time: " + dijkstraBenchmark.getAvgRunTime());

        System.out.println("---------------------");
        
        System.out.println("A* algorithm:");
        System.out.println("Successful: " + aStarBenchMark.allPathsFound());
        System.out.println("Setup time: " + aStarBenchMark.getAvgSetupTime());
        System.out.println("Run time: " + aStarBenchMark.getAvgRunTime());
    }
}
