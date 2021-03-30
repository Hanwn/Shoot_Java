package Thread;

import Task.Task;
import Task.TaskQueue;
import Timer.Timer;
import Status.ThreadPoolStatus;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

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
    private final int defaultBufferSize = 8192;

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
        //TODO: parse url,header,body, etc.
        SelectionKey sk = task.getSk();
        if (sk == null) {
            System.out.println("null");
        }
        String requestMsg = "";
        SocketChannel sChannel = (SocketChannel)sk.channel();
        try {
            ByteBuffer buffer = ByteBuffer.allocate(defaultBufferSize);
            int cnt = 0;
            while((cnt = sChannel.read(buffer)) > 0) {
                buffer.flip();
                requestMsg += new String(buffer.array(),0,buffer.limit());
                buffer.clear();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        String test = "HTTP/1.0 200 OK\r\n"
                + "Connection: close\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head><title>ShootServer</title></head>"
                + "<body><h1>Welcome to ShootServer</h1><p>This is the test page of Shoot.</p></body>"
                + "</html>";
        ByteBuffer buffer = ByteBuffer.wrap(test.getBytes());
        try {
            sk.cancel();
            sChannel.write(buffer);
            sChannel.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        parseHeader();
        parseBody();

    }

    private void parseHeader() {

    }

    private void parseBody() {

    }
}
