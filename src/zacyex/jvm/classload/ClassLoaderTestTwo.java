package zacyex.jvm.classload;

import com.sun.nio.zipfs.ZipPath;

import java.net.URL;
import java.security.Provider;

/**
 * @author zacyex
 * @date 2021/11/28 19:22
 * 查看类加载器加载哪些路径的class文件
 */
public class ClassLoaderTestTwo {

    public static void main(String[] args) {
        System.out.println("------引导类加载器(BootStrapClassLoader)");
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url);
//            file:/E:/java/jdk1.8.0/jre/lib/resources.jar
//            file:/E:/java/jdk1.8.0/jre/lib/rt.jar
//            file:/E:/java/jdk1.8.0/jre/lib/sunrsasign.jar
//            file:/E:/java/jdk1.8.0/jre/lib/jsse.jar
//            file:/E:/java/jdk1.8.0/jre/lib/jce.jar
//            file:/E:/java/jdk1.8.0/jre/lib/charsets.jar
//            file:/E:/java/jdk1.8.0/jre/lib/jfr.jar
//            file:/E:/java/jdk1.8.0/jre/classes
        }
        // 从中选择一个类查看其类加载器
        ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println(classLoader); // null

        System.out.println("------扩展类加载器(ExtClassLoader)");
        String extDirs = System.getProperty("java.ext.dirs");
        for(String path : extDirs.split(";")){
            System.out.println(path);
//            E:\Java\jdk1.8.0\jre\lib\ext
//            C:\Windows\Sun\Java\lib\ext
        }
        // 从中选择一个类查看其类加载器
        ClassLoader classLoaderTwo = ZipPath.class.getClassLoader();
        System.out.println(classLoaderTwo); // ExtClassLoader
    }
}
