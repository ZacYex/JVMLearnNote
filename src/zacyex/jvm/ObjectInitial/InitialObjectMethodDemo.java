package zacyex.jvm.ObjectInitial;

/**
 * @author zacyex
 * @date 2022/1/3 21:15
 *
 * 创建对象的方式：
 *    1.new;
 *    2.通过反射进行实例化
 *       2.1 Class的newInstance():只能调用空参的构造器，权限必须是public（不建议采用此方式）
 *       2.2 Constructor的newInstance(xxx):可以调用空参，带参的构造器，权限没有要求
 *    3.使用clone():不调用任何构造器，当前类需要实现Cloneable接口，实现clone()方法
 *    4.使用反序列化:从文件中，从网络中获取一个二进制流
 *    5.第三方库Objenesis
 * 创建对象的步骤：
 *    1.判断对象对应的类是否加载，链接，初始化
 *    2.为对象分配内存
 *      如果内存规整 ----> 指针碰撞法为对象分配内存
 *          注： (垃圾回收器采用标记整理，对应内存就是规整的)
 *      内存不规整 ----> 空闲列表分配(即从空闲列表中找出足够大的空间进行分配，并更新列表的内容)
 *          注： (垃圾回收器采用标记清除，对应内存就是不规整的)
 *    3.处理并发安全问题（由于堆空间是线程共享的，可能会有多个线程在实例化分配内存时，抢占同一块儿区域）
 *      采用CAS失败重试，区域加锁保证更新的原子性
 *      为每个线程预先分配一块儿TLAB -- 通过-XX:+UseTLAB参数进行设定
 *    4.初始化分配到的空间 ----> 为属性设置默认值，保证对象实例字段在不赋值时可以直接使用
 *    5.设置对象的对象头 ----> 记录类信息，hashcode值，锁信息等存储在对象的对象头中。这个过程具体实现方式取决于jvm
 *    6.执行init方法进行初始化 ----> 执行代码块和所调用的构造器，若有属性赋值的操作，则对属性进行显式初始化
 */
public class InitialObjectMethodDemo {

//    public int a;

    public static void main(String[] args) {
        Object obj = new Object();
//        InitialObjectMethodDemo initialObjectMethodDemo = new InitialObjectMethodDemo();
//        System.out.println(initialObjectMethodDemo.a);
    }

    /*
    public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=1
         0: new           #2                  // class java/lang/Object
         3: dup
         4: invokespecial #1                  // Method java/lang/Object."<init>":()V
         7: astore_1
         8: return
      LineNumberTable:
        line 21: 0
        line 22: 8
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       9     0  args   [Ljava/lang/String;
            8       1     1   obj   Ljava/lang/Object;
     */
}
