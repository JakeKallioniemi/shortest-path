package shortestpath.benchmark;

import shortestpath.domain.Graph;
import shortestpath.domain.PathFinder;
import shortestpath.util.List;
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
    private final PathFinder pathFinder;
    private final List<Long> setupTimes;
    private final List<Long> runTimes;

    /**
     * Creates a pathfinding algorithm benchmarking scenario.
     *
     * @param mapName name of the map used for the scenario
     * @param mapData data from the .map file
     * @param pathFinder pathfinding algorithm used in the benchmark
     */
    public Scenario(String mapName, List<String> mapData, PathFinder pathFinder) {
        this.mapName = mapName;
        this.mapData = mapData;
        this.pathFinder = pathFinder;
        this.tests = new List<>();
        this.setupTimes = new List<>();
        this.runTimes = new List<>();
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
            for (int j = 0; j < tests.size(); j++) {   
                Test test = tests.get(j);
                long runTimeStart = System.nanoTime();
                double pathLength = pathFinder.search(graph, test.getStart(), test.getEnd());
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
    
    public int getTestCount() {
        return tests.size();
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
     * The average (arithmetic mean) setup time in seconds for this scenario.
     * 
     * @return average setup time
     */
    public double getAvgSetupTime() {
        return MathUtil.average(setupTimes) / 1e9;
    }

    /**
     * The average (arithmetic mean) run time in seconds for this scenario.
     * 
     * @return average run time
     */
    public double getAvgRunTime() {
        return MathUtil.average(runTimes) / 1e9;
    }

    /**
     * Checks that all shortest paths were found correctly.
     * 
     * @return true if all found otherwise false
     */
    public boolean allPathsFound() {
        for (int i = 0; i < tests.size(); i++) {
            Test test = tests.get(i);
            if (!MathUtil.equals(test.getLength(), test.getOptimalLength(), 0.0001)) {
                return false;
            }
        }
        return true;
    }
}
