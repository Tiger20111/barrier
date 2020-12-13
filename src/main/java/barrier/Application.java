package barrier;

import barrier.node.Node;
import barrier.testandset.Barrier;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        int n = 30;
        testAndSet(n);
    }

    private static void testAndSet(int n) {
        Barrier barrier = new Barrier(n);
        ArrayList<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < n; i++) {
            nodes.add(new Node(barrier));
        }
        for (Node node:
                nodes) {
            new Thread(node).start();
        }
    }
}
