package zacyex.jvm.B_RuntimeDataArea.Heap;

/**
 * @author zacyex
 * @date 2021/12/21 20:32
 *
 * 堆空间常用的相关参数：
 * -XX:+PrintFlagsInitial   查看所有参数的默认初始值
 * -XX:+PrintFlagsFinal  查看所有参数的最终值（可能会存在修改，不再是初始值）
 * -Xms       初始堆空间内存（默认为物理内存的1/64）
 * -Xmx       最大堆空间内存（默认为物理内存的1/4）
 * -Xmn       设置新生代的大小。（新生代初始值和最大值都是这个参数）
 * -XX:NewRatio=     配置老年代和新生代的占比（值为老年代比新生代的比值）
 * -XX:SurvivorRatio=   设置新生代中Eden和s0/s1的空间占比
 * -XX:MaxTenuringThreshold=    设置新生代对象的最大阈值，到达阈值后进入老年代
 * -XX:+PrintGCDetails   输出详细的GC处理日志，且程序结束时会输出各个空间的内存情况
 * 打印GC简要信息： 1. -XX:+PrintGC   2. -verbose:gc
 * -XX:HandlePromotionFailure=  设置空间分配担保
 *     解释：在发生Minor GC之前，虚拟机检查老年代最大可用连续空间，是否大于新生代所有对象的总空间
 *          如果大于，则此次Minor GC是安全的
 *          如果小于，则虚拟机会查看-XX:HandlePromotionFailure设置值是否允许担保失败
 *               HandlePromotionFailure = true，继续检查老年代最大可用连续空间是否大于历次晋升到老年代的对象的平均大小
 *                  如果大于，则尝试进行一次Minor GC, 但这次Minor GC是有风险的。
 *                  如果小于，直接进行一次Full GC
 *               HandlePromotionFailure = false，直接进行一次Full GC
 *          JDK7之后，这个参数没有影响了，默认就是HandlePromotionFailure = true的规则。
 */
public class JVMHeapArgsDemo {

    public static void main(String[] args) {

    }
}
