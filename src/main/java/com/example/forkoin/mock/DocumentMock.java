package com.example.forkoin.mock;

import com.example.forkoin.model.Document;

/**
 * Created by luis.camilo on 22/09/2016.
 */
public class DocumentMock {
    private DocumentMock() {

    }

    public static DocumentMock newDocument() {
        return new DocumentMock();
    }

    public String[][] generateDocument(int start, int end, String word) {
        return Document.newInstance().generateDocument(start,end,word);
    }
}
