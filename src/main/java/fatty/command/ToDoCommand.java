package fatty.command;

import fatty.FattyException;
import fatty.Storage;
import fatty.TaskList;
import fatty.Ui;
import fatty.task.ToDoTask;

/**
 * Create To do task with description.
 */
public class ToDoCommand extends Command {
    private final String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FattyException {
        if (description.isEmpty()) {
            throw new FattyException("The description of a todo cannot be empty.");
        }
        ToDoTask toDo = new ToDoTask(description);
        tasks.addTask(toDo);
        storage.saveTasks(tasks);
        ui.showTaskAdded(toDo, tasks);
    }
}
