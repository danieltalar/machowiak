import java.util.List;

public class Problem {


    private int sumTasks;
    private int dueDate;
    List<Task> tasks;
    private int beginTime = 0;

    public int getSumTasks() {
        return sumTasks;
    }

    public void setSumTasks(int sumTasks) {
        this.sumTasks = sumTasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }
}
