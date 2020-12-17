package barrier;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        int n = 6;
        long startTime = System.nanoTime();
        testAndSet(n);
        long time = System.nanoTime() - startTime;
        System.out.println("time to execute TestAndSet: " + time);
        startTime = System.nanoTime();
        array(n);
        time = System.nanoTime() - startTime;
        System.out.println("time to execute Array: " + time);

    }

    private static void testAndSet(int n) throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        barrier.testandset.Barrier barrier = new barrier.testandset.Barrier(n);
        ArrayList<barrier.testandset.node.Node> nodes = new ArrayList<barrier.testandset.node.Node>();
        for (int i = 0; i < n; i++) {
            nodes.add(new barrier.testandset.node.Node(barrier));
        }
        for (barrier.testandset.node.Node node:
                nodes) {
            Thread thread = new Thread(node);
            threads.add(thread);
            thread.start();
        }
        for (int i = 0; i < n; i++) {
            threads.get(i).join();
        }
    }

    private static void array(int n) {
        ArrayList<Thread> threads = new ArrayList<>();
        barrier.array.Barrier barrier = new barrier.array.Barrier(n);
        ArrayList<barrier.array.node.Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new barrier.array.node.Node(barrier, i));
        }
        for (barrier.array.node.Node node:
                nodes) {
            Thread thread = new Thread(node);
            threads.add(thread);
            thread.start();
        }
    }
}
