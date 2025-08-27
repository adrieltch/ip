public class Deadlines extends Task{
    private static final String type = "[D]";
    private final String by;

    public Deadlines(String name, String deadline){
        super(name);
        this.by = deadline;
    }

    @Override
    public String toString(){
        return type + super.toString() + " (by: " + this.by + ")";
    }
    @Override
    public String toDataString() {
        return "D | " + (this.isMark ? "1" : "0") + " | " + this.name + " | " + this.by;
    }
}

