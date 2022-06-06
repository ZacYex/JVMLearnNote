package zacyex.jvm.H_JavaReference;

import java.lang.ref.WeakReference;

/**
 * @author zacyex
 * @date 2022/2/8 21:01
 *
 * 弱引用：声明类型为 WeakReference<T> weakReference = new WeakReference<T>(new Object());
 * 只要进行垃圾回收，GCRoots应用链上弱引用所引用的对象就会被回收
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        // 创建一个弱引用所引用的对象
        WeakReference<WeakReferenceDemo> weakReference = new WeakReference<>(new WeakReferenceDemo());
        // 等价于下面三行代码
//        WeakReferenceDemo strongRef = new WeakReferenceDemo();
//        WeakReference<WeakReferenceDemo> weakReference = new WeakReference<>(strongRef);
//        strongRef = null;

        // 从弱引用获取对象
        System.out.println(weakReference.get());

        System.gc();

        // 垃圾回收后再获取弱引用对象
        System.out.println("After GC:");
        System.out.println(weakReference.get());
    }
}
