package zacyex.jvm.runtimeDataArea.JavaStack.LocalVariables;

/**
 * @author zacyex
 * @date 2021/12/4 19:25
 *
 * 查看字节码文件，了解 局部变量表(运行时,方法对应的栈帧中主要存储结构之一)
 * 1.局部变量表的大小在编译阶段就已经确定下来了，真正某个线程运行时，对应栈中栈帧存储时，大小不会改变
 * 2.静态方法相比非静态方法，非静态的第一个槽总是this对象的引用
 * 3.32位基本变量和应用类型都是占用一个槽，64位的基本变量占用两个槽
 * 4.slot槽可以重复利用，当前面的变量作用域消失了，后续有其他变量会继续使用这个槽位
 * 注:使用jclasslib查看对应的方法，方法声明信息，编译后的指令对应的java代码行数，
 *   局部变量长度，局部变量的作用域(指从第几行字节码开始，作用于字节码的长度是多少)等信息
 */
public class LocalVariablesTest {

    public static void main(String[] args) {
        LocalVariablesTest localVariablesTest = new LocalVariablesTest();
        int num = 10;
        localVariablesTest.testOne(num);

        // 以下为class文件反编译后的字节码,LocalVariables为main方法中对应的局部变量表
//        public static void main(java.lang.String[]);
//        descriptor: ([Ljava/lang/String;)V
//        flags: (0x0009) ACC_PUBLIC, ACC_STATIC
//        Code:
//        stack=2, locals=3, args_size=1        -->locals = 3 代表局部变量表的槽数是3
//        0: new           #2                  // class zacyex/jvm/runtimeDataArea/JavaStack/LocalVariables/LocalVariable
//        sTest
//        3: dup
//        4: invokespecial #3                  // Method "<init>":()V
//        7: astore_1
//        8: bipush        10
//        10: istore_2
//        11: aload_1
//        12: invokespecial #4                  // Method testOne:()V
//        15: return
//                LineNumberTable:
//        line 12: 0
//        line 13: 8
//        line 14: 11
//        line 18: 15
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0      16     0  args   [Ljava/lang/String;   -->对应传入参数String[] args的引用存储，Ljava/..中的L代表引用类型
//        8       8     1 localVariablesTest   Lzacyex/jvm/runtimeDataArea/JavaStack/LocalVariables/Lo            8       8     1 localVariablesTest   Lzacyex/jvm/runtimeDataArea/JavaStack/LocalVariables/LocalVariablesTest;
//        11       5     2   num   I                    --> 对应局部变量num的存储,I 代表为基本类型中的int

    }

    // 查看非静态方法和静态方法中局部变量表的差异
    public void testOne(int a) {
        LocalVariablesTest localVariablesTest = new LocalVariablesTest();
        Integer integer = new Integer(10);
        int num = a;
        System.out.println(num+integer);

//        public void testOne(int);
//        descriptor: (I)V
//        flags: (0x0001) ACC_PUBLIC
//        Code:
//        stack=3, locals=5, args_size=2
//        0: new           #2                  // class zacyex/jvm/runtimeDataArea/JavaStack/LocalVariables/LocalVariablesTest
//        3: dup
//        4: invokespecial #3                  // Method "<init>":()V
//        7: astore_2
//        8: new           #5                  // class java/lang/Integer
//        11: dup
//        12: bipush        10
//        14: invokespecial #6                  // Method java/lang/Integer."<init>":(I)V
//        17: astore_3
//        18: iload_1
//        19: istore        4
//        21: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
//        24: iload         4
//        26: aload_3
//        27: invokevirtual #8                  // Method java/lang/Integer.intValue:()I
//        30: iadd
//        31: invokevirtual #9                  // Method java/io/PrintStream.println:(I)V
//        34: return
//                LineNumberTable:
//        line 47: 0
//        line 48: 8
//        line 49: 18
//        line 50: 21
//        line 54: 34
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0      35     0  this   Lzacyex/jvm/runtimeDataArea/JavaStack/LocalVariables/LocalVariablesTest;  -->非静态方法包括构造方法，索引为0的槽都是当前对象的引用
//        0      35     1     a   I
//        8      27     2 localVariablesTest   Lzacyex/jvm/runtimeDataArea/JavaStack/LocalVariables/LocalVariablesTest;
//        18      17     3 integer   Ljava/lang/Integer;
//        21      14     4   num   I
    }

    // 查看64位的和32位变量对应槽的区别
    public void testTwo(double a){
        double dnum = a;
        long lnum = 100;
        int inum = 10;

//        public void testTwo(double);
//        descriptor: (D)V
//        flags: (0x0001) ACC_PUBLIC
//        Code:
//        stack=2, locals=8, args_size=2
//        0: dload_1
//        1: dstore_3
//        2: ldc2_w        #10                 // long 100l
//        5: lstore        5
//        7: bipush        10
//        9: istore        7
//        11: return
//                LineNumberTable:
//        line 98: 0
//        line 99: 2
//        line 100: 7
//        line 101: 11
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0      12     0  this   Lzacyex/jvm/runtimeDataArea/JavaStack/LocalVariables/LocalVariablesTest; -->下一个槽下标是1，引用类型占一个槽位
//        0      12     1     a   D  --> 当前槽位下标是1，下一个槽下标是3，double类型占用两个槽位
//        2      10     3  dnum   D
//        7       5     5  lnum   J  --> 当前槽位下标是5，下一个槽下标是7，long类型占用两个槽位
//        11       1     7  inum   I

    }

    // 查看slot槽重复利用的案例
    public void testThree(){
        int a = 10;
        {
            int b = 0;
            b = a + 1;
        }
        int c = 20;

//        public void testThree();
//        descriptor: ()V
//        flags: (0x0001) ACC_PUBLIC
//        Code:
//        stack=2, locals=3, args_size=1
//        0: bipush        10
//        2: istore_1
//        3: iconst_0
//        4: istore_2
//        5: iload_1
//        6: iconst_1
//        7: iadd
//        8: istore_2
//        9: bipush        20
//        11: istore_2
//        12: return
//                LineNumberTable:
//        line 131: 0
//        line 133: 3
//        line 134: 5
//        line 136: 9
//        line 139: 12
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        5       4     2     b   I  --> 变量b所占槽位下标是2,作用范围是从偏移量为5的指令开始,作用长度为4个指令，未到达指令12
//        0      13     0  this   Lzacyex/jvm/runtimeDataArea/JavaStack/LocalVariables/LocalVariablesTest;
//        3      10     1     a   I
//        12       1     2     c   I  --> 变量c所占槽位下标也是2,作用范围是从偏移量为12的指令开始,作用长度为1个指令（证明之前有变量作用域结束了，对应使用的槽会被后续的变量使用）

    }
}
