package ua.pashanko.neuronudge.tasks;

public class Task {

    private int id;
    private String task;

    public Task(String taskName, int id) {
        this.task = taskName;
        this.id = id;
    }

    public Task(String taskName) {
        this.task = taskName;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String taskName) {
        this.task = taskName;
    }

    public int getTaskId() {
        return id;
    }

    public void setTaskId(int id) {
        this.id = id;
    }
}