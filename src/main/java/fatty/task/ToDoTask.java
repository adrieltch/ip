package fatty.task;



public class ToDoTask extends Task {
    private static final String type = "[T]";

    public ToDoTask(String description){
        super(description);
  }

    @Override
    public String toString(){
        return type + super.toString();
    }

    @Override
    public String toDataString() {
        return "T | " + (this.isMark ? "1" : "0") + " | " + this.description;
    }
}
