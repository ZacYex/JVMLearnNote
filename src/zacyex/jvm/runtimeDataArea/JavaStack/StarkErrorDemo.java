package zacyex.jvm.runtimeDataArea.JavaStack;

/**
 * 当栈的大小固定，栈空间满了抛出stackoverflow异常
 * 当栈的大小为动态的，栈空间满了会申请额外的内存，当额外内存也不够使用时抛出OutOfMemory异常
 * 设置栈内存大小的参数：-Xss1m
 * 演示栈中异常：stackoverflow
 * @author zacyex
 * @date 2021/12/1 21:07
 */
public class StarkErrorDemo {
    private static int count = 1;
    public static void main(String[] args) {
        System.out.println(count);
        count++;
        main(args);
    }
}
