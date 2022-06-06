package zacyex.jvm.E_ExecuteEngine;

/**
 * @author zacyex
 * @date 2022/1/6 20:09
 *
 * 前端编译：javac --> 将java编译为字节码.class文件
 *
 * 执行引擎：
 *     解释器：将逐行字节码指令转化为对应硬件识别的机器指令，并立即执行。
 *
 *     JIT编译器：将热点代码编译为对应硬件识别的机器指令，并存放在方法区中，可供后续进行直接执行。
 *     （这也是“java是半编译半解释执行的语言”的理解）
 *          热点代码的探测：
 *           1.方法调用计数器：一个方法被多次执行（即程序运行后被多次执行，不是指方法被多次调用）
 *              默认值：client模式下是1500次，server模式下是10000次，可通过参数
 *              -XX:CompileThreshold 来进行设定
 *              热度衰减：
 *                  当超过时间限度时，方法仍未达到被执行的次数，对应计数器会衰减一半，这个对应的时间周期
 *                称之为“半衰周期”。
 *                  进行热度衰减是在垃圾收集时顺带进行的，可以使用-XX:-UseCounterDecay 关闭热度衰减
 *                这样计数就是绝对的了，只要程序执行时间够长，绝大部分方法都会被jit编译器编译为本地代码，
 *                缓存在方法区中。另外还可以使用-XX:CounterHalfLifeTime设置半衰周期的时间，单位是秒。
 *           2.回边计数器：即统计方法中 循环体代码执行的次数
 *
 * 可通过参数让jvm只使用jit编译器，或只使用解释器，或混合的模式：
 *  -Xint 纯解释器的方式
 *  -Xcomp 纯编译器的方式
 *  -Xmixed 混合模式
 *
 *  ****通过设置jvm参数，执行以下main方法，感受每种模式花费的时间***
 *  -Xint 平均在9600ms左右
 *  -Xcomp 平均在1600ms左右
 *  -Xmixed 平均在1400ms左右
 */
public class ExecuteEngineDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        executeTimesByParam(1000000);

        long end = System.currentTimeMillis();

        System.out.println("花费时间为："+(end-start));

    }

    private static void executeTimesByParam(int count) {
        for(int i=0; i<count; i++){
            lable:for (int j =2;j<=100;j++){
                for (int k=2;k<=Math.sqrt(j);k++){
                    if(j%k==0){
                        continue lable;
                    }
                }
            }
        }
    }
}
