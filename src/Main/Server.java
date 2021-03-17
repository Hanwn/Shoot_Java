package Main;

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
        reactor = new Reactor();
        threadPool = new ThreadPool(4);
    }

    private void start() {
        System.out.println("============================================");
        System.out.println("========Shoot Http Server is Running========");
        System.out.println("============================================");
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.init();
        server.start();
    }

    private Timer timer;
    private Reactor reactor;
    private ThreadPool threadPool;

}
