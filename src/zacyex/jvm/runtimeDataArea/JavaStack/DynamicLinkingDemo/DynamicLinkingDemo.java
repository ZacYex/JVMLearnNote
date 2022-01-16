package zacyex.jvm.runtimeDataArea.JavaStack.DynamicLinkingDemo;

/**
 * @author zacyex
 * @date 2021/12/13 20:44
 */
public class DynamicLinkingDemo {

    int num = 10;

    // 以下为字节码反编译后的常量池部分
    /*
    Constant pool:
       #1 = Methodref          #9.#23         // java/lang/Object."<init>":()V
       #2 = Fieldref           #8.#24         // zacyex/jvm/runtimeDataArea/JavaStack/DynamicLinkingDemo/DynamicLinkingDemo.num:I
       #3 = Fieldref           #25.#26        // java/lang/System.out:Ljava/io/PrintStream;
       #4 = String             #27            // methodA()....
       #5 = Methodref          #28.#29        // java/io/PrintStream.println:(Ljava/lang/String;)V
       #6 = String             #30            // methodB()....
       #7 = Methodref          #8.#31         // zacyex/jvm/runtimeDataArea/JavaStack/DynamicLinkingDemo/DynamicLinkingDemo.methodA:()V
       #8 = Class              #32            // zacyex/jvm/runtimeDataArea/JavaStack/DynamicLinkingDemo/DynamicLinkingDemo
       #9 = Class              #33            // java/lang/Object
      #10 = Utf8               num
      #11 = Utf8               I
      #12 = Utf8               <init>
      #13 = Utf8               ()V
      #14 = Utf8               Code
      #15 = Utf8               LineNumberTable
      #16 = Utf8               LocalVariableTable
      #17 = Utf8               this
      #18 = Utf8               Lzacyex/jvm/runtimeDataArea/JavaStack/DynamicLinkingDemo/DynamicLinkingDemo;
      #19 = Utf8               methodA
      #20 = Utf8               methodB
      #21 = Utf8               SourceFile
      #22 = Utf8               DynamicLinkingDemo.java
      #23 = NameAndType        #12:#13        // "<init>":()V
      #24 = NameAndType        #10:#11        // num:I
      #25 = Class              #34            // java/lang/System
      #26 = NameAndType        #35:#36        // out:Ljava/io/PrintStream;
      #27 = Utf8               methodA()....
      #28 = Class              #37            // java/io/PrintStream
      #29 = NameAndType        #38:#39        // println:(Ljava/lang/String;)V
      #30 = Utf8               methodB()....
      #31 = NameAndType        #19:#13        // methodA:()V
      #32 = Utf8               zacyex/jvm/runtimeDataArea/JavaStack/DynamicLinkingDemo/DynamicLinkingDemo
      #33 = Utf8               java/lang/Object
      #34 = Utf8               java/lang/System
      #35 = Utf8               out
      #36 = Utf8               Ljava/io/PrintStream;
      #37 = Utf8               java/io/PrintStream
      #38 = Utf8               println
      #39 = Utf8               (Ljava/lang/String;)V
     */

    public void methodA(){
        System.out.println("methodA()....");

        /*
        public void methodA();
            descriptor: ()V
            flags: (0x0001) ACC_PUBLIC
            Code:
              stack=2, locals=1, args_size=1
                 0: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
                 3: ldc           #4                  // String methodA()....
                 5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
                 8: return
              LineNumberTable:
                line 12: 0
                line 13: 8
              LocalVariableTable:
                Start  Length  Slot  Name   Signature
                    0       9     0  this   Lzacyex/jvm/runtimeDataArea/JavaStack/DynamicLinkingDemo/DynamicLinkingDemo;
         */
    }

    public void methodB(){
        System.out.println("methodB()....");

        methodA();

        num++;

        /*
        public void methodB();
        descriptor: ()V
        flags: (0x0001) ACC_PUBLIC
        Code:
          stack=3, locals=1, args_size=1
             0: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
             3: ldc           #6                  // String methodB()....
             5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
             8: aload_0
             9: invokevirtual #7                  // Method methodA:()V
            12: aload_0
            13: dup
            14: getfield      #2                  // Field num:I
            17: iconst_1
            18: iadd
            19: putfield      #2                  // Field num:I
            22: return
          LineNumberTable:
            line 16: 0
            line 18: 8
            line 20: 12
            line 21: 22
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                0      23     0  this   Lzacyex/jvm/runtimeDataArea/JavaStack/DynamicLinkingDemo/DynamicLinkingDemo;
         */
    }

}
