package com.example.forkoin.main;

import com.example.forkoin.mock.DocumentMock;
import com.example.forkoin.tasks.DocumentTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by luis.camilo on 22/09/2016.
 */
public class DocumentMain {

    public static void main(String... args) {
        DocumentMock documenMock = DocumentMock.newDocument();
        String[][] document = documenMock.generateDocument(100, 10000, "the");
        DocumentTask task = DocumentTask.newInstance(document, 0, 100, "the");
        ForkJoinPool pool = DocumentMain.newInstanceForkJoinPool();
        pool.execute(task);
        showMessage(task, pool);
        pool.shutdown();
        waitForTermination(pool);
    }

    private static ForkJoinPool newInstanceForkJoinPool() {
        return new ForkJoinPool();
    }

    private static void showMessage(DocumentTask task, ForkJoinPool pool) {
        do {
            System.out.printf("******************************************\n");
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
            System.out.printf("******************************************\n");
            try {
                System.out.printf("Main: The word appears %d in the document",task.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());
    }

    private static void waitForTermination(ForkJoinPool pool) {
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
