package zacyex.jvm.StringTable;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author zacyex
 * @date 2022/1/9 16:43
 *
 * String 在JDK1.8是final char[]的数组，在JDK1.9是final byte[]的数组。
 *        1个char占用2个字节(16位),1个byte占用1个字节(8位),节省了更多空间
 *
 * String的字符串常量池是StringTable一个固定大小的HashTable，若放入String过多
 * 会造成Hash冲突严重，导致链表会很长，从而导致调用String.intern时性能会大幅度下降。
 * 使用-XX:StringTableSize 可设置StringTable的长度
 * jdk6默认值大小长度是1009，jdk7默认值是60013
 *
 * 字符串常量池中不会出现相同的字符串
 * （java语言规范里要求完全相同的字符字面量，应该包含相同的Unicode字符序列，并且必须是指向同一个String类实例）
 *
 */
public class StringDemoOne {

    /**
     * String的不可变性，当String对象的内容变更时，不是在原有的对象上更改数组的值，而是新创建一个对象
     */
    @Test
    public void testOne(){
        String s1 = "abc"; // 字面量定义的方式，“abc”存储在字符串常量池中
        String s2 = "abc";

        System.out.println(s1 == s2);
        s1 += "hello";

        System.out.println(s1 == s2);
    }

    @Test
    public void testTwo(){
        String s1 = "abc";
        String s2 = s1.replace('a','m');
        System.out.println(s1);
        System.out.println(s2);
    }

    /**
     * 通过反射强行修改char数组值后，s1和s2都发生了改变
     */
    @Test
    public void testThree() throws NoSuchFieldException, IllegalAccessException {
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("a");
        String s4 = "bc";
        s3 += s4;
//        s3 = s3.intern();
        String s5 = new String("abc");
        System.out.println(s1+" "+s2+" "+s3+" "+s5);
        System.out.println("abc");

        Class clazz = s1.getClass();
        Field value = clazz.getDeclaredField("value");
        value.setAccessible(true);
        char[] chars = (char[])value.get(s1);
        chars[0] = '1';

        System.out.println(s1+" "+s2+" "+s3+" "+s5); //s5 new的时候，是将常量池的字符串对象的value[]的引用地址和hash值赋值给了s5的value[]和hash
                                                     // 但s5对象本身的地址和常量池字符串对象本身的地址是不一样的，最后s5的值为"1bc"
        System.out.println(s1 == s5); // s5是堆中的字符串，s1是字符串常量池中的字符串，地址不等，结果为false
        System.out.println(s1 == "1bc"); // s1对应的是原本“abc”字符串常量的地址，结果为false
        System.out.println(s1.equals("1bc")); // 把s1转化为char[]一个一个比较，结果是true
        System.out.println("abc"); // 打印1bc，因为反射代码运行后，字面量“abc”对应字符串常量池中的“abc”已经变为了“1bc”,但地址未变
    }

    /**
     * 字符串常量池中不会出现相同的字符串演示
     * Debug模式下，查看每一步执行后，内存中String对象的个数情况
     *
     */
    public static void main(String[] args){
        System.out.println();
        System.out.println("1"); //执行完后2631
        System.out.println("2"); //执行完后2632
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        System.out.println("9");
        System.out.println("10"); //执行完后2640

        System.out.println("1"); //执行完后2640
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        System.out.println("9");
        System.out.println("10");//执行完后2640
    }



}
