package zacyex.jvm.B_RuntimeDataArea.PCRegister;

/**
 * @author zacyex
 * @date 2021/12/1 20:28
 * 查看本类的反编译后的字节码文件
 * 问题：使用PC寄存器存储字节码指令有什么用呢？
 *      为什么使用PC寄存器记录当前线程的执行地址呢？
 *
 *      因为CPU需要不停的切换各个线程，切换回来时，需要知道从哪儿继续开始执行
 */
public class PCRegisterDemo {

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int c = a + b;

        String str = "zacyex";
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

//    public class zacyex.jvm.runtimeDataArea.PCRegister.PCRegisterDemo
//    minor version: 0
//    major version: 52
//    flags: (0x0021) ACC_PUBLIC, ACC_SUPER
//    this_class: #5                          // zacyex/jvm/runtimeDataArea/PCRegisterDemo
//    super_class: #6                         // java/lang/Object
//    interfaces: 0, fields: 0, methods: 2, attributes: 1
//    Constant pool:
//            #1 = Methodref          #6.#26         // java/lang/Object."<init>":()V
//            #2 = String             #27            // zacyex
//            #3 = Fieldref           #28.#29        // java/lang/System.out:Ljava/io/PrintStream;
//            #4 = Methodref          #30.#31        // java/io/PrintStream.println:(I)V
//            #5 = Class              #32            // zacyex/jvm/runtimeDataArea/PCRegisterDemo
//            #6 = Class              #33            // java/lang/Object
//            #7 = Utf8               <init>
//            #8 = Utf8               ()V
//            #9 = Utf8               Code
//            #10 = Utf8               LineNumberTable
//            #11 = Utf8               LocalVariableTable
//            #12 = Utf8               this
//            #13 = Utf8               Lzacyex/jvm/runtimeDataArea/PCRegisterDemo;
//            #14 = Utf8               main
//            #15 = Utf8               ([Ljava/lang/String;)V
//            #16 = Utf8               args
//            #17 = Utf8               [Ljava/lang/String;
//            #18 = Utf8               a
//            #19 = Utf8               I
//            #20 = Utf8               b
//            #21 = Utf8               c
//            #22 = Utf8               str
//            #23 = Utf8               Ljava/lang/String;
//            #24 = Utf8               SourceFile
//            #25 = Utf8               PCRegisterDemo.java
//            #26 = NameAndType        #7:#8          // "<init>":()V
//            #27 = Utf8               zacyex
//            #28 = Class              #34            // java/lang/System
//            #29 = NameAndType        #35:#36        // out:Ljava/io/PrintStream;
//            #30 = Class              #37            // java/io/PrintStream
//            #31 = NameAndType        #38:#39        // println:(I)V
//            #32 = Utf8               zacyex/jvm/runtimeDataArea/PCRegisterDemo
//            #33 = Utf8               java/lang/Object
//            #34 = Utf8               java/lang/System
//            #35 = Utf8               out
//            #36 = Utf8               Ljava/io/PrintStream;
//            #37 = Utf8               java/io/PrintStream
//            #38 = Utf8               println
//            #39 = Utf8               (I)V
//    {
//  public zacyex.jvm.runtimeDataArea.PCRegister.PCRegisterDemo();
//        descriptor: ()V
//        flags: (0x0001) ACC_PUBLIC
//        Code:
//        stack=1, locals=1, args_size=1
//        0: aload_0
//        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
//        4: return
//            LineNumberTable:
//        line 8: 0
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0       5     0  this   Lzacyex/jvm/runtimeDataArea/PCRegisterDemo;
//
//        public static void main(java.lang.String[]);
//        descriptor: ([Ljava/lang/String;)V
//        flags: (0x0009) ACC_PUBLIC, ACC_STATIC
//        Code:
//        stack=2, locals=5, args_size=1
//        0: bipush        10    -->  "0:"指令地址偏移量 (PC寄存器存储下一步执行指令的地址), "bipush"指令
//        2: istore_1
//        3: bipush        20
//        5: istore_2
//        6: iload_1
//        7: iload_2
//        8: iadd
//        9: istore_3
//        10: ldc           #2                  // String zacyex
//        12: astore        4
//        14: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
//        17: iload_1
//        18: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
//        21: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
//        24: iload_2
//        25: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
//        28: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
//        31: iload_3
//        32: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
//        35: return
//            LineNumberTable:
//        line 11: 0
//        line 12: 3
//        line 13: 6
//        line 15: 10
//        line 16: 14
//        line 17: 21
//        line 18: 28
//        line 19: 35
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0      36     0  args   [Ljava/lang/String;
//        3      33     1     a   I
//        6      30     2     b   I
//        10      26     3     c   I
//        14      22     4   str   Ljava/lang/String;
//    }

}
