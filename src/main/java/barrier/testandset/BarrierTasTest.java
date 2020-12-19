package barrier.testandset;

import barrier.node.Node;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class BarrierTasTest{
    public static long run(int n, ExecutorService tasPool) throws Exception {
        long startTime = System.nanoTime();
        TasBarrier barrier = new TasBarrier(n);
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Node newNode = new Node(barrier);
            nodes.add(newNode);
        }
        for (Node node : nodes) {
            Future<?> f = tasPool.submit(node);
            f.get();
        }
        long timeTas = System.nanoTime() - startTime;
        System.out.println("time to execute TestAndSet: " + timeTas);

        return timeTas;
    }
}
