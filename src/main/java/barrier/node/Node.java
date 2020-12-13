package barrier.node;

import barrier.experement.Work;
import barrier.testandset.Barrier;
import org.apache.log4j.Logger;

public class Node implements Runnable{
    private Logger logger;
    private Barrier barrier;

    public Node(Barrier barrier) {
        createLogger();
        this.barrier = barrier;
    }

    private void work() throws InterruptedException {
        logger.info("Thread.id = " + Thread.currentThread().getId() + ": Foo start");
        Work.foo();
        logger.info("Thread.id = " + Thread.currentThread().getId() + ": Foo end");
        barrier.myWait();
        logger.info("Thread.id = " + Thread.currentThread().getId() + ": Bar start");
        Work.bar();
        logger.info("Thread.id = " + Thread.currentThread().getId() + ": Bar end");
    }

    private void createLogger() {
        logger = Logger.getLogger(getClass());
    }

    public void run() {
        try {
            work();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
