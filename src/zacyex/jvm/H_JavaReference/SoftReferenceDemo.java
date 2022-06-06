package zacyex.jvm.H_JavaReference;

import java.lang.ref.SoftReference;

/**
 * @author zacyex
 * @date 2022/2/8 20:43
 * 虚拟机参数：-Xms10m -Xmx10m
 *
 * 软引用：声明类型为 SoftReference<T> softReference = new SoftReference<T>(new Object());
 * 内存不足进行垃圾回收时，GCRoots应用链上软引用所引用的对象会进行回收
 * 内存足够时，强行调用垃圾回收，也不会回收软引用所引用的对象
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {
        // 创建一个软引用所引用的对象
        SoftReference<SoftReferenceDemo> softReference = new SoftReference<>(new SoftReferenceDemo());
        // 等价于下面三行代码
//        SoftReferenceDemo strongRef = new SoftReferenceDemo();
//        SoftReference<SoftReferenceDemo> softReference = new SoftReference<>(strongRef);
//        strongRef = null;

        // 从软引用获取对象
        System.out.println(softReference.get());

        System.gc();

        // 垃圾回收后再获取软引用对象
        System.out.println("After GC:");
        System.out.println(softReference.get());

        // 让内存变得不足时进行垃圾回收
        try{
            byte[] b = new byte[1024 * 1024 * 7];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            // 垃圾回收后再获取软引用对象
            System.out.println(softReference.get());
        }
    }
}
