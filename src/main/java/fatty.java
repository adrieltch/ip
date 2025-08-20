import java.util.*;

public class Fatty {
    private static final String horizontalLine = "_".repeat(75);

    private String list = "";
    private int count = 0;

    public static void main(String[] args) {
        Fatty fatty = new Fatty();
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

            switch (input) {
                case "bye":
                    bye();
                    scanner.close();
                    return;

                case "list":
                    getList();
                    break;

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
        + this.list + horizontalLine);
    }

    private void addList(String input) {
        this.count++;
        String temp = String.valueOf(this.count);
        this.list += temp + ". " + input + "\n";
    }

}





