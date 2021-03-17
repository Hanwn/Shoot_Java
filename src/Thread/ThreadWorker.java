package Thread;

import Task.Task;
import Task.TaskQueue;
import Timer.Timer;
import Status.ThreadPoolStatus;
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
    private ThreadPool threadPool;
    private TaskQueue taskQueue;

    @Override
    public void run() {
        while (true) {
            Task tf;
            synchronized (taskQueue) {
                while (taskQueue.getTaskNum() == 0 && threadPool.isShutdown() == ThreadPoolStatus.NORMAL) {
                    /**
                     * 需要wait操作
                     */
                    try {
                        taskQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (threadPool.isShutdown() == ThreadPoolStatus.IMMEDIATE_SHUTDOWN) {
                    /**
                     * 立即关机
                     */
                    break;
                } else if (threadPool.isShutdown() == ThreadPoolStatus.GRACEFUL_SHUTDOWN && taskQueue.getTaskNum() == 0) {
                    break;
                }
                tf = taskQueue.getTask();
            }
            doRequest(tf);
        }
    }

    public ThreadWorker() {}

    public ThreadWorker(Timer timer, TaskQueue taskQueue, ThreadPool threadPool) {
        this.timer = timer;
        this.taskQueue = taskQueue;
        this.threadPool = threadPool;
    }

    private void doRequest(Task task) {
        System.out.println("doRequest");
    }

    private void parseHeader() {

    }

    private void parseBody() {

    }
}
