package shortestpath.benchmark;

import java.util.ArrayList;
import java.util.PriorityQueue;
import shortestpath.datastructures.BinaryHeap;
import shortestpath.datastructures.List;
import shortestpath.util.MathUtil;

/**
 * Compares the performance of custom data structure implementations to Java
 * standard library.
 *
 * @author Jake
 */
public class DataStructureComparer {

    /**
     * Runs benchmark comparing Java ArrayList to custom list. The results are
     * returned as an array where the first element is average time for Java
     * standard library data structure and seconds element is for custom data
     * structure. The times are in nanoseconds.
     *
     * @param runs amount of repeats
     * @param n amount of elements
     * @return array with results
     */
    public double[] arrayListTest(int runs, long n) {
        List<Long> javaTimes = new List<Long>();
        List<Long> ownTimes = new List<Long>();

        for (int i = 0; i < runs; i++) {
            long startTime = System.nanoTime();
            ArrayList<Long> javaList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                javaList.add(System.nanoTime() % n);
            }
            for (int j = 0; j < n; j++) {
                javaList.get((int) (System.nanoTime() % n));
            }
            long endTime = System.nanoTime();
            javaTimes.add(endTime - startTime);

            startTime = System.nanoTime();
            List<Long> ownList = new List<>();
            for (int j = 0; j < n; j++) {
                ownList.add(System.nanoTime() % n);
            }
            for (int j = 0; j < n; j++) {
                ownList.get((int) (System.nanoTime() % n));
            }
            endTime = System.nanoTime();
            ownTimes.add(endTime - startTime);
        }

        double javaAvgTime = MathUtil.average(javaTimes);
        double ownAvgTime = MathUtil.average(ownTimes);

        return new double[]{javaAvgTime, ownAvgTime};
    }

    /**
     * Runs benchmark comparing Java PriorityQueue to custom binary heap. The
     * results are returned as an array where the first element is average time
     * for Java standard library data structure and seconds element is for
     * custom data structure. The times are in nanoseconds.
     *
     * @param runs amount of repeats
     * @param n amount of elements
     * @return array with results
     */
    public double[] heapTest(int runs, long n) {
        List<Long> javaTimes = new List<Long>();
        List<Long> ownTimes = new List<Long>();

        for (int i = 0; i < runs; i++) {
            long startTime = System.nanoTime();
            PriorityQueue<Long> javaHeap = new PriorityQueue<>();
            for (int j = 0; j < n; j++) {
                javaHeap.add(System.nanoTime() % n);
            }
            for (int j = 0; j < n; j++) {
                javaHeap.poll();
            }
            long endTime = System.nanoTime();
            javaTimes.add(endTime - startTime);

            startTime = System.nanoTime();
            BinaryHeap<Long> ownHeap = new BinaryHeap<>();
            for (int j = 0; j < n; j++) {
                ownHeap.add(System.nanoTime() % n);
            }
            for (int j = 0; j < n; j++) {
                ownHeap.poll();
            }
            endTime = System.nanoTime();
            ownTimes.add(endTime - startTime);
        }

        double javaAvgTime = MathUtil.average(javaTimes);
        double ownAvgTime = MathUtil.average(ownTimes);

        return new double[]{javaAvgTime, ownAvgTime};
    }
}
