package zacyex.jvm.classload;

/**
 * @author zacyex
 * @date 2021/11/24 21:09
 *
 * 有静态属性的类在初始化时，字节码中查看会出现clinit方法(HasClassClinit类)
 * 没有静态属性的类，加载过程中不会产生clinit方法(WithoutClassClinit类)
 */
public class ClassClinit {

    public static class HasClassClinit {
        private static int a = 10;
        static {
            a = 20;
        }
    }

    public static class WithoutClassClinit {
        private int a = 20;
    }

}
