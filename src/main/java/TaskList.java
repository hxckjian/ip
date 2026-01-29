package java;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArr;

    public TaskList(ArrayList<Task> taskArr) {
        this.taskArr = taskArr;
    }

    public TaskList() {
        this.taskArr = new ArrayList<>();
    }

    public Task addTask(Task task) {
        this.taskArr.add(task);
        return task;
    }

    public void removeTask(int inputIndex) {
        this.taskArr.remove(inputIndex - 1);
    }

    public void markTask(int inputIndex) {
        this.taskArr.get(inputIndex - 1).markDone();
    }

    public void unmarkTask(int inputIndex) {
        this.taskArr.get(inputIndex - 1).markUnDone();
    }

    public int getSize() {
        return this.taskArr.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= this.taskArr.size(); i++) {
            sb.append(i).append(". ").append(this.taskArr.get(i - 1));
            if (i != (this.taskArr.size())) {
                sb.append("\n");
            }
        }
//        if (sb.isEmpty()) {
//            throw new NekoException("List is empty!");
//        }
        return sb.toString();
    }
}
