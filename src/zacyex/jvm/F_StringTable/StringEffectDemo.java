package zacyex.jvm.F_StringTable;

/**
 * @author zacyex
 * @date 2022/1/13 21:12
 *
 * -XX:+PrintStringTableStatistics查看字符串常量池的情况
 * G1垃圾回收器有对字符串自动去重的操作，将对堆空间中的String引用变更为对StringTable对应值相等的引用，若StringTable没有，则在Stringtable上新建
 */
public class StringEffectDemo {
}
