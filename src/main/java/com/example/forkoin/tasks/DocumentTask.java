package com.example.forkoin.tasks;

import com.example.forkoin.model.Document;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

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
            DocumentTask taskOne = Document.newInstance(document, start, middle, word);
            DocumentTask taskTwo = Document.newInstance(document, middle, end, word);
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
        return 0;
    }

    private int processLine(String[][] document, int start, int end, String word) {
        List<LineTask> tasks = null;
        return 0;
    }
}
