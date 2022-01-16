package zacyex.jvm.classload;

/**
 * @author zacyex
 * @date 2021/11/24 21:05
 */
public class ClassPrepare {
    private static int a = 0;

    static{
        System.out.println(a);
        a=10;
    }

    public static void main(String[] args) {
        System.out.println(a);
    }
}
