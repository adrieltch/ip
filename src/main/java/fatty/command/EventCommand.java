package fatty.command;

import fatty.FattyException;
import fatty.Storage;
import fatty.TaskList;
import fatty.Ui;
import fatty.task.EventTask;
import java.time.LocalDateTime;

/**
 * Create Event Task with given from and to times.
 */
public class EventCommand extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FattyException {
        EventTask event = new EventTask(description, from, to);
        taskList.addTask(event);
        storage.saveTasks(taskList);
        ui.showTaskAdded(event, taskList);
    }
}
