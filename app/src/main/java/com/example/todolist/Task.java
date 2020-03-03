package com.example.todolist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String text;
    private LocalDateTime dueTime;
    private boolean isDone;

    public Task(String text, LocalDateTime dueTime) {
        this.text = text;
        this.dueTime = dueTime;
        isDone = false;
    }

    @Override
    public String toString() {
        return text + " " + dueTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(LocalDateTime dueTime) {
        this.dueTime = dueTime;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
