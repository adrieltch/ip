public class ToDos extends Task{
    private static final String type = "[T]";
    private final String id;

    public ToDos(String name){
        super(name);
        this.id = type;
    }

    @Override
    public String toString(){
        return this.id + super.toString();
    }
}
