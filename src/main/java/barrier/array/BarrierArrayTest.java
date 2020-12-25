package barrier.array;

import barrier.MyExecuter;
import barrier.node.Node;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class BarrierArrayTest {
    public static long run(int n, ExecutorService tasPool) throws Exception {
        long startTime = System.nanoTime();
        ArrayBarrier barrier = new ArrayBarrier(n);
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Node newNode = new Node(barrier, i);
            nodes.add(newNode);
        }
        MyExecuter.executeTasks(tasPool, nodes);
        long timeArr = System.nanoTime() - startTime;
        System.out.println("time to execute Array: " + timeArr);
        return timeArr;
    }

}
