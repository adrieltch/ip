public class ToDos extends Task{
    private static final String type = "[T]";

    public ToDos(String name){
        super(name);
  }

    @Override
    public String toString(){
        return type + super.toString();
    }

    @Override
    public String toDataString() {
        return "T | " + (this.isMark ? "1" : "0") + " | " + this.name;
    }
}
