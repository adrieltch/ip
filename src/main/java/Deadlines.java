import java.time.LocalDateTime;

public class Deadlines extends Task{
    private static final String TYPE = "[D]";
    private final LocalDateTime by;

    public Deadlines(String name, LocalDateTime by){
        super(name);
        this.by = by;
    }

    @Override
    public String toString(){
        return TYPE + super.toString() + " (by: " + this.by.format(DISPLAY_FORMAT) + ")";
    }
    @Override
    public String toDataString() {
        return "D | " + (this.isMark ? "1" : "0") + " | " + this.name + " | " + this.by.format(SAVE_FORMAT);
    }

}

