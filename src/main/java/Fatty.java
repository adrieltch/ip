import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.format.DateTimeParseException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Fatty {
    private static final String horizontalLine = "_".repeat(75);
    private static final String TASK_FILE = "./data/fatty.txt";

    private static TaskList taskList;
    private static Ui ui;


    public static void main(String[] args) {
        //Fatty fatty = new Fatty();
        ui = new Ui();
        taskList = new TaskList();

        loadTasks();
        ui.showWelcome();
        echo();
    }


    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                parseAndExecute(input, scanner);
            } catch (FattyException e) {
                ui.showError(e.getMessage());

            }
        }
    }

    private static void parseAndExecute(String input, Scanner scanner) throws FattyException {
        String[] parts = input.split(" ", 2);
        Command command = Command.fromString(parts[0]);


        switch (command) {
        case BYE:
            ui.showExit();
            scanner.close();
            System.exit(0);
            break;

        case LIST:
            ui.showTaskList(taskList);
            break;

        case MARK:
            if (parts.length < 2) throw new FattyException("You must specify a task number to isMark!");
            if (!parts[1].matches("\\d+")) {
                throw new FattyException("Task number must be a valid number!");
            }
            int markId = Integer.parseInt(parts[1]);

            taskList.mark(markId);
            saveTasks();

            ui.showMark(taskList.get(markId - 1));
            break;

        case UNMARK:
            if (parts.length < 2) throw new FattyException("You must specify a task number to unmark!");
            if (!parts[1].matches("\\d+")) {
                throw new FattyException("Task number must be a valid number!");
            }
            int unmarkId = Integer.parseInt(parts[1]);

            taskList.unmark(unmarkId);
            saveTasks();

            ui.showMark(taskList.get(unmarkId - 1));
            break;

        case TODO:
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new FattyException("The description of a todo cannot be empty.");
            }
            ToDos toDo = new ToDos(parts[1].trim());
            taskList.add(toDo);
            saveTasks();

            ui.showTaskAdded(toDo, taskList);
            break;

        case DEADLINE:
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new FattyException("The description of a deadline cannot be empty.");
            }

            String[] deadlineParts = parts[1].split("/by", 2);
            if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                throw new FattyException("Deadline must have a description and a /by <d/M/yyyy HHmm>.");
            }

            String deadlineName = deadlineParts[0].trim();
            String byInput = deadlineParts[1].trim();

            LocalDateTime by;
            try {
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                by = LocalDateTime.parse(byInput, inputFormat);
            } catch (DateTimeParseException e) {
                throw new FattyException("Invalid date/time format! Use d/M/yyyy HHmm (e.g., 21/12/2024 1800).");
            }

            Deadlines deadline = new Deadlines(deadlineName, by);

            taskList.add(deadline);
            saveTasks();

            ui.showTaskAdded(deadline, taskList);
            break;

        case EVENT:
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new FattyException("The description of an event cannot be empty.");
            }

            String[] eventParts = parts[1].split("/from", 2);
            if (eventParts.length < 2 || eventParts[0].trim().isEmpty()) {
                throw new FattyException("Event must have a description and /from <start>.");
            }

            String[] timeParts = eventParts[1].split("/to", 2);
            if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                throw new FattyException("Event must have both /from <start> and /to <end>.");
            }

            String eventName = eventParts[0].trim();
            String fromInput = timeParts[0].trim();
            String toInput = timeParts[1].trim();

            LocalDateTime from;
            LocalDateTime to;
            try {
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                from = LocalDateTime.parse(fromInput, inputFormat);
                to = LocalDateTime.parse(toInput, inputFormat);
            } catch (DateTimeParseException e) {
                throw new FattyException("Invalid date/time format! Use d/M/yyyy HHmm (e.g., 21/12/2024 1800).");
            }

            Events event = new Events(eventName, from, to);

            taskList.add(event);
            saveTasks();

            ui.showTaskAdded(event,taskList);

            break;

        case DELETE:
            if (parts.length < 2) {
                throw new FattyException("You must specify a task number to delete!");
            }
            if (!parts[1].matches("\\d+")) {
                throw new FattyException("Task number must be a valid number!");
            }
            int deleteId = Integer.parseInt(parts[1]);

            taskList.delete(deleteId);
            saveTasks();

            ui.showDelete(taskList.get(deleteId - 1), taskList);
            break;

        default:
            throw new FattyException("I don’t know what that means.");
        }
    }


    private static void loadTasks() {
        File file = new File(TASK_FILE);

        try {
            if (!file.exists()) {
                // First-time run: create the file and inform the user
                file.getParentFile().mkdirs();
                if (file.createNewFile()) {
                    System.out.println(horizontalLine + "\n"
                            + "No existing task file found. Fatty will create one for you...");
                }
                return; // Nothing to load yet
            }

            // If file exists, read and load tasks
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = Task.fromDataString(line);
                    taskList.add(task);
                }
            } catch (FattyException e) {
                System.out.println("⚠ Warning: Some tasks could not be loaded: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("☹ OOPS! An error occurred while setting up the task file: " + e.getMessage());
        }
    }


    private static void saveTasks() {
        File file = new File(TASK_FILE);

        try {
            file.getParentFile().mkdirs();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < taskList.size(); i++) {
                    Task task = taskList.get(i);
                    writer.write(task.toDataString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("☹ OOPS! Failed to save tasks: " + e.getMessage());
        }
    }

}





