package neko.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void newTask_initialState_notDone() {
        Task task = new Task("read book");
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void markDone_taskMarkedDone_statusIconX() {
        Task task = new Task("read book");
        task.markDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void markUnDone_afterMarkDone_taskNotDone() {
        Task task = new Task("read book");
        task.markDone();
        task.markUnDone();
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void toString_doneTask_correctFormat() {
        Task task = new Task("read book");
        task.markDone();
        assertEquals("[X] read book", task.toString());
    }

    @Test
    public void formatIntoData_notDoneTask_correctFormat() {
        Task task = new Task("read book");
        assertEquals("0 | read book", task.formatIntoData());
    }
}
