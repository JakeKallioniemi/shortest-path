package shortestpath;

import java.io.File;
import java.io.IOException;
import java.util.List;
import shortestpath.benchmark.Scenario;
import shortestpath.io.FileReader;
import shortestpath.util.Parser;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> mapData = FileReader.read(new File("maps/arena.map"));
        List<String> scenarioData = FileReader.read(new File("maps/arena.map.scen"));
        
        Scenario scenario = Parser.buildScenario(scenarioData, mapData);
        scenario.runScenario(10);
        
        System.out.println(scenario.allPathsFound());
        System.out.println(scenario.getAvgSetupTime());
        System.out.println(scenario.getAvgRunTime());
    }
}
