package zacyex.jvm.DirectRam;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * @author zacyex
 * @date 2022/1/4 20:42
 *
 * JDK8中，方法区的实现为元空间，元空间使用的是直接内存
 *
 * 本案例演示直接内存的使用和释放
 * IO                 NIO(New IO / Non-Blocking IO)
 * byte[] / char[]    Buffer
 * Stream             Channel
 */
public class DirectRamDemo {

    private static final int BUFFER = 1024 * 1024 * 1024;

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER);
        System.out.println("直接内存分配完毕！");

        Scanner scanner = new Scanner(System.in);
        scanner.next();

        System.out.println("直接内存开始释放！");
        byteBuffer = null;
        System.gc();
        scanner.next();
    }
}
