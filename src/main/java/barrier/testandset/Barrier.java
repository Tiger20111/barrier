package barrier.testandset;

import barrier.node.Node;

public class Barrier {
    private volatile int capacity;
    private int currentLocks = 0;
    public Barrier(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void myWait() throws InterruptedException {
        currentLocks++;
        while (currentLocks != capacity) {
            this.wait();
        }
        this.notifyAll();
    }

}
