package barrier.array.node;

import barrier.experement.Work;
import barrier.array.Barrier;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.util.logging.LogManager;

public class Node implements Runnable{
    private Logger logger;
    private Barrier barrier;
    private int index;

    public Node(Barrier barrier, int index) {
        this.index = index;
        createLogger();
        this.barrier = barrier;
    }

    private void work() throws InterruptedException {
        logger.info("Thread.id = " + Thread.currentThread().getId() + ": Foo start");
        Work.foo();
        logger.info("Thread.id = " + Thread.currentThread().getId() + ": Foo end");
        barrier.myWait(index);
        logger.info("Thread.id = " + Thread.currentThread().getId() + ": Bar start");
        Work.bar();
        logger.info("Thread.id = " + Thread.currentThread().getId() + ": Bar end");
    }

    private void createLogger() {
        try(FileInputStream ins = new FileInputStream("/Users/tiger/IdeaProjects/barrier/logs/barrierArray.log")){
            LogManager.getLogManager().readConfiguration(ins);
            logger = Logger.getLogger(getClass());
        }catch (Exception ignore){
            ignore.printStackTrace();
        }
    }


    public void run() {
        try {
            work();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
