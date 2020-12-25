package barrier;

import barrier.array.BarrierArrayTest;
import barrier.testandset.BarrierTasTest;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args) throws Exception {
        int optimized = 30;
        int n = 6;
        Logger logger = Logger.getLogger(Application.class);
        ExecutorService tasPool = Executors.newFixedThreadPool(n);
        ExecutorService arrPool = Executors.newFixedThreadPool(n);

        int warmupIters = optimized / n;

        for (int i = 0; i < warmupIters; i++) {
            logger.info("Num iter: " + i + "\n\n");
            System.out.println("Num iter: " + i);
            experiment(logger, n, tasPool, arrPool);
        }
        tasPool.shutdown();
        arrPool.shutdown();
    } 

    private static void experiment(Logger logger, int n, ExecutorService tasPool, ExecutorService arrPool) throws Exception {
        logger.info("Test and Set barrier:\n");
        long timeTas = BarrierTasTest.run(n, tasPool);
        logger.info("Array barrier:\n");
        long timeArr = BarrierArrayTest.run(n, arrPool);
        Thread.sleep(1000);
        logger.info("Time diff(T/A): " + (double) timeTas / timeArr + "\n");
        System.out.println("Time diff(T/A): " + (double) timeTas / timeArr);
    }
}
