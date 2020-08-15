package shortestpath.ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import shortestpath.benchmark.Scenario;
import shortestpath.domain.AStar;
import shortestpath.domain.Dijkstra;
import shortestpath.io.FileReader;
import shortestpath.util.List;
import shortestpath.util.Parser;

public class TextUI {

    private Scanner in;

    public TextUI(Scanner in) {
        this.in = in;
    }

    public void start() {
        System.out.println("Shortest path\n");
        while (true) {
            System.out.print("Filename: ");
            String filename = in.nextLine();
            String mapFilePath = "maps/" + filename + ".map";
            String scenFilePath = "maps/" + filename + ".map.scen";

            List<String> mapData;
            List<String> benchmarkData;
            try {
                mapData = FileReader.read(new File(mapFilePath));
                benchmarkData = FileReader.read(new File(scenFilePath));
            } catch (IOException e) {
                System.out.println("File doesn't exist or couldn't be read");
                continue;
            }
            Scenario dijkstra = Parser.buildScenario(benchmarkData, mapData, new Dijkstra());
            Scenario aStar = Parser.buildScenario(benchmarkData, mapData, new AStar());

            int runs = runs();

            dijkstra.runScenario(runs);
            aStar.runScenario(runs);

            System.out.println("-----Benchmark-----\n");     
            System.out.println("Map: " + filename + "\n");
            printResults(dijkstra, "Dijkstra's algorithm");
            printResults(aStar, "A*");
            System.out.println("-------------------");
        }
    }

    private int runs() {
        while (true) {
            System.out.println("Runs: ");
            String runCount = in.nextLine();
            try {
                return Integer.parseInt(runCount);
            } catch (NumberFormatException e) {
                System.out.println("Invalid");
            }
        }
    }

    private void printResults(Scenario scenario, String algoName) {
        System.out.println(algoName);
        System.out.println("All paths found: " + scenario.allPathsFound());
        System.out.println("Average setup time: " + scenario.getAvgSetupTime());
        System.out.println("Average run time: " + scenario.getAvgRunTime());
        System.out.println("");
    }
}
