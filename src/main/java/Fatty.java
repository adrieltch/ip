import java.util.*;

public class Fatty {
    private static final String horizontalLine = "_".repeat(75);

    private Task[] taskList;
    private int taskCount = 0;

    public static void main(String[] args) {
        Fatty fatty = new Fatty();
        fatty.taskList = new Task[100];

        fatty.greet();
        fatty.echo();
    }

    private void greet() {
        System.out.println( horizontalLine + "\n"
                + "Hello! I'm fatty.\n"
                + "What can I do for you?\n"
                + horizontalLine );
    }

    private void echo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];


            switch (command) {
                case "bye":
                    bye();
                    scanner.close();
                    return;

                case "list":
                    getList();
                    break;

                case "mark":
                    int markId = Integer.parseInt(parts[1]);
                    mark(markId); // mark task
                    break;


                case "unmark":
                    int unmarkId = Integer.parseInt(parts[1]);
                    unmark(unmarkId);
                    break;

                case "todo":
                    addList(new ToDos(parts[1]));
                    break;

                case "deadline":
                    String[] deadlineParts = parts[1].split(" /by ", 2);
                    addList(new Deadlines(deadlineParts[0], deadlineParts[1]));
                    break;

                case "event":
                    String[] eventParts = parts[1].split(" /from ", 2);
                    String[] timeParts = eventParts[1].split(" /to ", 2);

                    String from = timeParts[0];
                    String to = timeParts[1];

                    addList(new Events(eventParts[0], from, to));
                    break;

                default:
                    System.out.println(horizontalLine + "\n" +
                            "Please give an appropriate command!" + "\n" +
                            horizontalLine);
                    break;
            }
        }

    }

    private void bye(){
        System.out.println(horizontalLine + "\n"
                + "Bye. Hope to see you again soon!\n"
                + horizontalLine);
    }

    private void getList(){
        System.out.println(horizontalLine + "\n"
        + "here are the tasks in your list:");

        for (int i = 0; i < taskCount; i++) {
            int taskNum = i + 1;
            System.out.println(taskNum + "." + taskList[i]);
        }

        System.out.println(horizontalLine);
    }

    private void addList(Task task) {
        this.taskList[taskCount] = task;
        this.taskCount++;
        System.out.println(horizontalLine + "\n" +
                "Got it. Ive added this task:\n" +
                task + "\n" +
                "Now you have " + this.taskCount +
                " tasks in the list.\n" + horizontalLine);
    }

    private void mark(int id) {
        Task task = this.taskList[id - 1];
        task.mark();
        System.out.println(horizontalLine + "\n" +
                "Nice! I've marked this task as done:" + "\n" +
                task + "\n" + horizontalLine);
    }

    private void unmark(int id) {
        Task task = this.taskList[id - 1];
        task.unmark();
        System.out.println(horizontalLine + "\n" +
                "OK! I've marked this task as not done yet:" + "\n" +
                task + "\n" + horizontalLine);
    }
}





