package zacyex.jvm.H_JavaReference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author zacyex
 * @date 2022/2/8 21:10
 *
 * 虚引用：声明类型为 PhantomReference<T> phantomReference = new PhantomReference<T>(new Object(), new ReferenceQueue());
 * 虚引用不会影响对象是否被垃圾回收，也无法通过虚引用获取对应的对象(通过phantomReference.get()获取始终为空)
 * 只是回收时相应对象时进行告知的作用
 */
public class PhantomReferenceDemo {

    public static PhantomReferenceDemo obj;
    static ReferenceQueue<PhantomReferenceDemo> phantomQueue;

    public static void main(String[] args) {
        // 创建分线程，观察phantomQueue队列中的内容
        Thread t = new Thread(() -> {
            // 不断获取phantomQueue中的虚引用对象
            while(true) {
                if (phantomQueue != null) {
                    PhantomReference<PhantomReferenceDemo> objd = null;
                    try {
                        objd = (PhantomReference<PhantomReferenceDemo>) phantomQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (objd != null) {
                        System.out.println("虚引用对应的PhantomReferenceDemo实例被GC了！");
                    }
                }
            }
        });
        t.setDaemon(true); // 设置守护线程
        t.start();



        // 引用队列
        phantomQueue = new ReferenceQueue();
        PhantomReference<PhantomReferenceDemo> phantomReference = new PhantomReference<>(new PhantomReferenceDemo(), phantomQueue);

        // 获取弱引用对应的对象为null
        System.out.println(phantomReference.get());

        try {
            // 第一次GC，由于对象可复活，无法回收对象
            System.out.println("第一次GC");
            System.gc();
            Thread.sleep(1000);
            if(obj == null){
                System.out.println("obj 是 null");
            }else{
                System.out.println("obj 可用");
            }
            // 第二次GC
            System.out.println("第二次GC");
            obj = null;
            System.gc();
            Thread.sleep(1000);
            if(obj == null){
                System.out.println("obj 是 null");
            }else{
                System.out.println("obj 可用");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用finalize方法！");
        obj = this;
    }
}
