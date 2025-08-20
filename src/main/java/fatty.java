import java.util.*;

public class fatty {
    private static final String horizontalLine = "_".repeat(75);

    public static void main(String[] args) {
        fatty.greet();
        fatty.echo();
    }

    private static void greet() {
        System.out.println( horizontalLine + "\n"
                + "Hello! I'm fatty.\n"
                + "What can I do for you?\n"
                + horizontalLine);
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                fatty.bye();
                scanner.close();
                return;
            } else {
                System.out.println(horizontalLine + "\n" +
                        input + "\n" +
                        horizontalLine);
            }

        }
    }

    private static void bye(){
        System.out.println(horizontalLine + "\n"
                + "Bye. Hope to see you again soon!\n"
                + horizontalLine);
    }

}





