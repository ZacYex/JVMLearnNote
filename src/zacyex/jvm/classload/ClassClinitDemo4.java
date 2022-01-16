package zacyex.jvm.classload;

/**
 * @author zacyex
 * @date 2021/11/24 21:32
 * 类的加载过程只会有一次，多个线程在加载同一个类时会被同步加锁
 */
public class ClassClinitDemo4 {

    public static void main(String[] args) {
        Runnable run = () -> {
            System.out.println(Thread.currentThread().getName()+" start...");
            ClassClinitSynchronized clinitSynchronized = new ClassClinitSynchronized();
            System.out.println(Thread.currentThread().getName()+" end...");
        };

        Thread threadOne = new Thread(run, "threadOne");
        threadOne.start();
        Thread threadTwo = new Thread(run, "threadOne");
        threadTwo.start();
    }
}

class ClassClinitSynchronized {
    static {
        if(true){ // 防止编译时发现类初始化不了而报错
            System.out.println("ClassClinitSynchronized 初始化中....");
            while (true){
                // 死循环，类初始化过程永远不会完成
            }
        }
    }
}
