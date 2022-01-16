package zacyex.jvm.runtimeDataArea.Heap;

/**
 * 查看对象在栈上分配的情况
 * -Xmx1G -Xms1G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * -XX:-DoEscapeAnalysis  关闭逃逸分析(即不会进行栈上分配)
 * -XX:+PrintGCDetails   打印GC详细过程
 *
 * 关闭逃逸分析后，User的10000000个对象全分配在了堆中，且耗时为270ms
 * 打开逃逸分析后，User的63386对象分配在了堆中，且耗时为14ms
 *
 * 将堆内存调小后
 * -Xmx256M -Xms256M -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * 关闭逃逸分析后，User的10000000个对象全分配在了堆中，且发生了GC，耗时为205ms
 * 打开逃逸分析后，User的52297个对象分配在了堆中，未发生GC，且耗时为6ms
 *
 * 注意：此时的栈上分配并不是真正在栈上分配的对象，而是把对象替换为标量，将标量存在局部变量表中的形式
 *  * 对应标量替换的参数: -XX:+EliminateAllocations
 * 逃逸分析是栈上分配对象的前提条件，标量替换是hotspot在栈上分配对象的实现手段
 * @author zacyex
 * @date 2021/12/26 15:06
 */
public class StackAllocation {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for(int i=0; i<10000000; i++){
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费时间为："+(end-start)+"ms");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void alloc() {
        User user = new User();
    }

    static class User{
    }
}
