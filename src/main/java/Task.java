public class Task {
    private boolean mark;
    private final String name;


    public Task(String name) {
        this.mark = false;
        this.name = name;
    }

    @Override
    public String toString() {
        String check = "[ ]";
        if (this.mark) {
            check = "[X]";
        }
        return check + " " + this.name;
    }

    public void mark() {
        this.mark = true;
    }

    public void unmark() {
        this.mark = false;
    }
}
