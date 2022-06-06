package zacyex.jvm.I_GarbageCollector;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author zacyex
 * @date 2022/2/10 21:18
 *
 * 1.Client模式默认使用Serial GC，Serial Old GC
 * 2.Server模式下: ①.Serial Old可与Parallel Scavenge搭配使用
 *               ②.Serial Old作为CMS垃圾回收器的后备垃圾回收器
 *               （CMS回收老年代时是与用户线程并行的，当回收速度慢于用户线程对象分配速度，
 *               导致内存依旧不足垃圾回收失败的情况下，通过Serial Old再进行回收）
 * 3.Serial 使用复制算法，Serial Old使用标记整理
 *
 * Serial和Serial Old是单线程的垃圾回收器，且与用户线程是串行的，会暂停用户线程的执行
 *
 * -XX:+UseSerialGC 来指定程序使用Serial和Serial Old垃圾回收器
 */
public class SerialGC {

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
