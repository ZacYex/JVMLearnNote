package zacyex.jvm.StringTable;

import org.junit.Test;

/**
 * @author zacyex
 * @date 2022/1/10 20:09
 *
 * 字符串拼接：
 *    1.常量与常量的拼接结果在常量池，原理是编译期优化
 *    2.常量池中不会有相同内容的字符串
 *    3.只要其中一个是变量，结果就在常量池外的堆空间中。变量拼接原理是StringBuilder
 *    4.如果拼接的结果调用intern()方法，则主动将常量池中还没有的字符串对象放入池中，并返回此对象的地址
 *
 */
public class StringSpliceDemo {

    @Test
    public void test1(){
        String s1 = "a"+"b"+"c";
        String s2 = "abc";

        // 编译后都是直接用的常量池中的abc字面量
        /*
         0: ldc           #2                  // String abc
         2: astore_1
         3: ldc           #2                  // String abc
         5: astore_2
         */

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }

    @Test
    public void test2(){
        String s1 = "javaEE";
        String s2 = "hadoop";

        String s3 = "javaEEhadoop";
        String s4 = "javaEE"+"hadoop";

        // 变量与常量拼接的字节码指令 -->s5
        /*
        13: new           #9                  // class java/lang/StringBuilder
        16: dup
        17: invokespecial #10                 // Method java/lang/StringBuilder."<init>":()V
        20: aload_1
        21: invokevirtual #11                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        24: ldc           #7                  // String hadoop
        26: invokevirtual #11                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        29: invokevirtual #12                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        32: astore        5
         */
        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4); // true
        System.out.println(s3 == s5); // false
        System.out.println(s3 == s6); // false
        System.out.println(s3 == s7); // false
        System.out.println(s5 == s6); // false
        System.out.println(s5 == s7); // false
        System.out.println(s6 == s7); // false

        String s8 = s6.intern();
        System.out.println(s3 == s8); // true
    }

}
