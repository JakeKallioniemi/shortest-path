package shortestpath.ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import shortestpath.benchmark.DataStructureComparer;
import shortestpath.benchmark.Result;
import shortestpath.benchmark.Scenario;
import shortestpath.datastructures.Graph;
import shortestpath.datastructures.Grid;
import shortestpath.datastructures.List;
import shortestpath.domain.AStar;
import shortestpath.domain.Dijkstra;
import shortestpath.domain.JPS;
import shortestpath.io.FileReader;
import shortestpath.util.Parser;
import shortestpath.util.StringUtil;

/**
 * Simple console UI.
 * 
 * @author Jake
 */
public class TextUI {

    private Scanner in;

    /**
     * Creates a new UI.
     * 
     * @param in Scanner to read user input from command line.
     */
    public TextUI(Scanner in) {
        this.in = in;
    }

    /**
     * Launches the UI. Will loop until terminated.
     */
    public void start() {
        System.out.println("Shortest path");
        while (true) {
            printInstructions();
            String command = in.nextLine();
            int commandNumber = 0;
            try {
                commandNumber = StringUtil.toInt(command);
            } catch (NumberFormatException e) {
                System.out.println("Unknown command");
                continue;
            }
            if (commandNumber == 1) {
                mapBenchmark();
            } else if (commandNumber == 2) {
                dataStructureBenchmark();
            } else if (commandNumber == 3) {
                break;
            } else {
                System.out.println("Unknown command");
            }

        }
    }

    private void printInstructions() {
        System.out.println("");
        System.out.println("1 - Benchmark maps");
        System.out.println("2 - Benchmark data structures");
        System.out.println("3 - Exit");
        System.out.println("");
    }

    private void mapBenchmark() {
        System.out.print("Map name: ");
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
            return;
        }
        
        Graph graph = Parser.buildGraph(mapData);
        Grid grid = Parser.buildGrid(mapData);
        
        Scenario dijkstra = Parser.buildScenario(benchmarkData, mapData, new Dijkstra(graph));
        Scenario aStar = Parser.buildScenario(benchmarkData, mapData, new AStar(graph));
        Scenario jps = Parser.buildScenario(benchmarkData, mapData, new JPS(grid));

        int runs = runs();
        boolean discardFirstRun = false;

        if (runs > 2) {
            discardFirstRun = true;
        }

        System.out.println("Running benchmark...");

        long startTime = System.nanoTime();
        Result dijkstaResult = dijkstra.runScenario(runs, discardFirstRun);
        Result aStarResult = aStar.runScenario(runs, discardFirstRun);
        Result jpsResult = jps.runScenario(runs, discardFirstRun);
        double testRunTime = (System.nanoTime() - startTime) / 1e9;

        System.out.println("-----Benchmark-----\n");
        System.out.println("Map: " + filename);
        System.out.println("Time: " + String.format("%.4f", testRunTime) + " s");
        System.out.println("Runs: " + runs);
        System.out.println("Tests per run: " + dijkstaResult.getTestCount() + "\n");
        printResults(dijkstaResult, "Dijkstra's algorithm");
        printResults(aStarResult, "A*");
        printResults(jpsResult, "JPS");
        System.out.println("-------------------");
    }

    private int runs() {
        while (true) {
            System.out.print("Runs: ");
            String runCount = in.nextLine();
            try {
                return Integer.parseInt(runCount);
            } catch (NumberFormatException e) {
                System.out.println("Invalid");
            }
        }
    }

    private void printResults(Result result, String algoName) {
        System.out.println(algoName);
        System.out.println("All paths found:    " + result.allPathsFound());
        System.out.println("Average setup time: " + formatTime(result.getAvgSetupTime()));
        System.out.println("Average run time:   " + formatTime(result.getAvgRunTime()));
        System.out.println("Fastest run time:   " + formatTime(result.getMinRunTime()));
        System.out.println("Slowest run time:   " + formatTime(result.getMaxRunTime()));
        System.out.println("");
    }

    private String formatTime(double time) {
        return String.format("%.4f", time / 1e6) + " ms";
    }

    private void dataStructureBenchmark() {
        DataStructureComparer comparer = new DataStructureComparer();
        System.out.println("This might take a while...\n");

        System.out.println("Array list benchmark:");
        int n = 1000;
        for (int i = 0; i < 5; i++) {
            double[] result = comparer.arrayListTest(10, n);
            System.out.println("size: " + n + ", java: " + formatTime(result[0])
                    + ", own: " + formatTime(result[1])
            );
            n *= 10;
        }

        System.out.println("");
        System.out.println("Heap benchmark:");

        n = 1000;
        for (int i = 0; i < 5; i++) {
            double[] result = comparer.heapTest(10, n);
            System.out.println("size: " + n + ", java: " + formatTime(result[0])
                    + ", own: " + formatTime(result[1])
            );
            n *= 10;
        }
    }
}
