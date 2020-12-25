package barrier.testandset;

import barrier.MyExecuter;
import barrier.node.Node;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class BarrierTasTest{
    public static long run(int n, ExecutorService tasPool) throws Exception {
        long startTime = System.nanoTime();
        TasBarrier barrier = new TasBarrier(n);
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Node newNode = new Node(barrier);
            nodes.add(newNode);
        }
        MyExecuter.executeTasks(tasPool, nodes);
        long timeTas = System.nanoTime() - startTime;
        System.out.println("time to execute TestAndSet: " + timeTas);
        return timeTas;
    }
}
