import java.time.LocalDate;
import java.time.LocalDateTime;

public class Events extends Task{
    private static final String type = "[E]";
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Events(String name, LocalDateTime from, LocalDateTime to){
        super(name);
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
        return "E | " + (this.isMark ? "1" : "0") + " | " + this.name + " | "
                + this.from.format(SAVE_FORMAT) + " | " + this.to.format(SAVE_FORMAT);
    }
}

