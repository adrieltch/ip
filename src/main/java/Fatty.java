import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Fatty {
    private static final String horizontalLine = "_".repeat(75);
    private static final String TASK_FILE = "./data/fatty.txt";
    private final List<Task> taskList = new ArrayList<>();


    public static void main(String[] args) {
        Fatty fatty = new Fatty();

        fatty.loadTasks();
        fatty.greet();
        fatty.echo();
    }

    private void greet() {
        System.out.println(horizontalLine + "\n"
                + "Hello! I'm fatty.\n"
                + "What can I do for you?\n"
                + horizontalLine);
    }

    private void echo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                parseAndExecute(input, scanner);
            } catch (FattyException e) {
                System.out.println(horizontalLine + "\n"
                        + "☹ OOPS! Error: " + e.getMessage() + "\n"
                        + horizontalLine);
            } catch (Exception e) {
                System.out.println(horizontalLine + "\n"
                        + "☹ OOPS! Unexpected error: " + e.getMessage() + "\n"
                        + horizontalLine);
            }
        }
    }

    private void parseAndExecute(String input, Scanner scanner) throws FattyException {
        String[] parts = input.split(" ", 2);
        Command command = Command.fromString(parts[0]);


        switch (command) {
        case BYE:
            bye();
            scanner.close();
            System.exit(0);
            break;

        case LIST:
            getList();
            break;

        case MARK:
            if (parts.length < 2) throw new FattyException("You must specify a task number to isMark!");
            if (!parts[1].matches("\\d+")) {
                throw new FattyException("Task number must be a valid number!");
            }
            int markId = Integer.parseInt(parts[1]);
            mark(markId);
            break;

        case UNMARK:
            if (parts.length < 2) throw new FattyException("You must specify a task number to unmark!");
            if (!parts[1].matches("\\d+")) {
                throw new FattyException("Task number must be a valid number!");
            }
            int unmarkId = Integer.parseInt(parts[1]);
            unmark(unmarkId);
            break;

        case TODO:
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new FattyException("The description of a todo cannot be empty.");
            }
            addList(new ToDos(parts[1].trim()));
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

            addList(new Deadlines(deadlineName, by));
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

            addList(new Events(eventName, from, to));
            break;

        case DELETE:
            if (parts.length < 2) {
                throw new FattyException("You must specify a task number to delete!");
            }
            if (!parts[1].matches("\\d+")) {
                throw new FattyException("Task number must be a valid number!");
            }
            int deleteId = Integer.parseInt(parts[1]);
            delete(deleteId);
            break;

        default:
            throw new FattyException("I don’t know what that means.");
        }
    }

    private void bye() {
        System.out.println(horizontalLine + "\n"
                + "Bye. Hope to see you again soon!\n"
                + horizontalLine);
    }

    private void getList() {
        System.out.println(horizontalLine + "\n"
                + "Here are the tasks in your list:");

        for (int i = 0; i < taskList.size(); i++) {
            int taskNum = i + 1;
            System.out.println(taskNum + "." + taskList.get(i));
        }

        System.out.println(horizontalLine);
    }

    private void addList(Task task) throws FattyException {
        if (task == null) throw new FattyException("Task cannot be empty!");
        if (taskList.size() > 100) throw new FattyException("Task list is full!");

        this.taskList.add(task);
        saveTasks();

        System.out.println(horizontalLine + "\n" +
                "Got it. I've added this task:\n" +
                task + "\n" +
                "Now you have " + this.taskList.size() + " tasks in the list.\n" +
                horizontalLine);
    }

    private void mark(int id) throws FattyException {
        if (id <= 0 || id > taskList.size()) {
            throw new FattyException("Invalid task number: " + id);
        }
        Task task = taskList.get(id - 1);
        task.mark();
        saveTasks();
        System.out.println(horizontalLine + "\n" +
                "Nice! I've marked this task as done:\n" +
                task + "\n" + horizontalLine);
    }

    private void unmark(int id) throws FattyException {
        if (id <= 0 || id > taskList.size()) {
            throw new FattyException("Invalid task number: " + id);
        }
        Task task = taskList.get(id - 1);
        task.unmark();
        saveTasks();
        System.out.println(horizontalLine + "\n" +
                "OK! I've marked this task as not done yet:\n" +
                task + "\n" + horizontalLine);
    }

    private void delete(int id) throws FattyException {
        if (id <= 0 || id > taskList.size()) {
            throw new FattyException("Invalid task number!");
        }
        Task removed = taskList.remove(id - 1);
        saveTasks();
        System.out.println(horizontalLine + "\n" +
                "Noted. I've removed this task:\n" +
                removed + "\n" +
                "Now you have " + taskList.size() +
                " tasks in the list.\n" + horizontalLine);
    }

    private void loadTasks() {
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


    private void saveTasks() {
        File file = new File(TASK_FILE);

        try {
            file.getParentFile().mkdirs();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Task task : taskList) {
                    writer.write(task.toDataString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("☹ OOPS! Failed to save tasks: " + e.getMessage());
        }
    }

}





