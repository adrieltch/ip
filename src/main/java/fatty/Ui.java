package fatty;

import fatty.task.Task;

import java.util.Scanner;

public class Ui {
    private static final String horizontalLine = "_".repeat(75);
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }


    public void showWelcome() {
        System.out.println(horizontalLine + "\n"
                + "Hello! I'm fatty.\n"
                + "What can I do for you?\n"
                + horizontalLine);
    }

    public void showExit() {
        System.out.println(horizontalLine + "\n"
                + "Bye. Hope to see you again soon!\n"
                + horizontalLine);
    }

    public void showTaskList(TaskList taskList) {
        System.out.println(horizontalLine + "\n"
                + "Here are the tasks in your list:\n"
                + taskList + "\n" + horizontalLine);
    }

    public void showTaskAdded(Task task, TaskList taskList) {
        System.out.println(horizontalLine + "\n" +
                "Got it. I've added this task:\n" +
                task + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.\n" +
                horizontalLine);
    }

    public void showMark(Task task) {
        System.out.println(horizontalLine + "\n" +
                "Nice! I've marked this task as done:\n" +
                task + "\n" + horizontalLine);
    }

    public void showUnmark(Task task) {
        System.out.println(horizontalLine + "\n" +
                "OK! I've marked this task as not done yet:\n" +
                task + "\n" + horizontalLine);
    }

    public void showDelete(Task task, TaskList taskList) {
        System.out.println(horizontalLine + "\n" +
                "Noted. I've removed this task:\n" +
                task + "\n" +
                "Now you have " + taskList.size() +
                " tasks in the list.\n" + horizontalLine);
    }
    public void showError(String message) {
        System.out.println(horizontalLine + "\n"
                + "☹ OOPS! Error: " + message + "\n"
                + horizontalLine);
    }
}
