package barrier;

import barrier.node.Node;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MyExecuter {
    public static void executeTasks(ExecutorService tasPool, ArrayList<Node> nodes) throws InterruptedException, ExecutionException {
        ArrayList<Future<?>> futures = new ArrayList<>();
        for (Node node : nodes) {
            Future<?> f = tasPool.submit(node);
            futures.add(f);
        }
        for (Future<?> future : futures) {
            future.get();
        }
    }
}
