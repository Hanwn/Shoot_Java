package Thread;

import Task.TaskQueue;
import Timer.Timer;
/**
 * projectName: Shoot
 * fileName: ThreadWorker
 * packageName: Thread
 * author: Hanwn
 * date: 2021/3/16 22:30
 * github: https://www.github.com/Hanwn
 */

public class ThreadWorker implements Runnable{
    private Timer timer;
    private TaskQueue taskQueue;

    @Override
    public void run() {

    }

    public ThreadWorker() {}

    public ThreadWorker(Timer timer, TaskQueue taskQueue) {
        this.timer = timer;
        this.taskQueue = taskQueue;
    }

    private void doRequest() {

    }

    private void parseHeader() {

    }

    private void parseBody() {

    }
}
