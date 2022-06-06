package zacyex.jvm.G_GarbageCollection;

/**
 * @author zacyex
 * @date 2022/2/7 15:48
 *
 * 参数: -XX:+PrintGCDetails
 * 查看下面每个方法执行完后，对应的GC打印详细信息，分析对象被回收的过程
 */
public class LocalVarGCDemo {

    public void localVarOne(){
        byte[] buffer = new byte[10 * 1024 * 1024];
        System.gc();

        /*
        [GC (System.gc()) [PSYoungGen: 13518K->840K(37888K)] 13518K->11088K(123904K), 0.0861842 secs]
            [Times: user=0.02 sys=0.00, real=0.09 secs]
        [Full GC (System.gc()) [PSYoungGen: 840K->0K(37888K)] [ParOldGen: 10248K->10982K(86016K)] 11088K->10982K(123904K),
            [Metaspace: 3443K->3443K(1056768K)], 0.0071139 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
        */
        // 发生Minor GC时，新生代回收前占用13518K回收后占用840K
        // 发生Full GC时，老年代回收前占用10248K回收后占用10248K
        // 证明 buffer所指向的对象未被回收（年轻代进入老年代是因为对象过大达到survivor区的一半以上，
        // 符合“survivor区中相同年龄对象超过一半以上，则晋升的规则”）
    }

    public void localVarTwo() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        buffer = null;
        System.gc();

        /*
        [GC (System.gc()) [PSYoungGen: 13518K->824K(37888K)] 13518K->832K(123904K), 0.0012284 secs]
        [Times: user=0.00 sys=0.00, real=0.00 secs]
        [Full GC (System.gc()) [PSYoungGen: 824K->0K(37888K)] [ParOldGen: 8K->743K(86016K)] 832K->743K(123904K),
        [Metaspace: 3445K->3445K(1056768K)], 0.0055713 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         */

        // 发生Minor GC时，新生代回收前占用13518K回收后占用824K
        // 发生Full GC时，老年代回收前占用8K回收后占用743K
        // 证明 buffer所指向的对象被回收
    }

    public void localVarThree() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        System.gc();
        /*
        [GC (System.gc()) [PSYoungGen: 13518K->872K(37888K)] 13518K->11120K(123904K), 0.0178565 secs]
        [Times: user=0.00 sys=0.02, real=0.02 secs]
        [Full GC (System.gc()) [PSYoungGen: 872K->0K(37888K)] [ParOldGen: 10248K->10983K(86016K)] 11120K->10983K(123904K),
        [Metaspace: 3446K->3446K(1056768K)], 0.0099343 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
         */

        // 发生Minor GC时，新生代回收前占用13518K回收后占用872K
        // 发生Full GC时，老年代回收前占用10248K回收后占用10983K
        // 证明 buffer所指向的对象未被回收（虽然buffer作用域已经结束，
        // 但栈帧中局部变量表slot槽对应的引用还未被覆盖，还处于GCRoots的引用链上，所以不会被回收）
    }

    public void localVarFour() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        int value = 10;
        System.gc();

        /*
        [GC (System.gc()) [PSYoungGen: 13518K->792K(37888K)] 13518K->800K(123904K), 0.0019049 secs]
        [Times: user=0.00 sys=0.00, real=0.00 secs]
        [Full GC (System.gc()) [PSYoungGen: 792K->0K(37888K)] [ParOldGen: 8K->743K(86016K)] 800K->743K(123904K),
        [Metaspace: 3445K->3445K(1056768K)], 0.0077463 secs] [Times: user=0.02 sys=0.02, real=0.01 secs]
         */
        // 发生Minor GC时，新生代回收前占用13518K回收后占用792K
        // 发生Full GC时，老年代回收前占用8K回收后占用743K
        // 证明 buffer所指向的对象被回收（buffer作用域已经结束，后续value复用之前buffer对应局部变量表的slot槽位置
        // 此时buffer引用不在了，则buffer指向的对象会被回收）
    }

    public void lcoalVarFive() {
        localVarOne();
        System.gc();

        /*
        localVarOne方法中调用GC的打印信息：
        [GC (System.gc()) [PSYoungGen: 13518K->872K(37888K)] 13518K->11120K(123904K), 0.0092800 secs]
        [Times: user=0.02 sys=0.00, real=0.01 secs]
        [Full GC (System.gc()) [PSYoungGen: 872K->0K(37888K)] [ParOldGen: 10248K->10983K(86016K)] 11120K->10983K(123904K),
        [Metaspace: 3445K->3445K(1056768K)], 0.0097114 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]

        localVarFive方法中调用GC的打印信息：
        [GC (System.gc()) [PSYoungGen: 0K->0K(37888K)] 10983K->10983K(123904K), 0.0005956 secs]
        [Times: user=0.00 sys=0.00, real=0.00 secs]
        [Full GC (System.gc()) [PSYoungGen: 0K->0K(37888K)] [ParOldGen: 10983K->741K(86016K)] 10983K->741K(123904K),
        [Metaspace: 3446K->3446K(1056768K)], 0.0080407 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
         */

        // localVarFive中发生Minor GC时，新生代回收前占用0K回收后占用0K
        // localVarFive中发生Full GC时，老年代回收前占用10983K回收后占用741K
        // 证明 buffer所指向的对象被回收（localVarOne();执行完后，对应栈帧出栈，buffer引用不存在了，
        // 则在localVarFive()方法后续执行GC中，对应的byte[]对象不处于GCRoots的引用链上，于是被回收了）
    }

    public static void main(String[] args) {
        LocalVarGCDemo localVarGCDemo = new LocalVarGCDemo();
        //localVarGCDemo.localVarOne();
        //localVarGCDemo.localVarTwo();
        //localVarGCDemo.localVarThree();
        //localVarGCDemo.localVarFour();
        localVarGCDemo.lcoalVarFive();
    }
}
