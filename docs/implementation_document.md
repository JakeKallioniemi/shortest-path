# Implementation Document

## Project structure

Since the control flow of the program is fairly simple it's all done directly from the TextUI class. TextUI uses FileReader and Parser to build the required graphs and benchmark data from the map files. It then uses them to build a Scenario, which is then ran. The scenario uses a provided path finding algorithm to go trough all the tests that were defined in the parsed file. Afterwards in returns the results which are then printed to console by the UI class. The project also contains several utility classes and data structures that are used in the benchmarking process.

## About the algorithms

I originally used Euclidian distance as heuristic for A* and JPS. After reading more abot the different heuristics I switched to octile distance. JPS uses octile distance to calculate the distance from previous previous node as well. The performance boost for A* especially was very significant.

The benchmark file assumes that the path is not cutting trough any wall cornerns, which means that the pruning in JPS works a bit differently from most online examples.

Dijkstra's algorithm and A* require some preprocessing since both of them use adjacency lists as graph. JPS just uses the raw grid. The preprocessing time isn't very significant since after every time the graph is build multiple searches are done, which is probably true for most real world scenarios as well.

## Time and space complexity

Dijkstra's algorithm has a time complexity of O((|E|+|V|)log|V|) where |E| is the amount of edges and |V| is the amount of vertices (nodes). The space complexity of the algorithm is O(|V|) (we can't have complete graphs).

A* and JPS both have the same time and space complexity as Dijkstra's algorithm. Of course in practice they are significantly faster.

The binary heap has time complexity of O(1) for peeking, O(logn n) for polling and O(log n) for inserting.

The array list has time complexity of O(1) amortized for inserting and O(1) for indexing.

## Performance

More information about the maps, benchmarks and how to run them in the [user guide](https://github.com/jakekall/shortest-path/blob/master/docs/user_guide.md).

I tested four different maps arena, orz304d, den011d and brc203d. These are all included in the map folder. The reported times are averages over many runs. The time of the first run is discarded. Since the time it takes to run the benchmark varies wildly between the maps, I couldn't run them all the same amount of times. So the times for the larger maps might vary a bit more. The tests were ran on Intel i5-4690K 3.50 GHz with 8 GB of RAM. All times are in milliseconds.

| map     | repeats | tests | Dijkstra | A*      | JPS     |
|---------|---------|-------|----------|---------|---------|
| arena   | 1000    | 130   | 25.32    | 3.015   | 3.21    |
| orz304d | 500     | 380   | 227.3    | 77.21   | 40.85   |
| den011d | 50      | 750   | 1881.87  | 910.63  | 396.83  |
| brc203d | 20      | 1290  | 5120.79  | 3049.82 | 1799.53 |

## Flaws and improvements

- In large maps JPS sometimes (handfull out of 1000+ tests) calculates the length of the path incorrectly. The paths are off by only few units or less, so I think the error happens just because of floating point arithmetic.
- JPS stores state in the nodes so all of them have to be reset before it can search for the next path.
- runScenario() method in Scenario class got a bit messy after adding JPS. 
- StringUtil still uses Java's parseInt and parseDouble. These are only used for parsing the files.
- The program could have included more comparisons. For example some benchmarks on different heuristics.
- There could have been more benchmark info. For example dumbed into a file.
- The program is a bit boring since it doesn't actually show any of the found paths. A GUI and some path finding visualization would have been nice.

## Sources

- [Moving AI Lab](https://movingai.com/), Nathan R. Sturtevant - for the maps used in benchmarks
- [Introduction to the A* Algorithm](https://www.redblobgames.com/pathfinding/a-star/introduction.html), Red Blob Games (Amit Patel)
- [Heuristics](http://theory.stanford.edu/~amitp/GameProgramming/Heuristics.html), Red Blob Games (Amit Patel)
- [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm), Wikipedia
- [A* search algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm), Wikipedia
- [A Visual Explanation of Jump Point Search ](https://zerowidth.com/2013/a-visual-explanation-of-jump-point-search.html), Nathan Witmer
- [Online Graph Pruning for Pathfinding on Grid Maps](http://grastien.net/ban/articles/hg-aaai11.pdf), Daniel Harabor and Alban Grastien
