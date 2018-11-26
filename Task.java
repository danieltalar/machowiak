

public class Task {


    private int id;
    private int time;
    private int earliness;
    private int delay;

    public Task() {
    }

    public Task(int id, int time, int earliness, int delay) {
        this.id = id;
        this.time = time;
        this.earliness = earliness;
        this.delay = delay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getEarliness() {
        return earliness;
    }

    public void setEarliness(int earliness) {
        this.earliness = earliness;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
