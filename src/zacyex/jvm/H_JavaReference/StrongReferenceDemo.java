package zacyex.jvm.H_JavaReference;

/**
 * @author zacyex
 * @date 2022/2/8 20:36
 *
 * 强引用：声明类型类似于 Object obj = new Object();
 * 在GCRoots引用链上强引用所引用的对象是不会被垃圾回收掉的
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        byte[] buffer = new byte[100 * 1024 * 1024];
        // buffer = null;

        System.gc();
    }
}
