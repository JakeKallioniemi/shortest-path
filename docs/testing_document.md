# Testing Document

Report on test coverage can be found [here](https://codecov.io/gh/jakekall/shortest-path). I've also included a picture of JaCoCo report just in case. The line coverage is 87% and branch coverage is 78%. 

![test coverage](https://github.com/jakekall/shortest-path/blob/master/docs/pics/coverage.PNG)

## Unit testing

Unit testing is done with [JUnit5](https://junit.org/junit5/). The utility classes and custom data structures have a decent amount of test. Reading files and UI are not unit tested and they are ignored from the test coverage report. There are several classes that contain no or very little logic and are mainly used to hold data which are not tested directly. Because of the nature of the benchmark classes testing that they work correctly with unit tests is difficult.

## Algorithm correctness

Correctness of the pathfinding algorithms is mainly confirmed by running benchmarks. As a part of each benchmark the program verifies that each path was found correctly by comparing it's length to the expected path length from the scenario files. Each scenario file contains hundreds or even thousands of different paths so the testing ends up being quite thorough.

## Performance testing

Testing the performance of the search algorithms is covered in the [implementation document](https://github.com/jakekall/shortest-path/blob/master/docs/implementation_document.md#performance).

The custom data structures were tested against Java standard library implementations. The array lists were tested by adding and getting random values since those are the only operations the custom list supports. Heap was tested with adding random values and polling. All tests were reapeated 10 times and the reported times are averages in milliseconds. Both lists performed similarly while the Java implementation of heap was faster than the custom one. The tests can be run by selecting option 2 from the programs menu. 

Array list test:

| elements | own       | java      |  
|----------|-----------|-----------|
| 1000     | 1.0242    | 1.9581    |
| 10000    | 1.8695    | 2.0451    |
| 100000   | 8.7705    | 8.9413    |
| 1000000  | 87.8980   | 90.4555   |
| 10000000 | 1567.5131 | 1533.2477 |

Heap test:

| elements | own       | java      |
|----------|-----------|-----------|
| 1000     | 0.5784    | 0.5352    |
| 10000    | 2.0958    | 1.6359    |
| 100000   | 22.8921   | 19.8519   |
| 1000000  | 387.2181  | 298.2361  |
| 10000000 | 6483.9770 | 5863.4980 |

Note that the data structures don't use System.arraycopy(). There was a small effect on data structure performance but it didn't have any noticable effect on the actual algorithms
