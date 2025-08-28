import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;
            }
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = Task.fromDataString(line);
                    tasks.add(task);
                }
            }
        } catch (Exception e) {
            System.out.println("⚠ Failed to load tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(TaskList tasks) {
        File file = new File(filePath);

        try {
            file.getParentFile().mkdirs();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    writer.write(task.toDataString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("☹ OOPS! Failed to save tasks: " + e.getMessage());
        }
    }
}

