package zacyex.jvm.I_GarbageCollector;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author zacyex
 * @date 2022/2/15 20:39
 *
 * Parallel Scavenge收集器：吞吐量优先（在设置延迟时间的基础上提供最高的吞吐量）
 * 新生代的垃圾回收器，采用复制算法，并行回收，会产生STW
 * 搭配Parallel Old收集器，采用标记压缩，并行回收，会产生STW
 * JDK8 的默认垃圾回收器
 *
 * -XX:+UseParallelGC 指定新生代使用Parallel Scavenge
 * -XX:+UseParallelOldGC 指定老年代使用Parallel Old
 * (上述两个参数设置一个另一个也会同时生效)
 *
 * -XX:ParallelGCThreads 设置并行收集器的线程数
 * (默认情况下，CPU小于8个，等于CPU的个数，CUP大于8个，等于(3+[5*CPU_Count]/8))
 *
 * -XX:MaxGCPauseMillis 设置垃圾回收最大停顿时间（即STW的时间），单位是毫秒
 *    慎用：为了尽量把停顿时间控制在MaxGCPauseMillis以内，收集器在工作时会调整Java堆大小或者其他一些参数
 *
 * -XX:GCTimeRatio 设置垃圾收集时间占总时间的比例(=1/(N+1))，用于衡量吞吐量的大小
 *      取值范围(0,100)，默认是99，也就是垃圾回收时间不超过1%
 *
 * -XX:+UseAdaptiveSizePolicy 设置Parallel Scavenge收集器具备自适应调节策略
 *      这种模式下，年轻代的大小，Eden和Suvivor的比例，晋升老年代的对象年龄等参数会被自动调整，默认为开启
 */
public class ParallelCollector {
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
