package java.lang;

/**
 * @author zacyex
 * @date 2021/11/28 20:04
 * 此类为演示双亲委派机制类
 * 加载初始化阶段时会执行静态代码块输出“自定义的String类”
 */
public class String {
    static {
        System.out.println("自定义的String类");
    }

    public String[] split(String s){
        return new String[]{"。。。", "123"};
    }

    public static void main(String[] args) {
        /*
         * 运行报错：“错误: 在类 java.lang.String 中找不到 main 方法, 请将 main 方法定义为:
         * public static void main(String[] args)
         * 否则 JavaFX 应用程序类必须扩展javafx.application.Application”
         *
         * 找不到String中的main方法，进一步证明双亲委派机制:
         * String类是由BootStrapClassLoader进行了加载，当前的String类未被加载
         */
        System.out.println("试试就逝世.....");

        // 补充知识
        // 在jvm中判断两个class对象是否是同一个类的两个必要条件：
        // 1.类的全限定类名必须一致
        // 2.加载这个类的类加载器必须相同
    }
}
