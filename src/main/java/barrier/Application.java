package barrier;

import barrier.array.BarrierArrayTest;
import barrier.testandset.BarrierTasTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args) throws Exception {
        int optimized = 5_0;
        int n = 2;

        ExecutorService tasPool = Executors.newFixedThreadPool(n);
        ExecutorService arrPool = Executors.newFixedThreadPool(n);

        int warmupIters = optimized / n;

        for (int i = 0; i < warmupIters; i++) {
            System.out.println("Num iter: " + i);
            experiment(n, tasPool, arrPool);
        }
        tasPool.shutdown();
        arrPool.shutdown();
    } 

    private static void experiment(int n, ExecutorService tasPool, ExecutorService arrPool) throws Exception {
        long timeTas = BarrierTasTest.run(n, tasPool);
        long timeArr = BarrierArrayTest.run(n, arrPool);
//        Thread.sleep(1000);
        System.out.println("time diff: " + (double) timeTas / timeArr);
    }
}
