package zacyex.jvm.runtimeDataArea.JavaStack;

/**
 * @author zacyex
 * @date 2021/12/1 20:56
 */
public class StackDemo {

    public static void main(String[] args) {
        StackDemo stackDemo = new StackDemo();
        stackDemo.methodA();
    }

    public void methodA(){
        int i = 10;
        int j = 20;

        methodB();
    }

    public void methodB(){
        int m = 30;
        int n = 40;
    }
}
