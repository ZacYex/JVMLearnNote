package zacyex.jvm.F_StringTable;

/**
 * @author zacyex
 * @date 2022/1/10 21:35
 */
public class StringSSInternDemo {

    public static void main(String[] args) {
        String s = new String("a");
        // -->"a"字面量放入常量池中，且堆空间新开辟一个String对象，
        // 将常量池中“a”的value[]赋值给s的value[]，”a“的hash赋给s的hash
        // (注意这个hash是string重写hashcode()方法的hash值，存储在String对象的属性中，常
        // 量池"a"在StringTable上的引用对应的地址值和s引用对应的地址值是不相等的，故s == s1 为false)
        s.intern();
        // intern执行后发现常量池已经有"a"了便不做处理，返回常量池中字符串的地址
        String s1 = "a";
        System.out.println(s == s1); //false

        String s2 = new String("a") + new String("b");
        // s2生成的最终”ab“是只存在堆中的，因为StringBuilder调用的toString()最终调用的new String(char value[], int offset, int count)构造方法
        // 将StringBuilder自己内部的char数组作为参数传入，过程中未出现”ab“字面量，所以常量池中不会存在”ab“
        s2.intern();
        // intern执行后发现常量池没有"ab"
        // jdk6及之前版本处理方式：直接在StringTable中创建一个新”ab“的String对象，并返回常量池中字符串的引用
        // jdk7及之后版本处理方式：将s2的引用赋值给StringTable对应位置的String引用，并返回常量池中字符串的引用
        String s3 = "ab";
        System.out.println(s2 == s3); //jdk6 为false jdk7及之后 为true

    }
}
