package Main;

import Task.TaskQueue;
import Timer.Timer;
import Thread.ThreadPool;
import Thread.Reactor;

/**
 * projectName: Shoot
 * fileName: Server
 * packageName: Main
 * author: Hanwn
 * date: 2021/3/16 22:27
 * github: https://www.github.com/Hanwn
 */

public class Server {

    private void init() {
        timer = new Timer();
        taskQueue = new TaskQueue();
        reactor = new Reactor(timer, taskQueue);
        threadPool = new ThreadPool(4, timer, taskQueue);
    }

    private void start() {
        System.out.println("============================================");
        System.out.println("========Shoot Http Server is Running========");
        System.out.println("============================================");
        try {
            reactor.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.init();
        server.start();
    }

    private Timer timer;
    private Reactor reactor;
    private TaskQueue taskQueue;
    private ThreadPool threadPool;

}
