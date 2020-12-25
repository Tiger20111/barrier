package barrier.node;

import barrier.Barrier;
import barrier.experement.Work;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.util.logging.LogManager;

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

    private void createLogger() throws Exception {
        try(FileInputStream ins = new FileInputStream("C:\\Users\\Tiger20111\\IdeaProjects\\barrier\\logs\\logs.log ")){
            LogManager.getLogManager().readConfiguration(ins);
            logger = Logger.getLogger(getClass());
        }
    }


    @Override
    public void run() {
        work();
    }
}
