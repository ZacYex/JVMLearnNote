package zacyex.jvm.I_GarbageCollector;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author zacyex
 * @date 2022/2/10 21:36
 *
 * ParNew GC:垃圾回收线程是并发的新生代垃圾回收器
 * 采用复制算法，会产生STW
 *
 * JDK8中：ParNew GC - CMS GC可搭配使用
 *        ParNew GC - Serial Old GC可搭配使用
 *
 * JDK9及之后：ParNew GC - CMS GC可搭配使用
 *
 * -XX:+UseParNewGC 使用ParNew垃圾回收器(可与-XX:+UseConcMarkSweepGC 一起使用)
 * -XX:ParalleGCThreads   限制线程数量，默认开启和cpu数据相同的线程数
 */
public class ParNewGC {

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
