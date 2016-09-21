package com.example.forkoin.tasks;

import java.util.concurrent.RecursiveTask;

/**
 * Created by luis.camilo on 20/09/2016.
 */
public class LineTask extends RecursiveTask<Integer> {
    private LineTask(String[] strings, int i, int length, String word) {

    }

    public static LineTask newInstance(String[] strings, int i, int length, String word) {

        return new LineTask(strings,i,length,word);
    }

    @Override
    protected Integer compute() {
        return null;
    }
}
