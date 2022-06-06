package zacyex.jvm.G_GarbageCollection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author zacyex
 * @date 2022/1/18 19:51
 *
 * 通过jprofiler查看某一时刻dump文件中GCRoots有哪些
 */
public class GCRootsDemo {
    public static void main(String[] args) {
        List<Object> numList = new ArrayList<>();
        Date birth = new Date();

        for (int i = 0; i < 100; i++) {
            numList.add(String.valueOf(i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("数据添加完毕，请操作：");
        new Scanner(System.in).next();
        // -->在此生成dump文件
        numList = null;
        birth = null;

        System.out.println("numList,birth已置空，请操作：");
        new Scanner(System.in).next();
        // -->在此生成dump文件

        System.out.println("结束");
    }
}
