package Task;

import java.nio.channels.SelectionKey;

/**
 * projectName: Shoot
 * fileName: Task
 * packageName: Task
 * author: Hanwn
 * date: 2021/3/16 22:27
 * github: https://www.github.com/Hanwn
 */

public class Task {

    public Task() {}

    public Task(SelectionKey sk) {
        this.sk = sk;
        next = null;
    }

    public SelectionKey getSk() {
        return sk;
    }

    private SelectionKey sk;
    public Task next;

}
