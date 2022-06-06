package zacyex.jvm.A_Classload;

/**
 * @author zacyex
 * @date 2021/11/24 21:22
 * 类加载过程中子类的clinit应当在父类的clinit执行完后执行
 * （父类的加载先于子类）
 */
public class ClassClinitDemo3 {

    private static class A {
        protected static int a = 10;
        static {
            a = 20;
        }
    }

    private static class B extends A {
        protected static int b = a;
    }

    public static void main(String[] args) {
        System.out.println(B.b);
    }
}
