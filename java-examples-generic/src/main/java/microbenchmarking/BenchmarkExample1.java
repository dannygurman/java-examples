package microbenchmarking;

import org.openjdk.jmh.annotations.*;

public class BenchmarkExample1 {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)//Throughput is default
    //the value parameter controls how many times the benchmark will be executed,
    // and the warmup parameter
    // controls how many times a benchmark will dry run before results are collected, for example:
    @Fork(value = 3, warmups = 2)
    //@Warmup annotation can be used to control the number of warmup iterations.
    @Warmup(iterations = 4)
    public void initThroughput() {
        // Do nothing
        /*   running BenchmarkRunner will execute our arguably somewhat useless benchmark.
        Once the run is complete, a summary table is presented:
    # Run complete. Total time: 00:06:45
# Benchmark mode: Throughput, ops/time
# Benchmark: microbenchmarking.BenchmarkExample1.initThroughput
  */
    }




    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 2, warmups = 1)
    @Warmup(iterations = 3)

    public void initAverageTime() {
        // Do nothing
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }




}
