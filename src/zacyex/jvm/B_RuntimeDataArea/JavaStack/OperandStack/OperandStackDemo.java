package zacyex.jvm.B_RuntimeDataArea.JavaStack.OperandStack;

/**
 * @author zacyex
 * @date 2021/12/12 20:09
 *
 * 栈帧中的操作数栈
 */
public class OperandStackDemo {


    public void testAddOperation(){
        //byte,short,char,boolean: 都以int型来保存
        byte i = 15;
        int j = 10;
        int k = i + j;

        // 以下为OperandStackDemo.class字节码经过javap反编译得到的
        /*
          public void testAddOperation();
            descriptor: ()V
            flags: (0x0001) ACC_PUBLIC
            Code:
    注:       stack=2, locals=4, args_size=1   --> stack=2为操作数栈的深度,locals=4为局部变量表的槽数
                 0: bipush        15           --> bipush:将15存入操作数栈中
                 2: istore_1                   --> istore_1:将操作数栈中顶部元素取出（15取出）存入局部变量表1槽（注：由于是非静态方法，0槽用于存放this引用）
                 3: bipush        10           --> bipush:将10存入操作数栈中
                 5: istore_2                   --> istore_2:将操作数栈中顶部元素取出（10取出）存入局部变量表2槽
                 6: iload_1                    --> iload_1:从槽1中取出数据(取出15)压入操作数栈中
                 7: iload_2                    --> iload_2:从槽2中取出数据(取出10)压入操作数栈中
                 8: iadd                       --> iadd:从操作数栈中出栈两个数，进行相加并压入操作数栈中
                 9: istore_3                   --> istore_3:将操作数栈栈顶元素取出(取出25)存入局部变量表3槽
                10: return                     --> return:结束
              LineNumberTable:
                line 12: 0
                line 13: 3
                line 14: 6
                line 15: 10
              LocalVariableTable:
                Start  Length  Slot  Name   Signature
                    0      11     0  this   Lzacyex/jvm/runtimeDataArea/JavaStack/OperandStack/OperandStackDemo;
                    3       8     1     i   B
                    6       5     2     j   I
                   10       1     3     k   I
        }
         */
    }


    public int testAddOperationTwo() {
        byte i = 15;
        int j = 10;
        return i + j;

        /*
        public int testAddOperationTwo();
        descriptor: ()I
        flags: (0x0001) ACC_PUBLIC
        Code:
          stack=2, locals=3, args_size=1
             0: bipush        15
             2: istore_1
             3: bipush        10
             5: istore_2
             6: iload_1
             7: iload_2
             8: iadd
    注:       9: ireturn  --> 此处变为了ireturn
          LineNumberTable:
            line 51: 0
            line 52: 3
            line 53: 6
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0      10     0  this   Lzacyex/jvm/runtimeDataArea/JavaStack/OperandStack/OperandStackDemo;
                3       7     1     i   B
                6       4     2     j   I
         */
    }
    public void testAddOperationThree() {
        // 方法有返回值时
        int i = testAddOperationTwo();
        int j = 20;

        /*
        public void testAddOperationThree();
        descriptor: ()V
        flags: (0x0001) ACC_PUBLIC
        Code:
          stack=1, locals=3, args_size=1
    注:       0: aload_0                        --> 从局部变量中装载引用类型值
             1: invokevirtual #2                  // Method testAddOperationTwo:()I  --> 调用引用类型的虚方法得到返回值，并压入操作数栈中
             4: istore_1                        --> 将操作数栈栈顶的元素进行出栈，存入局部变量表槽1
             5: bipush        20
             7: istore_2
             8: return
          LineNumberTable:
            line 57: 0
            line 58: 5
            line 59: 8
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0       9     0  this   Lzacyex/jvm/runtimeDataArea/JavaStack/OperandStack/OperandStackDemo;
                5       4     1     i   I
                8       1     2     j   I
         */
    }


    //TODO 从字节码角度查看i++和++i区别
    public void testIOne(){
        int i = 10;
        i++;

        int j = 15;
        j++;

        /*
        public void testIOne();
        descriptor: ()V
        flags: (0x0001) ACC_PUBLIC
        Code:
          stack=1, locals=3, args_size=1
             0: bipush        10
             2: istore_1
             3: iinc          1, 1
             6: bipush        15
             8: istore_2
             9: iinc          2, 1
            12: return
          LineNumberTable:
            line 112: 0
            line 113: 3
            line 115: 6
            line 116: 9
            line 117: 12
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0      13     0  this   Lzacyex/jvm/runtimeDataArea/JavaStack/OperandStack/OperandStackDemo;
                3      10     1     i   I
                9       4     2     j   I
         */
    }

    public void testITwo(){
        int i = 10;
        int j = 15;
        int m = i++;
        int n = ++j;

        /*
        public void testITwo();
        descriptor: ()V
        flags: (0x0001) ACC_PUBLIC
        Code:
          stack=1, locals=5, args_size=1
    注：      0: bipush        10            --> 将10压入操作数栈中
             2: istore_1                    --> 将操作数栈栈顶元素（10）出栈并存入局部变量表槽1
             3: bipush        15            --> 将15压入操作数栈中
             5: istore_2                    --> 将操作数栈栈顶元素（15）出栈并存入局部变量表槽2
             6: iload_1                     --> 将局部变量表槽1的元素（10）压入操作数栈中
             7: iinc          1, 1          --> 将局部变量表槽1的元素（10）进行+1并重新存入
            10: istore_3                    --> 将操作数栈栈顶元素（10）出栈并存入局部变量表槽3
            11: iinc          2, 1          --> 将局部变量表槽2的元素（15）进行+1并重新存入
            14: iload_2                     --> 将局部变量表槽2的元素（16）压入操作数栈中
            15: istore        4             --> 将操作数栈栈顶元素（16）出栈并存入局部变量表槽4
            17: return
          LineNumberTable:
            line 120: 0
            line 121: 3
            line 122: 6
            line 123: 11
            line 124: 17
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0      18     0  this   Lzacyex/jvm/runtimeDataArea/JavaStack/OperandStack/OperandStackDemo;
                3      15     1     i   I
                6      12     2     j   I
               11       7     3     m   I
               17       1     4     n   I
         */
    }
}
