package shortestpath.benchmark;

/**
 * Holds the results of a benchmark. Times are in nanoseconds.
 *
 * @author Jake
 */
public class Result {

    private final int testCount;
    private final boolean allPathsFound;
    private final double avgSetupTime;
    private final double avgRunTime;
    private final long minRunTime;
    private final long maxRunTime;

    /**
     * Creates a new benchmark result.
     * 
     * @param testCount amount of tests per run
     * @param allPathsFound whether all paths were found or not
     * @param avgSetupTime average time take for preprocessing graph
     * @param avgRunTime average time to run tests
     * @param minRunTime fastest run time
     * @param maxRunTime slowest run time
     */
    public Result(int testCount, boolean allPathsFound, double avgSetupTime,
            double avgRunTime, long minRunTime, long maxRunTime) {
        this.testCount = testCount;
        this.allPathsFound = allPathsFound;
        this.avgSetupTime = avgSetupTime;
        this.avgRunTime = avgRunTime;
        this.minRunTime = minRunTime;
        this.maxRunTime = maxRunTime;
    }

    public int getTestCount() {
        return testCount;
    }

    public boolean allPathsFound() {
        return allPathsFound;
    }

    public double getAvgSetupTime() {
        return avgSetupTime;
    }

    public double getAvgRunTime() {
        return avgRunTime;
    }

    public double getMinRunTime() {
        return minRunTime;
    }

    public double getMaxRunTime() {
        return maxRunTime;
    }

}
