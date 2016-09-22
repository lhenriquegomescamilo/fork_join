package com.example.forkoin.tasks;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * Created by luis.camilo on 20/09/2016.
 */
public class LineTask extends RecursiveTask<Integer> implements Serializable {

    private String[] lines;
    private int start;
    private int end;
    private String word;

    private LineTask(String[] lines, int start, int end, String word) {

        this.lines = lines;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    public static LineTask newInstance(String[] strings, int i, int length, String word) {

        return new LineTask(strings, i, length, word);
    }

    @Override
    protected Integer compute() {
        Integer result = null;
        if ((end - start) > 100) {
            result = count(lines, start, end, word);
        } else {
            int middle = (start + end) / 2;
            LineTask taskOne = LineTask.newInstance(lines, start, middle, word);
            LineTask taskTwo = LineTask.newInstance(lines, middle, end, word);
            invokeAll(taskOne, taskTwo);
            try {
                result = taskOne.get() + taskTwo.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private Integer count(String[] lines, int start, int end, String word) {
        Long result = IntStream.range(start, end)
                    .filter(i -> lines[i].equals(word))
                .count();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result.intValue();
    }
}
