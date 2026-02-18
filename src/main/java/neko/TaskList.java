package neko;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
        int originalSize = this.getSize();
        this.taskArr.add(task);

        assert this.getSize() == originalSize + 1
                : "Task not added correctly";
    }

    /**
     * Removes a task from the task list.
     *
     * @param inputIndex One-based index of the task to remove.
     */
    public void removeTask(int inputIndex) throws NekoException {
        if (inputIndex < 1 || inputIndex > this.taskArr.size()) {
            throw new NekoException("Meow... That task index doesn't exist!");
        }
        assert inputIndex >= 1 && inputIndex <= this.getSize()
                : "Invalid index in removeTask()";
        this.taskArr.remove(toZeroBasedIndex(inputIndex));
    }

    /**
     * Marks a task as completed.
     *
     * @param inputIndex One-based index of the task to mark.
     */
    public void markTask(int inputIndex) throws NekoException {
        this.getTask(toZeroBasedIndex(inputIndex)).setDone();
    }

    /**
     * Marks a task as not completed.
     *
     * @param inputIndex One-based index of the task to unmark.
     */
    public void unmarkTask(int inputIndex) throws NekoException {
        this.getTask(toZeroBasedIndex(inputIndex)).setUnDone();
    }

    public void snoozeTask(int inputIndex, int days) throws NekoException {
        this.getTask(toZeroBasedIndex(inputIndex)).snooze(days);
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
    public Task getTask(int index) throws NekoException {
        if (index < 0 || index >= this.taskArr.size()) {
            throw new NekoException("Meow... That task index doesn't exist!");
        }
        assert index >= 0 && index < this.getSize()
                : "Index out of bounds in getTask()";
        return this.taskArr.get(index);
    }

    public int toZeroBasedIndex(int oneBasedIndex) {
        return oneBasedIndex - 1;
    }

    /**
     * Returns a formatted list of tasks whose descriptions contain the given keyword.
     *
     * @param keyword Keyword to search for within task descriptions.
     * @return A formatted string of matching tasks, or an empty string if no matches are found.
     */
    public String find(String keyword) {
        return taskArr.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .map(task -> taskArr.indexOf(task) + 1 + ". " + task)
                .collect(Collectors.joining("\n"));
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
