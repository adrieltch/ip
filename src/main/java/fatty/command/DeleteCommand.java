package fatty.command;

import fatty.FattyException;
import fatty.Storage;
import fatty.TaskList;
import fatty.Ui;

import fatty.task.Task;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FattyException {
        Task removed = taskList.get(index);
        taskList.delete(index);
        storage.saveTasks(taskList);
        ui.showDelete(removed, taskList);
    }
}
