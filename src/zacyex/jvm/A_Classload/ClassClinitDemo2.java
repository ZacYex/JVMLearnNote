package zacyex.jvm.A_Classload;

/**
 * @author zacyex
 * @date 2021/11/24 21:16
 * 初始化静态变量，和声明静态变量的顺序可以没有先后，
 * 但使用静态变量必须在静态变量初始化完成之后
 */
public class ClassClinitDemo2 {
    private static int num = 1;

    static {
        num = 2;
//        num = number;  // -> error
//        System.out.println(number); // -> error
        number = 20; // ->correct
    }

    private static int number = 10;

    public static void main(String[] args) {
        System.out.println(num);
        System.out.println(number);
    }

}
