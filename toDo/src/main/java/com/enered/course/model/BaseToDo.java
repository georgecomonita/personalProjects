package com.enered.course.model;

public class BaseToDo {
    private String content;

    public BaseToDo(String content) {
        this.content = content;
    }

    public BaseToDo() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BaseToDo{" +
                "content='" + content + '\'' +
                '}';
    }
}
