package fatty.command;

import fatty.FattyException;
import fatty.Storage;
import fatty.TaskList;
import fatty.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws FattyException;

    public boolean isExit() {
        return false;
    }
}