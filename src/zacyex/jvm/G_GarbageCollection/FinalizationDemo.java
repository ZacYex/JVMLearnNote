package zacyex.jvm.G_GarbageCollection;

/**
 * @author zacyex
 * @date 2022/1/16 18:52
 *
 * 对象关于垃圾回收的三种状态：
 * 1.可触及的：可以通过根搜索算法触及到的对象（处于GC Roots的引用链上）
 *      GC Roots: 包含Java虚拟机栈上的引用，本地方法栈上的引用，类的静态变量的引用，运行时常量池上的引用
 * 2.可复活的：对象不在引用链上，进行垃圾回收前，会将垃圾回收的对象放入F-Queue中，并由Finalizer线程执行finalize方法
 *      在重写的finalize方法中重新被引用到引用链上了（注：finalize只会被调用一次，调用后再被回收时不会再次调用，直接回收）
 * 3.不可触及的：对象处于不可触及，且重写finalize方法未复活，或未重写。
 *
 * 通过finalization机制，让对象在被回收前重新复活.
 *
 *
 */
public class FinalizationDemo {

    public static FinalizationDemo obj;

    public static void main(String[] args) throws InterruptedException {

        FinalizationDemo finalizationDemo = new FinalizationDemo();
        finalizationDemo = null;
        System.out.println(FinalizationDemo.obj);
        // 第一次垃圾回收拯救自己
        System.gc();
        Thread.sleep(1000);
        System.out.println(FinalizationDemo.obj);

        // 第二次垃圾回收
        FinalizationDemo.obj = null;
        Thread.sleep(1000);
        System.out.println(FinalizationDemo.obj);

    }

    @Override
    protected void finalize(){
        System.out.println("Finalizer线程调用当前对象的finalize方法！");
        FinalizationDemo.obj = this;
    }
}
