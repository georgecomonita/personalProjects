package com.enered.course.model;

import java.time.LocalDateTime;

public class HighPriorityToDo extends BaseToDo {
    private LocalDateTime deadline;

    public HighPriorityToDo(String content, LocalDateTime deadline) {
        super(content);
        this.deadline = deadline;
    }

    public HighPriorityToDo() {

    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isDeadlineStillValid() {
        return LocalDateTime.now().isBefore(deadline);
    }

    @Override
    public String toString() {
        String superValue = super.toString();
        return "HighPriorityToDo{" +
                "deadline=" + deadline + superValue +
                '}';
    }
}
