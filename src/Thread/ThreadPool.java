package Thread;

import Task.TaskQueue;
import Timer.Timer;
import Status.ThreadPoolStatus;
/**
 * projectName: Shoot
 * fileName: ThreadPool
 * packageName: Thread
 * author: Hanwn
 * date: 2021/3/16 22:30
 * github: https://www.github.com/Hanwn
 */

public class ThreadPool {
    public ThreadPool() {}

    public ThreadPool(int threadNum,Timer timer, TaskQueue taskQueue) {
        this.threadNum = threadNum;
        this.taskQueue = taskQueue;
        this.timer = timer;
        this.started = 0;
        this.status = ThreadPoolStatus.NORMAL;
        createThread();
    }

    private void createThread() {
        for (int i = 0; i < threadNum; ++i) {
            ThreadWorker threadWorker = new ThreadWorker(timer, taskQueue, this);
            Thread t = new Thread(threadWorker);
            t.start();
        }
    }

    public ThreadPoolStatus isShutdown() {
        return status;
    }

    private int started;
    private Timer timer;
    private int threadNum;
    private ThreadPoolStatus status;
    private TaskQueue taskQueue;
}
