package zacyex.jvm.B_RuntimeDataArea.MethodArea;

/**
 * @author zacyex
 * @date 2021/12/27 20:49
 *
 * JDK7:设置永久代的大小(方法区在jdk7及之前的实现方式)
 * -XX:MaxPermSize=  设置永久代的最大内存(32位机默认值是64M，64位机默认是82M)
 * -XX:PermSize= 设置永久代的初始内存(默认值是20.75M)
 *
 * JDK8:设置元空间的大小(方法区在jdk8之后的实现方式)'
 * -XX:MaxMetaspaceSize=  设置元空间的最大内存(默认为-1没有限制，jdk8的元空间使用的是直接内存，而非虚拟机内存)
 * -XX:MetaspaceSize= 设置元空间的初始内存(默认值是21M)
 *
 */
public class MethodAreaDemo {
    public static void main(String[] args) {
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
