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
            String input = scanner.next();

            switch (input) {
                case "bye":
                    bye();
                    scanner.close();
                    return;

                case "list":
                    getList();
                    break;

                case "mark":
                    if (scanner.hasNextInt()) {
                        int id = scanner.nextInt();
                        mark(id); // mark task
                        break;
                    }

                case "unmark":
                    if (scanner.hasNextInt()) {
                        int id = scanner.nextInt();
                        unmark(id);
                        break;
                    }

                default:
                    addList(input);
                    System.out.println(horizontalLine + "\n" +
                            "added: " + input + "\n" +
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

    private void addList(String input) {
        this.taskList[taskCount] = new Task(input);
        this.taskCount++;
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





