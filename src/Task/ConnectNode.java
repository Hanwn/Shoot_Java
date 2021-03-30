package Task;

import java.nio.channels.SelectionKey;

/**
 * projectName: Shoot
 * fileName: ConnectNode
 * packageName: Task
 * author: Hanwn
 * date: 2021/3/16 22:28
 * github: https://www.github.com/Hanwn
 */

public class ConnectNode {
    public ConnectNode() {}

    public ConnectNode(SelectionKey sk) {
        this.sk = sk;
        deleted = false;
    }

    public void setDeleted(boolean flag) {
        deleted = flag;
    }

    public SelectionKey getSk() {
        return sk;
    }

    private SelectionKey sk;
    private boolean deleted;
}
