package zacyex.jvm.I_GarbageCollector;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author zacyex
 * @date 2022/2/15 21:11
 * CMS垃圾回收器是并发的垃圾回收器(实现垃圾回收线程和用户线程同时工作)
 * 老年代垃圾回收器，标记清除算法，会产生STW
 *
 * CMS的工作原理：初始标记，并发标记，重新标记，并发清理，重置线程
 *
 * CPU 0 (用户线程)------> |                     | (用户线程)------> | [重新标记]——————> | (用户线程)------> | (用户线程)------>
 * CPU 1 (用户线程)------> |  [初始标记]————————>  | (用户线程)------> | [重新标记]——————> | (用户线程)------> | (用户线程)------>
 * CPU 2 (用户线程)------> |                     | [并发标记]——————> | [重新标记]——————> | [并发清理]——————> | [重置线程]——————>
 * CPU 3 (用户线程)------> |                     | (用户线程)------> | [重新标记]——————> | (用户线程)------> | (用户线程)------>
 *
 * 初始标记：工作线程STW暂停，这个阶段仅标记GCRoots直接关联到的对象（由于直接关联的对象很少，所以速度非常快）；
 * 并发标记：从GC Roots的直接关联对象开始标记整个对象图的过程，这个过程耗时长，但不需要停顿用户线程；
 * 重新标记：工作线程STW暂停，这个阶段用于修正与“并发标记”并发执行的用户线程导致的变动的那一部分对象的标记记录，
 *          耗时比"初始标记"长一些，但远比“并发标记短”；
 * 并发清理：清理掉标记为垃圾的对象（这儿存疑，可达性分析是标记存活的对象，但如果是标记存活的，但并发的用户线程产生的还未被标记）；
 *
 * CMS和其他垃圾回收器的触发方式不相同，堆内存使用率达到阈值时，便开始进行垃圾回收CMS工作过程中，
 * 以便于后续并行的工作线程可继续使用后续的内存，当预留的空间依旧无法满足后续程序需要，则会出现垃圾回收失败，
 * 虚拟机启用后备方案：临时启用Serial Old进行老年代的收集，此时停顿时间就很长了
 *
 *
 * -XX:+UseConcMarkSweepGC 指定使用CMS收集器
 *
 * -XX:CMSInitiatingOccupanyFraction 设置内存使用率阈值
 *     JDK5及以前默认为68，及68%
 *     JDK6以上为92，及92%，达到阈值会进行CMS的垃圾回收
 *
 * -XX:+UseCMSCompactAtFullCollection 执行完Full GC后堆内存空间进行压缩整理，
 *      压缩整理无法和用户线程并发执行
 *
 * -XX:CMSFullGCsBeforCompaction 设置在执行多少次Full GC后进行压缩整理
 *
 * -XX:ParallelCMSThreads 设置CMS的线程数 默认为(ParallelGCThreads + 3)/4
 *
 *
 *
 *
 */
public class CMSCollector {
    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();

        while(true){
            byte[] arr = new byte[100];
            list.add(arr);

            try{
                TimeUnit.MILLISECONDS.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}
