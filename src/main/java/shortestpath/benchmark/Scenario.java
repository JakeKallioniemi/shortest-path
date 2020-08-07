package shortestpath.benchmark;

import java.util.ArrayList;
import java.util.List;
import shortestpath.domain.Dijkstra;
import shortestpath.domain.Graph;
import shortestpath.util.MathUtil;
import shortestpath.util.Parser;

/**
 * Defines a pathfinding algorithm benchmarking scenario.
 *
 * @author Jake
 */
public class Scenario {

    private final String mapName;
    private final List<String> mapData;
    private final List<Test> tests;
    private List<Long> setupTimes;
    private List<Long> runTimes;

    /**
     * Creates a pathfinding algorithm benchmarking scenario.
     *
     * @param mapName name of the map used for the scenario
     * @param mapData data from the .map file
     */
    public Scenario(String mapName, List<String> mapData) {
        this.mapName = mapName;
        this.mapData = mapData;
        this.tests = new ArrayList<>();
        this.setupTimes = new ArrayList<>();
        this.runTimes = new ArrayList<>();
    }

    /**
     * Adds a new test run to the scenario.
     *
     * @param test
     */
    public void addTest(Test test) {
        tests.add(test);
    }

    /**
     * Runs the scenario and benchmarks the time needed to setup the graph and
     * perform the pathfinding.
     *
     * @param runCount amount of runs, more runs gives more accurate results but
     * takes more time.
     */
    public void runScenario(int runCount) {
        for (int i = 0; i < runCount; i++) {
            long setupTimeStart = System.nanoTime();
            Graph graph = Parser.buildGraph(mapData);
            long setupTimeEnd = System.nanoTime();
            setupTimes.add(setupTimeEnd - setupTimeStart);

            long runTime = 0;
            for (Test test : tests) {
                long runTimeStart = System.nanoTime();
                Dijkstra dijkstra = new Dijkstra();
                double pathLength = dijkstra.search(graph, test.getStart(), test.getEnd());
                test.setLength(pathLength);
                long runTimeEnd = System.nanoTime();
                runTime += runTimeEnd - runTimeStart;
            }
            runTimes.add(runTime);
        }
    }

    public String getMapName() {
        return mapName;
    }

    public List<Test> getTests() {
        return tests;
    }

    /**
     * The setup times for each run.
     *
     * @return setup times
     */
    public List<Long> getSetupTimes() {
        return setupTimes;
    }

    /**
     * The run times for each run.
     * 
     * @return run times.
     */
    public List<Long> getRunTimes() {
        return runTimes;
    }
    
    /**
     * The average (arithmetic mean) setup time for this scenario.
     * 
     * @return average setup time
     */
    public double getAvgSetupTime() {
        return MathUtil.average(setupTimes) / 1e9;
    }

    /**
     * The average (arithmetic mean) run time for this scenario.
     * 
     * @return average run time
     */
    public double getAvgRunTime() {
        return MathUtil.average(runTimes) / 1e9;
    }

    public boolean allPathsFound() {
        for (Test test : tests) {
            if (!MathUtil.equals(test.getLength(), test.getOptimalLength(), 6)) {
                return false;
            }
        }
        return true;
    }
}
