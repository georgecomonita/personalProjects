package com.enered.course.thread;

import com.enered.course.model.HighPriorityToDo;

public class DeadlineCheckerRunnable implements Runnable {
    private HighPriorityToDo highPriorityToDo;

    public DeadlineCheckerRunnable(HighPriorityToDo highPriorityToDo) {
        System.out.println("Deadline Checker for " + highPriorityToDo + " initialized.");
        this.highPriorityToDo = highPriorityToDo;
    }

    @Override
    public void run() {
        while(highPriorityToDo.isDeadlineStillValid()) {
            System.out.println("Deadline still valid.");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Deadline expired for " + highPriorityToDo);
    }
}
