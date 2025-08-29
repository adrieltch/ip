package fatty.command;

import fatty.Storage;
import fatty.TaskList;
import fatty.Ui;

/**
 * Display tasks in tasklist.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}


