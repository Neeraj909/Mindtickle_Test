package com.test.swagger.annotation;

public enum TesterName {
    MINDTICKLE("Mindtickle Test");

    public String authorName;
    TesterName(String authorName)
    {
        this.authorName=authorName;
    }

    public String toString() {
        return this.authorName;
    }
}
