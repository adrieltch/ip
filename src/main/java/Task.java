public abstract class Task {
    protected boolean isMark;
    protected final String name;


    public Task(String name) {
        this.isMark = false;
        this.name = name;
    }

    @Override
    public String toString() {
        String check = "[ ]";
        if (this.isMark) {
            check = "[X]";
        }
        return check + " " + this.name;
    }

    public void mark() {
        this.isMark = true;
    }

    public void unmark() {
        this.isMark = false;
    }

    public abstract String toDataString();

    public static Task fromDataString(String data) throws FattyException {
        String[] parts = data.split(" \\| "); // type | status | description | times
        String type = parts[0];
        boolean done = parts[1].equals("1");
        switch (type) {
            case "T":
                Task todo = new ToDos(parts[2]);
                if (done) todo.mark();
                return todo;
            case "D":
                Task deadline = new Deadlines(parts[2], parts[3]);
                if (done) deadline.mark();
                return deadline;
            case "E":
                Task event = new Events(parts[2], parts[3], parts[4]);
                if (done) event.mark();
                return event;
            default:
                throw new FattyException("Unknown task type in file!");
        }
    }
}
