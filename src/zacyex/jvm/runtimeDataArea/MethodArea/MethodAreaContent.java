package zacyex.jvm.runtimeDataArea.MethodArea;

/**
 * @author zacyex
 * @date 2021/12/29 20:32
 *
 * 方法区存放的主要信息为：JIT编译后的热点代码，方法信息，类型信息，域信息，运行时常量池，静态变量，字符串常量池
 *
 * JDK6中方法区的实现为永久代，存放的即为方法区所规范的信息（内存是JVM虚拟机内存中划分的一部分）
 * JDK7中方法区的实现为永久代，并将静态变量和字符串常量池的存放放入了堆中（内存是JVM虚拟机内存中划分的一部分）
 * （字符串常量也是对象，放在方法区中不容易进行垃圾回收）
 * JDK8中方法区的实现为元空间，存储内容和JDK7的永久代一致，但内存改为了使用直接内存
 * （减少了方法区的GC以及因为方法区出现的内存溢出的情况，且不用再为方法区进行调优，和JRocket，J9的方法区实现方式一致了）
 *
 */
public class MethodAreaContent {

    private int varOne;

    private String varTwo;

    private static int varThree;

    private static MethodAreaContent varFour;

    private static final int varFive = 10;

    private static final MethodAreaContent varSix = new MethodAreaContent();

    public static void main(String[] args) {
        System.out.println("do nothing");
    }

    private void methodA(){
        System.out.println("do nothing");
    }

    private static void methodB(){
        System.out.println("do nothing");
    }

    // 编译后的字节码文件，在加载后对应内容的存放解读
    /*
          ---> 1.类型信息（包括类的全路径，版本信息，父类以及实现的接口信息，访问修饰符等）经过类加载子系统加载后存放在方法区
          Classfile /E:/zacyex_projects/JVMLearn/out/production/JVMLearn/zacyex/jvm/runtimeDataArea/MethodArea/MethodAreaContent.class
          Last modified 20211229; size 1004 bytes
          MD5 checksum 8bce760949a6e05807e93fcc7a299469
          Compiled from "MethodAreaContent.java"
        public class zacyex.jvm.runtimeDataArea.MethodArea.MethodAreaContent
          minor version: 0
          major version: 52
          flags: (0x0021) ACC_PUBLIC, ACC_SUPER
          this_class: #5                          // zacyex/jvm/runtimeDataArea/MethodArea/MethodAreaContent
          super_class: #8                         // java/lang/Object
          interfaces: 0, fields: 6, methods: 5, attributes: 1
          ---> 2.常量池(此处编译后class文件中的是静态常量池，对应的是符号引用)经过类加载子系统加载后，变为运行时常量池（由链接的解析阶段，将符号引用转化为直接引用，涉及到其他符号对应的类的加载），存放在方法区中
        Constant pool:
           #1 = Methodref          #8.#35         // java/lang/Object."<init>":()V
           #2 = Fieldref           #36.#37        // java/lang/System.out:Ljava/io/PrintStream;
           #3 = String             #38            // do nothing
           #4 = Methodref          #39.#40        // java/io/PrintStream.println:(Ljava/lang/String;)V
           #5 = Class              #41            // zacyex/jvm/runtimeDataArea/MethodArea/MethodAreaContent
           #6 = Methodref          #5.#35         // zacyex/jvm/runtimeDataArea/MethodArea/MethodAreaContent."<init>":()V
           #7 = Fieldref           #5.#42         // zacyex/jvm/runtimeDataArea/MethodArea/MethodAreaContent.varSix:Lzacyex/jvm/runtimeDataArea/Meth
        odArea/MethodAreaContent;
           #8 = Class              #43            // java/lang/Object
           #9 = Utf8               varOne
          #10 = Utf8               I
          #11 = Utf8               varTwo
          #12 = Utf8               Ljava/lang/String;
          #13 = Utf8               varThree
          #14 = Utf8               varFour
          #15 = Utf8               Lzacyex/jvm/runtimeDataArea/MethodArea/MethodAreaContent;
          #16 = Utf8               varFive
          #17 = Utf8               ConstantValue
          #18 = Integer            10
          #19 = Utf8               varSix
          #20 = Utf8               <init>
          #21 = Utf8               ()V
          #22 = Utf8               Code
          #23 = Utf8               LineNumberTable
          #24 = Utf8               LocalVariableTable
          #25 = Utf8               this
          #26 = Utf8               main
          #27 = Utf8               ([Ljava/lang/String;)V
          #28 = Utf8               args
          #29 = Utf8               [Ljava/lang/String;
          #30 = Utf8               methodA
          #31 = Utf8               methodB
          #32 = Utf8               <clinit>
          #33 = Utf8               SourceFile
          #34 = Utf8               MethodAreaContent.java
          #35 = NameAndType        #20:#21        // "<init>":()V
          #36 = Class              #44            // java/lang/System
          #37 = NameAndType        #45:#46        // out:Ljava/io/PrintStream;
          ---> 字符串对应的字面量存放在字符串常量池中，JDK6时字符串常量池又存放在运行时常量池中，及方法区中,JDK7之后字符串常量池存放在了堆区（个人理解）
          #38 = Utf8               do nothing
          #39 = Class              #47            // java/io/PrintStream
          #40 = NameAndType        #48:#49        // println:(Ljava/lang/String;)V
          #41 = Utf8               zacyex/jvm/runtimeDataArea/MethodArea/MethodAreaContent
          #42 = NameAndType        #19:#15        // varSix:Lzacyex/jvm/runtimeDataArea/MethodArea/MethodAreaContent;
          #43 = Utf8               java/lang/Object
          #44 = Utf8               java/lang/System
          #45 = Utf8               out
          #46 = Utf8               Ljava/io/PrintStream;
          #47 = Utf8               java/io/PrintStream
          #48 = Utf8               println
          #49 = Utf8               (Ljava/lang/String;)V
        {
          ---> 域信息（及成员变量的信息，包括访问修饰符，类型等）加载后存放在方法区中
          private int varOne;
            descriptor: I
            flags: (0x0002) ACC_PRIVATE

          private java.lang.String varTwo;
            descriptor: Ljava/lang/String;
            flags: (0x0002) ACC_PRIVATE

          ---> 静态变量(静态变量是加载后就会赋值的，未显示赋值就是初始值)加载后存放在方法区中(jdk7后从方法区的实现（永久代）中移入了堆中)
                注意：静态变量存放在方法区是说的基本类型的值，以及引用类型的引用，若静态变量是引用类型，其对应的实例化对象至始至终都是存放在堆空间的
          private static int varThree;
            descriptor: I
            flags: (0x000a) ACC_PRIVATE, ACC_STATIC

          private static zacyex.jvm.runtimeDataArea.MethodArea.MethodAreaContent varFour;
            descriptor: Lzacyex/jvm/runtimeDataArea/MethodArea/MethodAreaContent;
            flags: (0x000a) ACC_PRIVATE, ACC_STATIC

          private static final int varFive;
            descriptor: I
            flags: (0x001a) ACC_PRIVATE, ACC_STATIC, ACC_FINAL
            ConstantValue: int 10

          private static final zacyex.jvm.runtimeDataArea.MethodArea.MethodAreaContent varSix;
            descriptor: Lzacyex/jvm/runtimeDataArea/MethodArea/MethodAreaContent;
            flags: (0x001a) ACC_PRIVATE, ACC_STATIC, ACC_FINAL

          ---> 方法信息（即方法的描述，参数返回值，字节码指令等）经加载后存放在方法区
          public zacyex.jvm.runtimeDataArea.MethodArea.MethodAreaContent();
            descriptor: ()V
            flags: (0x0001) ACC_PUBLIC
            Code:
              stack=1, locals=1, args_size=1
                 0: aload_0
                 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
                 4: return
              LineNumberTable:
                line 7: 0
              LocalVariableTable:
                Start  Length  Slot  Name   Signature
                    0       5     0  this   Lzacyex/jvm/runtimeDataArea/MethodArea/MethodAreaContent;

          public static void main(java.lang.String[]);
            descriptor: ([Ljava/lang/String;)V
            flags: (0x0009) ACC_PUBLIC, ACC_STATIC
            Code:
              stack=2, locals=1, args_size=1
                 0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
                 3: ldc           #3                  // String do nothing
                 5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
                 8: return
              LineNumberTable:
                line 22: 0
                line 23: 8
              LocalVariableTable:
                Start  Length  Slot  Name   Signature
                    0       9     0  args   [Ljava/lang/String;

          private void methodA();
            descriptor: ()V
            flags: (0x0002) ACC_PRIVATE
            Code:
              stack=2, locals=1, args_size=1
                 0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
                 3: ldc           #3                  // String do nothing
                 5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
                 8: return
              LineNumberTable:
                line 26: 0
                line 27: 8
              LocalVariableTable:
                Start  Length  Slot  Name   Signature
                    0       9     0  this   Lzacyex/jvm/runtimeDataArea/MethodArea/MethodAreaContent;

          private static void methodB();
            descriptor: ()V
            flags: (0x000a) ACC_PRIVATE, ACC_STATIC
            Code:
              stack=2, locals=0, args_size=0
                 0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
                 3: ldc           #3                  // String do nothing
                 5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
                 8: return
              LineNumberTable:
                line 30: 0
                line 31: 8

          static {};
            descriptor: ()V
            flags: (0x0008) ACC_STATIC
            Code:
              stack=2, locals=0, args_size=0
                 0: new           #5                  // class zacyex/jvm/runtimeDataArea/MethodArea/MethodAreaContent
                 3: dup
                 4: invokespecial #6                  // Method "<init>":()V
                 7: putstatic     #7                  // Field varSix:Lzacyex/jvm/runtimeDataArea/MethodArea/MethodAreaContent;
                10: return
              LineNumberTable:
                line 19: 0
        }
        SourceFile: "MethodAreaContent.java"
     */

}
