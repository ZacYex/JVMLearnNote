package zacyex.jvm.B_RuntimeDataArea.Heap;

/**
 * @author zacyex
 * @date 2021/12/15 20:18
 *
 * 通过main方法构建一个进程，设置运行时参数，通过jvisualvm查看内存分配情况
 * -Xms 堆空间起始大小(年轻代+老年代)
 * -Xmx 堆空间最大大小(年轻代+老年代)
 * -XX:+PrintGCDetails
 *
 * 堆空间默认大小：
 * 初始内存大小：物理电脑内存大小/64
 * 最大内存大小：物理电脑内存大小/4
 * 开发中建议将初始堆内存和最大堆内存设置成相同的值，避免不断的申请内存，以及GC后释放内存
 *
 * 查看设置的参数：方式一：jps找到对应java进程的pid jstat -gc pid 进行查看各个区内存使用情况
 *              方式二：-XX:+PrintGCDetails
 */
public class HeapDemoOne {
    public static void main(String[] args) {

        // 返回Java虚拟机中堆内存总量
        long initialMemory = Runtime.getRuntime().totalMemory()/1024/1024;

        // 返回Java虚拟机试图使用的最大堆内存亮
        long maxMemory = Runtime.getRuntime().maxMemory()/1024/1024;

        System.out.println("-Xms:"+initialMemory+"m");
        System.out.println("-Xmx:"+maxMemory+"m");

        new Thread(()->{
            try {
                Thread.sleep(10000000);
            }catch (Exception ex){
                System.out.println(ex);
            }
        },"one").start();
    }
}
