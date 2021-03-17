package Thread;

import Task.Task;
import Task.TaskQueue;
import Timer.Timer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

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


    private void addToQueue(Task task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public void run() throws IOException {
        /**
         * 在linux提供的epoll中，是存在边缘触发和水平触发两种模式的，在java的nio中如何体现？
         * 在传输大文件（缓冲区无法一次传输完成）的情况下，应该如何处理
         */
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9618));
        ssc.configureBlocking(false);
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey sk = it.next();
                if (sk.isAcceptable()) {
                    /**
                     *
                     */
                    SocketChannel schannel = ssc.accept();
                    schannel.configureBlocking(false);
                    schannel.register(selector, SelectionKey.OP_READ);
                    // TODO:往堆里插入元素
                    // timer.getHeap();
                }else if (sk.isReadable()) {
                    //TODO:优化性能，这里需要获得锁，是否可以转化为异步，只要往队列中加入即可，不要等待加入成功就直接返回
                    addToQueue(new Task(sk));
                }
                // 处理完毕，移除当前事件
                it.remove();
            }
        }
    }

    private Timer timer;
    private TaskQueue taskQueue;
}
