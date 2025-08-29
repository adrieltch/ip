package fatty.command;

import fatty.Storage;
import fatty.TaskList;
import fatty.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}