public class Deadlines extends Task{
    private static final String type = "[D]";
    private final String id;
    private String deadline;

    public Deadlines(String name, String deadline){
        super(name);
        this.id = type;
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return this.id + super.toString() + " (by: " + this.deadline + ")";
    }
}

//need to remove "/by"