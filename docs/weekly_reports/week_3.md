# Week 3

Time spent: 10h

## What I did

- Added ability to parse .map.scen files for benchmark info
- Added ability to run benchmarks build from parsed data
- Added diagonal movement in grids
- Implemented A* with Euclidean distance as heuristic (matches the distances in benchmark data)
- Added some utility classes

## What am I going to do next

- Start implementing data structures
- Add text UI
- Catch up on testing
- Clean up code
- Start implementing JPS
- Continue working on util classes
- Start writing documentation

## Thoughts and questions

I want to make sure that the algorithms actually find the shortest path. I'm using BigDecimal to truncate the expected path length and the found path length so that I can make sure they are equal up to certain amount of decimal places (currently 6). Should I do this without BigDecimal? If so how should I approach the problem?
