# Design document

The program will compare the speed of different algorithms that solve the shortest path problem. The goal is to implement atleast Dijkstra's algorithm, A* and JPS. Additional algorithms, data structure comparisons, different optimizations and visualizations are a possibility depending on how smoothly the project goes. Material for pathfinding is still undecided but Moving AI labs 2D grids seems like the most likely candidate.

## Algorithms and data structures used

There will be atleast 3 algorithms for pathfinding:
- Dijkstra's algorithm
- A*
- JPS (Jump point search)

Dijkstra's algorithm and A* are probably the most well known pathfinding algorithms, while JPS acts as a more specialized algorithm.

In terms of data structures a priority queue is needed, which is going to be implemented with a binary heap or possibly a Fibonacci heap. Hash table and a set might be needed as well. I didn't have time to research the implementation of JPS so I don't know if needs anything else.

## Input / Output

- Parsing maps for pathfinding
- Console UI for starting performance tests and reporting results
- Possible GUI for easier input and algorithm visualization

## Time and space complexity

The time complexity of Dijkstra's algorithm is O((E+V)log V) with binary heap and O(E+V log V) with Fibonacci heap. The space complexity is O(V). A* has the same worst case complexity as Dijkstra's algorithm. The complexity of JPS is still unknown to me.
