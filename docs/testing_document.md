# Testing Document

Report on test coverage can be found [here](https://codecov.io/gh/JakeKallioniemi/shortest-path)

## Unit testing

Unit testing is done with [JUnit5](https://junit.org/junit5/). The goal is to have very comprehensive unit testing of all utility classes including custom data structures. Reading files and UI are not unit tested and they are ignored from the test coverage report. There are several classes that contain no or very little logic and are mainly used to hold data. These are currently dragging down the test coverage percentage.

## Algorithm correctness

Correctness of the pathfinding algorithms is mainly confirmed by running benchmarks. As a part of each benchmark the program verifies that each path was found correctly by comparing it's length to the expected path length from the scenario files. Each scenario file contains hundreds or even thousands of different paths so the testing ends up being quite thorough.

## Performance testing

TODO
