public class Events extends Task{
    private static final String type = "[E]";
    private final String id;
    private String from;
    private String to;

    public Events(String name, String from, String to){
        super(name);
        this.id = type;
        this.from = from;
        this.to = to;

    }

    @Override
    public String toString(){
        return this.id + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}

