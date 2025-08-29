import java.time.LocalDateTime;

public class EventTask extends Task{
    private static final String type = "[E]";
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventTask(String description, LocalDateTime from, LocalDateTime to){
        super(description);
        this.from = from;
        this.to = to;

    }

    @Override
    public String toString(){
        return type + super.toString() + " (from: " + this.from.format(DISPLAY_FORMAT)
                + " to: " + this.to.format(DISPLAY_FORMAT) + ")";
    }

    @Override
    public String toDataString() {
        return "E | " + (this.isMark ? "1" : "0") + " | " + this.description + " | "
                + this.from.format(SAVE_FORMAT) + " | " + this.to.format(SAVE_FORMAT);
    }
}

