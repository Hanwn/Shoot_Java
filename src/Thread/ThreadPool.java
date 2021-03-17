package Thread;

import Task.TaskQueue;

/**
 * projectName: Shoot
 * fileName: ThreadPool
 * packageName: Thread
 * author: Hanwn
 * date: 2021/3/16 22:30
 * github: https://www.github.com/Hanwn
 */

public class ThreadPool {

    public ThreadPool(int threadNum) {
        this.threadNum = threadNum;
    }

    private boolean shutdown;
    private int threadNum;
    private TaskQueue taskQueue;
}
