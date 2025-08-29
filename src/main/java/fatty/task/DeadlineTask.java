package fatty.task;

import java.time.LocalDateTime;

public class DeadlineTask extends Task{
    private static final String TYPE = "[D]";
    private final LocalDateTime by;

    public DeadlineTask(String description, LocalDateTime by){
        super(description);
        this.by = by;
    }

    @Override
    public String toString(){
        return TYPE + super.toString() + " (by: " + this.by.format(DISPLAY_FORMAT) + ")";
    }
    @Override
    public String toDataString() {
        return "D | " + (this.isMark ? "1" : "0") + " | " + this.description + " | " + this.by.format(SAVE_FORMAT);
    }

}

