import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

//    public TaskList() {
//        this.tasks = new ArrayList<>();
//    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public void add(Task task) throws FattyException {
        if (task == null) throw new FattyException("Task cannot be empty!");
        if (tasks.size() > 100) throw new FattyException("Task list is full!");

        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public void mark(int index) throws FattyException {
        if (index <= 0 || index > this.size()) {
            throw new FattyException("Invalid task number: " + index);
        }

        tasks.get(index - 1).mark();
    }

    public void unmark(int index) throws FattyException{
        if (index <= 0 || index > this.size()) {
            throw new FattyException("Invalid task number: " + index);
        }

        tasks.get(index - 1).unmark();
    }

    public int size() {
        return tasks.size();
    }

//    public ArrayList<Task> getAll() {
//        return tasks;
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            int taskNum = i + 1;
            sb.append(taskNum)
                    .append(". ")
                    .append(tasks.get(i))
                    .append("\n");
        }
        return sb.toString().trim();
    }

}

