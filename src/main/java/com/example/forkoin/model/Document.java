package com.example.forkoin.model;

import com.example.forkoin.tasks.DocumentTask;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by luis.camilo on 19/09/2016.
 */
public class Document {
    private static Document document;
    private String[] words = {"the", "hello", "goodbye", "packt", "java", "thread", "pool", "random", "class", "main"};

    public String[][] generateDocument(Integer numLines, int numWords, String word) {
        Long counter = 0L;
        final Random random = new Random();
        String documents[][] = new String[numLines][numWords];
        counter =  Stream.iterate(0, row -> row++)
                .limit(numLines)
                .map(row -> Stream.iterate(0, col -> col++)
                        .limit(numWords)
                        .map(col -> {
                            int index = random.nextInt(words.length);
                            String workFound = words[index];
                            documents[row][col] = workFound ;
                            return workFound.equals(word);
                        }).count()
                ).count();
        System.out.println("DocumeentMock the word appears " + counter + "times int the document");
        return documents;

    }

    public static DocumentTask newInstanceDocumentTask(String[][] document, int start, int middle, String word) {
        return new DocumentTask(document, start, middle, word);
    }

    public static Document newInstance() {
        if(document == null){
            document = new Document();
        }
        return document;
    }
}
