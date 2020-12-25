package barrier.node;

import barrier.Barrier;
import barrier.experement.Work;
import org.apache.log4j.Logger;

public class Node implements Runnable{
    private Logger logger;
    private Barrier barrier;
    private int index;

    public Node(Barrier barrier) throws Exception {
        createLogger();
        this.barrier = barrier;
    }

    public Node(Barrier barrier, int index) throws Exception {
        createLogger();
        this.barrier = barrier;
        this.index = index;
    }

    private void work() {
        logger.info("Thread.id = " + Thread.currentThread().getId() + ": Foo start");
        Work.foo();
        logger.info("Thread.id = " + Thread.currentThread().getId() + ": Foo end");
        barrier.myWait(index);
        logger.info("Thread.id = " + Thread.currentThread().getId() + ": Bar start");
        Work.bar();
        logger.info("Thread.id = " + Thread.currentThread().getId() + ": Bar end");
    }

    private void createLogger() {
            logger = Logger.getLogger(getClass());
    }


    @Override
    public void run() {
        work();
    }
}
