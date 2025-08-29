import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected boolean isMark;
    protected final String description;

    protected static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    protected static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");



    public Task(String description) {
        this.isMark = false;
        this.description = description;
    }

    @Override
    public String toString() {
        String check = "[ ]";
        if (this.isMark) {
            check = "[X]";
        }
        return check + " " + this.description;
    }

    public void mark() {

        this.isMark = true;
    }

    public void unmark() {
        this.isMark = false;
    }

    public abstract String toDataString();

    public static Task fromDataString(String data) throws FattyException {
        String[] parts = data.split(" \\| "); // type | status | description | times (d/M/yyyy HHmm)
        String type = parts[0];
        boolean done = parts[1].equals("1");
        switch (type) {
            case "T":
                Task todo = new ToDoTask(parts[2]);
                if (done) todo.mark();
                return todo;
            case "D":
                LocalDateTime deadlineBy = LocalDateTime.parse(parts[3], SAVE_FORMAT);
                Task deadline = new DeadlineTask(parts[2], deadlineBy);
                if (done) deadline.mark();
                return deadline;
            case "E":
                LocalDateTime eventBy = LocalDateTime.parse(parts[3], SAVE_FORMAT);
                LocalDateTime eventTo = LocalDateTime.parse(parts[4], SAVE_FORMAT);

                Task event = new EventTask(parts[2], eventBy, eventTo);
                if (done) event.mark();
                return event;
            default:
                throw new FattyException("Unknown task type in file!");
        }
    }
}
