public class Events extends Task{
    private static final String type = "[E]";
    private final String from;
    private final String to;

    public Events(String name, String from, String to){
        super(name);
        this.from = from;
        this.to = to;

    }

    @Override
    public String toString(){
        return type + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toDataString() {
        return "E | " + (this.isMark ? "1" : "0") + " | " + this.name + " | " + this.from + " | " + this.to;
    }
}

