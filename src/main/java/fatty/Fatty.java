package fatty;

import fatty.command.Command;

/**
 * Main logic for Fatty
 */
public class Fatty {
    /** File path to load and save taskList */
    private static final String TASK_FILE = "./data/fattyTasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Fatty() {
        this(TASK_FILE);
    }

    public Fatty(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FattyException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (FattyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     *
     * @param input the user input
     * @return the String from UI
     */
    public String getResponse(String input) {
        return "Fatty heard: " + input;
    }
}


