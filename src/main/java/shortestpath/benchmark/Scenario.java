package shortestpath.benchmark;

import shortestpath.datastructures.List;
import shortestpath.domain.JPS;
import shortestpath.domain.PathFinder;
import shortestpath.util.MathUtil;
import shortestpath.util.Parser;

/**
 * Defines a pathfinding algorithm benchmarking scenario.
 *
 * @author Jake
 */
public class Scenario {

    private final List<String> mapData;
    private final List<Test> tests;
    private final PathFinder pathFinder;
    private final List<Long> setupTimes;
    private final List<Long> runTimes;

    /**
     * Creates a pathfinding algorithm benchmarking scenario.
     *
     * @param mapData data from the .map file
     * @param pathFinder pathfinding algorithm used in the benchmark
     */
    public Scenario(List<String> mapData, PathFinder pathFinder) {
        this.mapData = mapData;
        this.pathFinder = pathFinder;
        this.tests = new List<>();
        this.setupTimes = new List<>();
        this.runTimes = new List<>();
    }

    /**
     * Adds a new test run to the scenario.
     *
     * @param test test to be added
     */
    public void addTest(Test test) {
        tests.add(test);
    }

    public List<Test> getTests() {
        return tests;
    }

    /**
     * Runs the scenario and benchmarks the time needed to setup the graph and
     * perform the pathfinding.
     *
     * @param runCount amount of runs, more runs gives more accurate results but
     * takes more time.
     * @param ignoreFirstRun if true the result for first run is not counted
     */
    public Result runScenario(int runCount, boolean ignoreFirstRun) {
        for (int i = 0; i < runCount; i++) {
            long setupTimeStart = System.nanoTime();
            if (pathFinder instanceof JPS) {
                Parser.buildGrid(mapData);
            } else {
                Parser.buildGraph(mapData);
            }
            if (!(i == 0 && ignoreFirstRun)) {
                setupTimes.add(System.nanoTime() - setupTimeStart);
            }

            long runTime = 0;
            for (int j = 0; j < tests.size(); j++) {
                Test test = tests.get(j);
                long runTimeStart = System.nanoTime();
                double pathLength = pathFinder.search(test.getStart(), test.getEnd());
                test.setLength(pathLength);
                runTime += System.nanoTime() - runTimeStart;
            }
            if (!(i == 0 && ignoreFirstRun)) {
                runTimes.add(runTime);
            }
        }
        Result result = new Result(
                tests.size(),
                allPathsFound(),
                MathUtil.average(setupTimes),
                MathUtil.average(runTimes),
                MathUtil.min(runTimes),
                MathUtil.max(runTimes)
        );
        return result;
    }

    private boolean allPathsFound() {
        for (int i = 0; i < tests.size(); i++) {
            Test test = tests.get(i);
            if (!MathUtil.equals(test.getLength(), test.getOptimalLength(), 0.1)) {
                return false;
            }
        }
        return true;
    }
}
