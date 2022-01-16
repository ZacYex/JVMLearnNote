package zacyex.jvm.runtimeDataArea.Heap.YoungOldArea;

/**
 * @author zacyex
 * @date 2021/12/19 16:49
 *
 * 通过参数设置新生代和老年代的比例
 * -XX:NewRatio=   默认值是2
 * (-XX:NewRatio=2 代表新时代占1，老年代占2，新生代占整个堆空间的1/3)
 * -XX:SurvivorRatio=  官方文档默认8，但实际是6
 * (-XX:SurvivorRatio=8 代表新时代中两个幸存者区各占1，伊甸园区占8)
 * -XX:-UseAdaptiveSizePolicy  “-”代表关闭自适应内存分配策略
 * -Xmn 设置新生代大小 一般不用
 */
public class EdenSurvivorDemo {

    public static void main(String[] args) {
         new Thread(()->{
             try {
                 Thread.sleep(1000000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             System.out.println("防止程序关闭过快，运行时查看对应个区的大小占比！");
         },"one").start();
    }
}
