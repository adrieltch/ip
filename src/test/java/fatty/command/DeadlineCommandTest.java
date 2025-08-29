package fatty.command;

import fatty.FattyException;
import fatty.Storage;
import fatty.TaskList;
import fatty.Ui;
import fatty.task.DeadlineTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineCommandTest {

    private TaskList taskList;
    private TestUi ui;
    private TestStorage storage;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        ui = new TestUi();
        storage = new TestStorage();
    }

    @Test
    void execute_addsDeadlineTask_success() throws FattyException {
        LocalDateTime by = LocalDateTime.of(2025, 8, 30, 23, 59);
        DeadlineCommand command = new DeadlineCommand("finish assignment", by);

        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.size(), "Task list should contain 1 task.");
        assertInstanceOf(DeadlineTask.class, taskList.get(1), "Task should be a DeadlineTask.");

        DeadlineTask task = (DeadlineTask) taskList.get(1);
        assertEquals("[D][ ] finish assignment (by: Aug 30 2025, 11:59 pm)", task.toString());
        assertEquals("D | 0 | finish assignment | 30/8/2025 2359", task.toDataString());

        assertTrue(ui.messageShown, "Ui should have shown task added message.");
        assertTrue(storage.saved, "Storage should have saved tasks.");
    }

    // --- Minimal test doubles ---
    private static class TestUi extends Ui {
        boolean messageShown = false;

        @Override
        public void showTaskAdded(fatty.task.Task task, TaskList taskList) {
            messageShown = true; // just mark that it was called
        }
    }

    private static class TestStorage extends Storage {
        boolean saved = false;

        public TestStorage() {
            super("test.txt"); // dummy file path
        }

        @Override
        public void saveTasks(TaskList tasks) {
            saved = true; // don’t actually write to file
        }
    }
}
