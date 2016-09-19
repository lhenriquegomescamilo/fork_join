package com.example.forkoin.tasks;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.stream.IntStream;

import com.example.forkoin.model.Product;

public class Task extends RecursiveAction implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<Product> products;
    private Integer first;
    private Integer last;

    private Double increment;

    private Task(List<Product> products, Integer first, Integer last, Double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if (last - first < 10) {
            updatePrices();
        } else {
            forkCompute();

        }

    }

    private void forkCompute() {
        Integer middle = (last + first) / 2;
        System.out.printf("Task: pending tasks  %s\n", getQueuedTaskCount());
        Task task = Task.newInstance(products, first, middle + 1, increment);
        Task taskTwo = Task.newInstance(products, middle + 1, last, increment);
        invokeAll(task, taskTwo);
    }

    public static Task newInstance(List<Product> products, Integer first, int i, Double increment) {
        return new Task(products, first, i, increment);
    }

    private void updatePrices() {
        IntStream.range(first, last).forEach(i -> {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));
        });
    }

}
