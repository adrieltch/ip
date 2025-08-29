package fatty.command;

import fatty.FattyException;
import fatty.Storage;
import fatty.TaskList;
import fatty.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FattyException {
        taskList.unmark(index);
        storage.saveTasks(taskList);
        ui.showUnmark(taskList.get(index));
    }
}

