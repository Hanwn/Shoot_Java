package Thread;

import Task.Task;
import Task.TaskQueue;
import Timer.Timer;

/**
 * projectName: Shoot
 * fileName: Reactor
 * packageName: Thread
 * author: Hanwn
 * date: 2021/3/16 22:30
 * github: https://www.github.com/Hanwn
 */

public class Reactor {


    public Reactor() {}

    public Reactor(Timer r, TaskQueue tq) {
        this.taskQueue = tq;
        this.timer = r;
    }

    private void accept() {

    }

    private void addToQueue() {

    }

    public void run() {

    }

    private Timer timer;
    private TaskQueue taskQueue;
}
