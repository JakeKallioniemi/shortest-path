package shortestpath.ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import shortestpath.benchmark.DataStructureComparer;
import shortestpath.benchmark.Scenario;
import shortestpath.domain.AStar;
import shortestpath.domain.Dijkstra;
import shortestpath.io.FileReader;
import shortestpath.util.List;
import shortestpath.util.Parser;
import shortestpath.util.StringUtil;

public class TextUI {

    private Scanner in;

    public TextUI(Scanner in) {
        this.in = in;
    }

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
        Scenario dijkstra = Parser.buildScenario(benchmarkData, mapData, new Dijkstra());
        Scenario aStar = Parser.buildScenario(benchmarkData, mapData, new AStar());

        int runs = runs();

        System.out.println("Running benchmark...");
        dijkstra.runScenario(runs);
        aStar.runScenario(runs);

        int testCount = dijkstra.getTestCount();

        System.out.println("-----Benchmark-----\n");
        System.out.println("Map: " + filename);
        System.out.println("Runs: " + runs);
        System.out.println("Tests per run: " + testCount + "\n");
        printResults(dijkstra, "Dijkstra's algorithm");
        printResults(aStar, "A*");
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

    private void printResults(Scenario scenario, String algoName) {
        System.out.println(algoName);
        System.out.println("All paths found: " + scenario.allPathsFound());
        System.out.println("Average setup time: " + scenario.getAvgSetupTime());
        System.out.println("Average run time: " + scenario.getAvgRunTime());
        System.out.println("");
    }

    private void dataStructureBenchmark() {
        DataStructureComparer comparer = new DataStructureComparer();
        System.out.println("This might take a while...\n");

        System.out.println("Array list benchmark:");
        int n = 1000;
        for (int i = 0; i < 5; i++) {
            double[] result = comparer.arrayListTest(10, n);
            System.out.println("size: " + n + ", java: " + result[0] + ", own: " + result[1]);
            n *= 10;
        }

        System.out.println("");
        System.out.println("Heap benchmark:");
        
        n = 1000;
        for (int i = 0; i < 5; i++) {
            double[] result = comparer.heapTest(10, n);
            System.out.println("size: " + n + ", java: " + result[0] + ", own: " + result[1]);
            n *= 10;
        }
    }
}
