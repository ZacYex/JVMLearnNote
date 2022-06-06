package zacyex.jvm.G_GarbageCollection;

import java.util.concurrent.TimeUnit;

/**
 * @author zacyex
 * @date 2022/2/7 15:26
 *
 * 执行System.gc()/Runtime.getRuntime().gc()后不一定会执行gc
 */
public class SystemGCDemo {

    public static void main(String[] args) {
        new SystemGCDemo();
        System.gc();

        //System.runFinalization();
    }

    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("执行full gc了!");
    }
}
