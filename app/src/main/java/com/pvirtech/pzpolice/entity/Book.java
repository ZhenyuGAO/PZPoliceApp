package com.pvirtech.pzpolice.entity;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by pd on 2016/11/9.
 */

public class Book  extends RealmObject{
    @Ignore
    private int sessionId;

    private String author;
    private String name;

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
