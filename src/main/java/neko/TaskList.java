package neko;

import java.util.ArrayList;

import neko.task.Task;

/**
 * Represents a list of {@link Task} objects and provides operations
 * to add, remove, update, search, and retrieve tasks.
 */
public class TaskList {
    private ArrayList<Task> taskArr;

    /**
     * Creates a task list using an existing list of tasks.
     *
     * @param taskArr List of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> taskArr) {
        this.taskArr = taskArr;
    }

    /**
     *  Creates an empty task list.
     */
    public TaskList() {
        this.taskArr = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskArr.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param inputIndex One-based index of the task to remove.
     */
    public void removeTask(int inputIndex) {
        this.taskArr.remove(inputIndex - 1);
    }

    /**
     * Marks a task as completed.
     *
     * @param inputIndex One-based index of the task to mark.
     */
    public void markTask(int inputIndex) {
        this.taskArr.get(inputIndex - 1).setDone();
    }

    /**
     * Marks a task as not completed.
     *
     * @param inputIndex One-based index of the task to unmark.
     */
    public void unmarkTask(int inputIndex) {
        this.taskArr.get(inputIndex - 1).setUnDone();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Total number of tasks.
     */
    public int getSize() {
        return this.taskArr.size();
    }

    /**
     * Returns the underlying task list.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTaskArr() {
        return this.taskArr;
    }

    /**
     * Returns a task at the specified index.
     *
     * @param index Zero-based index of the task.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return this.taskArr.get(index);
    }

    /**
     * Returns a formatted list of tasks whose descriptions contain the given keyword.
     *
     * @param keyword Keyword to search for within task descriptions.
     * @return A formatted string of matching tasks, or an empty string if no matches are found.
     */
    public String find(String keyword) {
        StringBuilder sb = new StringBuilder();
        int index = 1;

        for (Task task : this.taskArr) {
            if (task.getDescription().contains(keyword)) {
                sb.append(index++).append(". ").append(task).append("\n");
            }
        }
        return sb.toString().trim();
    }

    /**
     * Returns a formatted string representation of the task list.
     *
     * @return Formatted task list string.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= this.taskArr.size(); i++) {
            sb.append(i).append(". ").append(this.taskArr.get(i - 1));
            if (i != (this.taskArr.size())) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
