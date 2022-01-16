package zacyex.jvm.classload;

import org.omg.SendingContext.RunTime;

import java.util.Calendar;
import java.util.Comparator;

/**
 * @author zacyex
 * @date 2021/11/28 20:03
 * 演示双亲委派机制
 */
public class ParentalDelegation {

    public static void main(String[] args) {

        java.lang.String string = new java.lang.String();
        /*
         * 输出为null，且未执行静态代码块的输出语句,证明加载String时,虽然AppClassLoader有对应的class文件进行加载
         * 但还是会先由上级的类加载器，一级一级委派，若最上级能加载，则由最上级进行加载，不能加载时
         * 再由下级一级一级看谁能加载
         */
        System.out.println(string.getClass().getClassLoader()); // null

        // 例二
        ClassLoader classLoaderOne = Comparator.class.getClassLoader();
        System.out.println(classLoaderOne); //null

        // 输出为“AppClassLoader”，证明Comparator接口由引导类加载器进行加载
        // Comparator它的实现类，引导类加载器加载不了，由系统类加载器进行加载
        ClassLoader classLoaderTwo = (new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        }).getClass().getClassLoader();
        System.out.println(classLoaderTwo); // AppClassLoader
    }
}
