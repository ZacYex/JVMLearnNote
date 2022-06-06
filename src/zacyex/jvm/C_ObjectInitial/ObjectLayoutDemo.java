package zacyex.jvm.C_ObjectInitial;

/**
 * @author zacyex
 * @date 2022/1/4 20:11
 *
 * 对象的内存布局：
 *      对象头：
 *          运行时元数据：
 *              哈希值
 *              GC分代年龄
 *              锁状态标志
 *              线程持有的锁
 *              偏向线程ID
 *              偏向时间戳
 *          类型指针（指向元数据InstanceClass，确定对象所属的类型）
 *          [数组长度（当对象是数组时需要额外记录）]
 *      实例数据（对象真正存储的有效信息）：
 *          相同宽度的字段分配在一起
 *          父类定义的变量在子类之前
 *          若CompactFileds参数为true：子类的窄变量可能插入到父类变量的空隙
 *      对齐填充（不是必须的，无特殊含义，起到占位符的作用）
 *
 */
public class ObjectLayoutDemo {
}
