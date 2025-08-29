package fatty.command;

import fatty.FattyException;
import fatty.Storage;
import fatty.TaskList;
import fatty.task.DeadlineTask;
import fatty.Ui;

import java.time.LocalDateTime;


/**
 * Command to create deadline task
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FattyException {
        DeadlineTask deadline = new DeadlineTask(description, by);

        taskList.addTask(deadline);
        storage.saveTasks(taskList);
        ui.showTaskAdded(deadline, taskList);
    }
}
