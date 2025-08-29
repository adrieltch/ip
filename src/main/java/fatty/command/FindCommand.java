package fatty.command;

import fatty.FattyException;
import fatty.Storage;
import fatty.TaskList;
import fatty.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FattyException {
        TaskList found = taskList.find(keyword);
        ui.showFind(found);
    }
}
