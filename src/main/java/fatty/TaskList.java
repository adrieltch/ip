package fatty;

import fatty.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) throws FattyException {
        if (task == null) {
            throw new FattyException("Task cannot be empty!");
        }
        if (tasks.size() > 100) {
            throw new FattyException("Task list is full!");
        }

        tasks.add(task);
    }

    public Task get(int index) throws FattyException {
        if (tasks.isEmpty() || index < 1 || index > tasks.size()) {
            throw new FattyException("Invalid task Number!");
        }
        return tasks.get(index - 1);
    }

    public void delete(int index) throws FattyException {
        if (tasks.isEmpty() || index < 1 || index > tasks.size()) {
            throw new FattyException("Invalid task Number!");
        }

        tasks.remove(index - 1);
    }

    public void mark(int index) throws FattyException {
        if (tasks.isEmpty() || index < 1 || index > tasks.size()) {
            throw new FattyException("Invalid task number: " + index);
        }

        tasks.get(index - 1).mark();
    }

    public void unmark(int index) throws FattyException{
        if (tasks.isEmpty() || index < 1 || index > tasks.size()) {
            throw new FattyException("Invalid task number: " + index);
        }

        tasks.get(index - 1).unmark();
    }

    public int size() {
        return tasks.size();
    }

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

