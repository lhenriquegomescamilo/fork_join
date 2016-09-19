package com.example.forkoin.main;

import com.example.forkoin.generator.ProductListGenerator;
import com.example.forkoin.model.Product;
import com.example.forkoin.tasks.Task;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Created by luis.camilo on 19/09/2016.
 */
public class Main {
    public static void main(String... args) {
        ProductListGenerator generator = ProductListGenerator.newInstance();
        List<Product> products = generator.generate(10_000);
        Task task = Task.newInstance(products, 0, products.size(), 0.20);
        ForkJoinPool pool = newInstanceForJoinPool();
        pool.execute(task);
        printResultInConsole(task, pool);
        pool.shutdown();
        if (task.isCompletedNormally()) {
            System.out.printf("Main: The process has completed normally.\n");
        }
        products.stream()
                .filter(product -> product.getPrice() != 12)
                .forEach(System.out::println);
        System.out.println("Main: End of the program.\n");
    }

    private static ForkJoinPool newInstanceForJoinPool() {
        Supplier<ForkJoinPool> forkJoinPoolSupplier = ForkJoinPool::new;
        return forkJoinPoolSupplier.get();
    }

    private static void printResultInConsole(Task task, ForkJoinPool pool) {
        do {
            System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());
    }
}
