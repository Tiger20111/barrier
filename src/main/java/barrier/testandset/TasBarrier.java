package barrier.testandset;

import barrier.Barrier;
import barrier.lock.TasLock;

import java.util.concurrent.locks.Lock;

public class TasBarrier implements Barrier {
    private final Object monitor;
    private final int capacity;
    private int currentLocks = 0;
    private Lock tasLock;
    public TasBarrier(int capacity) {
        this.capacity = capacity;
        monitor = new Object();
        tasLock = new TasLock();
    }

    @Override
    public void myWait(int index) {
        tasLock.lock();
        currentLocks++;
        tasLock.unlock();
        await();
    }

    private void await() {
        synchronized (monitor) {
            if (currentLocks != capacity) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                monitor.notifyAll();
            }
        }
    }

}
