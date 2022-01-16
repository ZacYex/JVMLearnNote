package zacyex.jvm.ExecuteEngine;

/**
 * @author zacyex
 * @date 2022/1/6 21:53
 *
 *  AOT编译器，JIT编译器：
 *      AOT编译器是运行前进行编译，JIT编译器是运行过程中对热点代码进行编译
 *
 *  JIT编译器：
 *      C1编译器：在-client模式下使用的编译器，速度快，但编译后的代码优力度不如C2，主要优化方法内敛，去虚拟化，以及冗余消除
 *      C2编译器：在-server模式下，C1和C2同时进行编译，C2可进行逃逸分析和标量替换，以及同步消除。
 *
 *      JDK10后：
 *          引入Graal编译器
 */
public class JITCompilerDemo {
}
