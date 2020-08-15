# User Guide

**NOTE:** The project comes with Gradle Wrapper. To utilize the wrapper use `gradlew <task>` on Windows or `./gradlew <task>` on Linux and macOS instead of `gradle <task>`.

## Running benchmarks

You can start the program with
```
gradle run
```

Once the program is running, write the name of the benchmark file you want to run **without extensions** e.g. if you want to run `arena.map.scen` file just write `arena`.

Next input the amount of times you want to repeat the benchmark. Higher amount of runs will give more accurate results but takes more time. The result of the benchmark will be the average time of all runs.

## Benchmark files

The program uses maps from [Moving AI Lab](https://www.movingai.com). The maps are stored in the maps folder of the project. The project comes with three maps: arena, den011d and brc203d from [Dragon Age: Origins](https://www.movingai.com/benchmarks/dao/index.html). If you want to download more maps you can find them [here](https://www.movingai.com/benchmarks/grids.html). To run the benchmark you need both the .map file and the matching .scen file. The .map file contains the map itself and the .scen file contains required data for the benchmark. More about the file formats [here](https://www.movingai.com/benchmarks/formats.html).

## Testing

You can run the unit tests with
```
gradle test
```
