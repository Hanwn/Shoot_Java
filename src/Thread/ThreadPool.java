package Thread;

import Task.TaskQueue;
import Timer.Timer;
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
        createThread();
    }

    private void createThread() {
        for (int i = 0; i < threadNum; ++i) {
            ThreadWorker threadWorker = new ThreadWorker(timer, taskQueue);
            Thread t = new Thread(threadWorker);
            t.start();
        }
    }

    public boolean isShutdown() {
        return shutdown;
    }

    private Timer timer;
    private boolean shutdown;
    private int threadNum;
    private TaskQueue taskQueue;
}
