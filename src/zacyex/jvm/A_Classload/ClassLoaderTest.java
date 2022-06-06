package zacyex.jvm.A_Classload;

/**
 * @author zacyex
 * @date 2021/11/28 19:05
 * 查看类加载器 BootStrapClassLoader,ExtClassLoader,AppClassLoader
 */
public class ClassLoaderTest {

    public static void main(String[] args) {

        // 获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader); // AppClassLoader

        // 获取系统类加载器的加载器 -> 扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader); // ExtClassLoader


        // 扩展类加载器的加载器 -> 引导类加载器
        ClassLoader bootStrapClassLoader = extClassLoader.getParent();
        System.out.println(bootStrapClassLoader); // null

        // java核心类库的类都是引导类加载器进行加载的
        ClassLoader classLoaderOne = String.class.getClassLoader();
        System.out.println(classLoaderOne); // null

        // 用户自定义类都是由系统类加载器进行加载的
        ClassLoader classLoaderTwo = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoaderTwo); // AppClassLoader
    }
}
