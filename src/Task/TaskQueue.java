package Task;

/**
 * projectName: Shoot
 * fileName: TaskQueue
 * packageName: Task
 * author: Hanwn
 * date: 2021/3/16 22:28
 * github: https://www.github.com/Hanwn
 */

public class TaskQueue {

    public TaskQueue() {
        tasks = new Task();
        tail = tasks;
        taskNum = 0;
    }

    public void add(Task task) {
        tail.next = task;
        tail = task;
        taskNum += 1;
    }

    public Task getTask() {
        //TODO:need to complete
        Task ret = tasks.next;
        tasks = tasks.next;
        taskNum -= 1;
        return ret;
    }

    public int getTaskNum() {
        return taskNum;
    }

    private int taskNum;
    private Task tasks;
    private Task tail;
}
