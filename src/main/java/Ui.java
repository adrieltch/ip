public class Ui {
    private static final String horizontalLine = "_".repeat(75);

    protected void showWelcome() {
        System.out.println(horizontalLine + "\n"
                + "Hello! I'm fatty.\n"
                + "What can I do for you?\n"
                + horizontalLine);
    }

    protected void showExit() {
        System.out.println(horizontalLine + "\n"
                + "Bye. Hope to see you again soon!\n"
                + horizontalLine);
    }

    protected void showTaskList(TaskList taskList) {
        System.out.println(horizontalLine + "\n"
                + "Here are the tasks in your list:\n"
                + taskList + "\n" + horizontalLine);
    }

    protected void showTaskAdded(Task task, TaskList taskList) {
        System.out.println(horizontalLine + "\n" +
                "Got it. I've added this task:\n" +
                task + "\n" +
                "Now you have " + taskList.getSize() + " tasks in the list.\n" +
                horizontalLine);
    }

    protected void showMark(Task task) {
        System.out.println(horizontalLine + "\n" +
                "Nice! I've marked this task as done:\n" +
                task + "\n" + horizontalLine);
    }

    protected void showUnmark(Task task) {
        System.out.println(horizontalLine + "\n" +
                "OK! I've marked this task as not done yet:\n" +
                task + "\n" + horizontalLine);
    }

    protected void showDelete(Task task, TaskList taskList) {
        System.out.println(horizontalLine + "\n" +
                "Noted. I've removed this task:\n" +
                task + "\n" +
                "Now you have " + taskList.getSize() +
                " tasks in the list.\n" + horizontalLine);
    }
    protected void showError(String message) {
        System.out.println(horizontalLine + "\n"
                + "☹ OOPS! Error: " + message + "\n"
                + horizontalLine);
    }
}
