package zacyex.jvm.B_RuntimeDataArea.Heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author zacyex
 * @date 2021/12/19 17:46
 * -Xms600m -Xmx600m
 *
 * 运行并通过jvisualvm查看内存动态
 *
 * 垃圾收集分为两大类：一种是部分收集(Partial GC),一种是整堆收集(Full GC)
 * 部分收集：不会完整收集整个Java堆的垃圾，又可细分为：
 *      新生代收集(Minor GC / Young GC)：只是新生代(Eden,s0\s1)的垃圾收集；
 *      老年代收集(Major GC / Old GC)：只是老年代的垃圾收集。
 *          目前，只有CMS GC会有单独收集老年代的行为。
 *          注意：很多时候Major GC会和Full GC混淆使用，需要具体分辨是老年代回收还是整堆回收。
 *      混合收集(Mixed GC)：整个新生代以及部分老年代的垃圾收集。
 *          目前，只有G1 GC会有这种行为。
 * 整堆收集(Full GC)：收集整个java堆和方法区的垃圾。
 *
 */
public class HeapInstanceDemo {

    byte[] buffer = new byte[new Random().nextInt(1024*200)];

    public static void main(String[] args) {
        ArrayList<HeapInstanceDemo> list = new ArrayList<>();
        while(true){
            list.add(new HeapInstanceDemo());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
