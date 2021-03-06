package com.example.forkoin.tasks;

import com.example.forkoin.model.Document;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by luis.camilo on 20/09/2016.
 */
public class DocumentTask extends RecursiveTask<Integer> {
    private String document[][];
    private int start, end;
    private String word;

    public DocumentTask(String[][] document, int start, int end, String word) {
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (end - start < 10) {
            result = processLine(document, start, end, word);
        } else {
            int middle = (start + end) / 2;
            DocumentTask taskOne = Document.newInstanceDocumentTask(document, start, middle, word);
            DocumentTask taskTwo = Document.newInstanceDocumentTask(document, middle, end, word);
            invokeAll(taskOne, taskTwo);
            try {
                result = groupResult(taskOne.get(), taskTwo.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private Integer groupResult(Integer taskOne, Integer taskTwo) {

        return taskOne + taskTwo;

    }

    private Integer processLine(String[][] document, int start, int end, String word) {
        List<LineTask> tasks = IntStream.range(start, end)
                .mapToObj(index -> LineTask.newInstance(document[index], 0, document[index].length, word))
                .collect(Collectors.toList());
        Integer result = tasks.stream().mapToInt(task -> {
            Integer resultFromTask = 0;
            try {
                resultFromTask = task.get();
            } catch (Exception e) {
                resultFromTask = 0;
            }
            return resultFromTask;

        }).sum();
        return result;
    }

    public static DocumentTask newInstance(String[][] document, int start, int end, String word) {
        return new DocumentTask(document, start, end, word);
    }
}
