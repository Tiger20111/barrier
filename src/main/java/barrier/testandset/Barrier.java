package barrier.testandset;

import java.util.concurrent.atomic.AtomicBoolean;

public class Barrier {
    private final Object monitor;
    private int capacity;
    private int currentLocks = 0;
    private AtomicBoolean state = new AtomicBoolean(false);
    public Barrier(int capacity) {
        this.capacity = capacity;
        monitor = new Object();
    }

    public void myWait() throws InterruptedException {
        testAndTestAndSet();
        currentLocks++;
        state.set(false);
        if (currentLocks != capacity)  {
            synchronized (monitor) {
                monitor.wait();
            }
        } else {
            synchronized (monitor) {
                monitor.notifyAll();
            }
        }
    }

    private void testAndTestAndSet() {
        while (true) {
            while (!state.get()) {
                if (!state.getAndSet(true))
                    return;
            }
        }
    }

}
